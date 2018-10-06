package demo.thread;

import javax.swing.text.StyledEditorKit;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * CyclicBarrier==》做的加法，而CountDownLatch是减法
 *
 * @author: Liu Mingyao
 * @create: 2018-08-14 15:39
 **/
public class CyclicBarrierDemo {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        CyclicBarrier cyc = new CyclicBarrier(5, () -> System.out.println("======召唤神龙======"));

        for (int i = 1; i <= 5; i++) {
            int tem=i;
           new Thread(()->{
               System.out.println(Thread.currentThread().getName()+"\t找到了第"+tem+"颗龙珠");
               try {
                   cyc.await();//从0开始加，直到达到构造器的数字就执行构造器里面的线程
               } catch (Exception e) {
                   e.printStackTrace();
               }
           },Student.getName(i)).start();

        }

    }
}
