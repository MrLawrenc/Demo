package demo.thread;

/**
 * interrupt 对等待的线程抛出异常
 * 可以改变循环变量的值来终止该线程
 *
 * @author: Liu Mingyao
 * @create: 2018-08-04 10:16
 **/
public class StopThread extends Thread {
    @Override
    public synchronized void run() {
        System.out.println("我是子线程开始执行");
        while (flag) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("子线程抛出了异常");
            }
        }
        System.out.println("子线程结束==========================");
    }

    volatile boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        StopThread stopThread = new StopThread();
        stopThread.start();
        for (int i = 0; i < 10; i++) {
            System.out.println("我是主线程  i=" + i);
            Thread.sleep(1000);
            if (i == 4) {
                stopThread.flag = false;
                stopThread.interrupt();
            }
        }
        System.out.println("主线程结束=================");
    }

}
