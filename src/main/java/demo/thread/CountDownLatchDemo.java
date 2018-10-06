package demo.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * CountDownLatch====》实现线程的调度有序
 * 让一些线程阻塞，知道另外一些线程完成一系列操作之后才被唤醒
 *
 * @author: Liu Mingyao
 * @create: 2018-08-14 14:42
 **/
enum Student {

    ONE(1, "张1"), TWO(2, "张2"), THREE(3, "张3"), FOUR(4, "张4"), FIVE(5, "张5");
    private int i;
    private String name;

    public static String getName(int i){
        for (Student student : values()) {
            if (student.getI()==i) return  student.getName();
        }
        return null;
    }

    Student(int i, String name) {
        this.i = i;
        this.name = name;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);

        for (int i = 1; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "线程==》 " + " 同学正在离开教室");

                latch.countDown();//计数器减一

            }, Student.getName(i)).start();//Student.getName(i)代表线程名字
        }
        latch.await();//只有当计算器5自减为0之后才会被唤醒，不然就是阻塞状态

        System.out.println("同学全部离开教室，班长离开教室，并且关门");
        





        /**
         * @Description: 测试并发
         * @Param: [args]
         * @return: void
         * @Author: Liu Ming
         */
        CountDownLatch countDownLatch1=new CountDownLatch(1000);
        for (int i = 1; i < 6; i++) {
            new Thread(() -> {

                System.out.println(Thread.currentThread().getName() + "线程==》 " + " 同学正在离开教室");

                latch.countDown();//计数器减一

            }, Student.getName(i)).start();//Student.getName(i)代表线程名字
        }

    }
}
