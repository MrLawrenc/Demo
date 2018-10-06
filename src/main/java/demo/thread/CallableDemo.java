package demo.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Callable接口
 * <p>
 * 可以得到线程的返回值，但是不影响主线程的执行
 *
 * @author: Liu Mingyao
 * @create: 2018-08-13 10:18
 **/
/*class Data implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        TimeUnit.SECONDS.sleep(20);//睡眠更灵活
        System.out.println("我是callable的call()方法");
        return 200;
    }
}*/

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // FutureTask<Integer> futureTask = new FutureTask<>(new Data());等价于下面lambda表达式
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(20);//睡眠更灵活,等价于Thread。sleep();
            System.out.println("我是callable的call()方法");
            return 200;
        });

        new Thread(futureTask, "哈哈哈哈1").start();
        new Thread(futureTask, "哈哈哈哈2").start();//只会调用一次,第二次是不会调用的

        Integer integer = futureTask.get();//得到线程返回值，异步回调方法等线程返回再执行主线程，尽量将get放到最后不应该等待

        System.out.println("我是返回值====》" + integer);
    }
}
