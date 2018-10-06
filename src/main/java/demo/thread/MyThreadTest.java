package demo.thread;


/**
 * 复习线程知识
 * 两种开启线程常见的方式(共4种)wait notify 同步代码块synchronized
 *
 * @author: Liu Mingyao
 * @create: 2018-08-03 19:59
 **/

//写操作
class Write extends Thread {
    MyThreadTest test;

    public Write(MyThreadTest test) {
        this.test = test;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (test) {
                if (!test.isWrite) {
                    try {
                        test.wait();//如果状态不是写就让出锁资源，给读线程
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (test.flag) {
                    test.setName("小红");
                    test.setSex("女");
                    test.flag = false;
                } else {
                    test.setName("小黄");
                    test.setSex("男");
                    test.flag = true;
                }
                test.isWrite = false;
                test.notify();
            }
        }
    }
}

//读操作
class Reade implements Runnable {
    MyThreadTest test;

    public Reade(MyThreadTest test) {
        this.test = test;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (test) {
                if (test.isWrite) {
                    try {
                        test.wait();//如果状态不是读就让出锁资源，给写线程
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("名字是：" + test.getName() + "  " + "性别是：" + test.getSex());
                test.isWrite = true;
                test.notify();
            }
        }
    }
}

public class MyThreadTest {
    public String name;
    public String sex;
    boolean isWrite = true;//默认为true 开始写
    boolean flag = true;//0就开始写小红 1写小黄


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public static void main(String[] args) {
        MyThreadTest test = new MyThreadTest();
        Write write = new Write(test);
        Thread thread = new Thread(new Reade(test));
        //Thread.State线程的六种状态;NEW  RUNNABLE  BLOCKED  WAITING TIMED_WAITING TERMINATED
        write.start();
        thread.start();
    }

}
