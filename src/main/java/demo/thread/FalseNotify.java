package demo.thread;

import java.util.concurrent.Callable;

/**
 * 在多线程的判断里面使用while，不用if可以避免虚假唤醒.while可以在每次唤醒之后拉回来重新判断
 *
 * @author: Liu Mingyao
 * @create: 2018-08-12 11:25
 **/

//共享资源类
class ShareData {
    int a = 0;

    public synchronized void add() throws InterruptedException {
        if (a == 1) wait();
        // while (a == 1) wait();
        System.out.println(Thread.currentThread().getName() + "正在生产==》" + (++a));
        notifyAll();
    }

    public synchronized void dec() throws InterruptedException {
        if (a == 0) wait();
        //while (a == 0) wait();
        System.out.println(Thread.currentThread().getName() + "正在消费==》" + (--a));
        notifyAll();
    }

}

public class FalseNotify {

    public static void main(String[] args) {
        ShareData data = new ShareData();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    data.add();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "生产者线程01").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    data.dec();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "消费者线程02").start();


        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    data.add();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "生产者线程03").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    data.dec();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "消费者线程04").start();
    }
}
