package demo.thread;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

/**
 * @author: Liu Mingyao
 * @create: 2018-08-14 17:02
 **/
public class NotSafeDemo {
    int a = 2;

    //j8特性
    public static void main(String[] args) {
        List list = Arrays.asList("a", "b", "c");

        //lambda表达式迭代集合
        list.forEach(item -> System.out.println(item));
        System.out.println("===================================");
        //java8进行并行流处理后迭代
        list.parallelStream().forEach(s -> System.out.print(s));//输出bca
        System.out.println("===================================");
        list.forEach(o -> System.out.println(o));

        System.out.println("===================================");
        //j8特性，方法引用
        /**
         @Description: Integer::parseInt //静态方法引用
         System.out::print //实例方法引用
         Person::new       //构造器引用
         */
        list.forEach(System.out::println);
        System.out.println("===================================");
        Stream.generate(Math::random).limit(5).forEach(System.out::println);//https://blog.csdn.net/ioriogami/article/details/12782141/
        Object aNew = (Runnable) NotSafeDemo::new;
        System.out.print(aNew);
        test();
    }


    /**
     * @Description: ConcurrentModificationException===》常见并发错误，并发修改异常
     * @Author: Liu Ming
     * 演示集合不安全，及控制
     */
    public static void test() {
//        List<String> list = new ArrayList<>(); Collections.synchronizedCollection(list);==》安全

        /**
         * 不直接往容器里面添加，先将当前容器copy，复制出一个新的容器，再往新的容器添加元素
         * 添加完成之后，再将原来容器引用指向新的，，这样做的好处是可以对CopyOnWriteArrayList并发的读，而不需要加锁，
         * 因为当前容器并不会添加任何元素，这是读写分离的思想。
         * 类似有读写分离的容器还有CopyOnWriteArraySet,ConcurrentHashMap
         * @Author: Liu Ming
         */
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 4));//安全，在写的时候会复制。里面add操作加锁了。
                System.out.println(list);
            }).start();

        }
    }

}
