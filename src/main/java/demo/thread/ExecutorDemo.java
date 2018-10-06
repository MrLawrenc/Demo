package demo.thread;

import sun.text.resources.cldr.ia.FormatData_ia;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 获得多线程方式===》线程池
 * Executor==>线程池总接口，一般不用，使用继承的子接口ExecutorService，相当于使用Collection的Arraylist
 *
 * @author: Liu Mingyao
 * @create: 2018-08-14 16:21
 **/
public class ExecutorDemo {

    public static void main(String[] args) {
        ExecutorService service = null;
        try {
            //一池五个线程
            //service = Executors.newFixedThreadPool(5);// Executors工具类，相当于Collections
            //service = Executors.newFixedThreadPool(1);
            service = Executors.newCachedThreadPool();//线程不够就创建
            for (int i = 1; i <= 5; i++) {
                Future<Integer> fu = service.submit(() -> {
                    System.out.print(Thread.currentThread().getName() + " ");
                    return new Random().nextInt(10);
                });
                System.out.println("==============result: " + fu.get());
            }
        } catch (Exception e) {

        } finally {
            service.shutdown();
        }
    }

}
