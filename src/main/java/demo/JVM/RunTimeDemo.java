package demo.JVM;

import java.util.Arrays;
import java.util.Random;

/**
 * @author: Liu Mingyao
 * @create: 2018-08-15 15:23
 **/
public class RunTimeDemo {
    public static void main(String[] args) {

        Arrays.asList(args).forEach(o -> System.out.println(o));

        Runtime runtime = Runtime.getRuntime();
        System.out.println("当前JVM最大内存(M)" + runtime.maxMemory() / 1024 / 1024);
        System.out.println("初始内存(M)" + runtime.totalMemory() / 1024 / 1024);
        String a = "nnnnn";
        while (true) {
            byte by[] = new byte[10 * 1024 * 1024];
        }
        //while (true) a+="哈哈哈哈"+new Random(1000000)+new Random(888888888);
    }
}
