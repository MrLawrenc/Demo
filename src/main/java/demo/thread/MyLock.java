package demo.thread;


import java.util.HashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 复习线程知识
 * 两种开启线程常见的方式(共4种)wait notify 同步代码块synchronized
 * wait和notify只能在同步代码块中使用
 *
 * @author: Liu Mingyao
 * @create: 2018-08-03 19:59
 **/

//写操作
class Write1 extends Thread {
    MyLock test;
    Condition condition;

    public Write1(MyLock test, Condition condition) {
        this.test = test;
        this.condition = condition;
    }

    @Override
    public void run() {
        while (true) {

            test.lock.lock();
            if (!test.isWrite) {
                try {
                    condition.await();//如果状态不是写就让出锁资源，给读线程
                } catch (Exception e) {
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
            condition.signal();

            test.lock.unlock();
        }
    }
}

//读操作
class Reade1 implements Runnable {
    MyLock test;
    Condition condition;

    public Reade1(MyLock test, Condition condition) {
        this.test = test;
        this.condition = condition;
    }

    @Override
    public void run() {
        while (true) {

            test.lock.lock();//加锁

            if (test.isWrite) {
                try {
                    condition.await();//如果状态不是读就让出锁资源，给写线程
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("名字是：" + test.getName() + "  " + "性别是：" + test.getSex());
            test.isWrite = true;
//            condition.signal();随机唤醒一个等待队列的线程
            condition.signal();//唤醒所有等待线程

            test.lock.unlock();//释放锁

        }
    }
}

public class MyLock {
    public String name;
    public String sex;
    boolean isWrite = true;//默认为true 开始写
    boolean flag = true;//0就开始写小红 1写小黄

    public Lock lock = new ReentrantLock();


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
        MyLock test = new MyLock();
        Condition condition = test.lock.newCondition();
        Write1 write = new Write1(test, condition);
        Thread thread = new Thread(new Reade1(test, condition));
        write.start();
        thread.start();
    }

}
