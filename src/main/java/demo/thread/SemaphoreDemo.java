package demo.thread;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore==>车位
 *
 * @author: Liu Mingyao
 * @create: 2018-08-14 16:01
 **/
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//模拟三个停车位，如果为1相当于lock，同步锁

        for (int i = 1; i <= 5; i++) {//模仿五个人去停车，
            new Thread(() -> {
                try {
                    semaphore.acquire();//进入车位，超过3个车位就不能停，只能等别人走了才能停
                    int waitTime = new Random().nextInt(6);
                    System.out.println(Thread.currentThread().getName() + "\t抢到了1个车位*****"+"\t停车"+waitTime+"秒");
                    TimeUnit.SECONDS.sleep(waitTime);//每个人抢到车位停三秒再离开
                    System.out.println(Thread.currentThread().getName() + "\t离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();//离开车位
                }
            }, Student.getName(i)).start();
        }

    }

}
