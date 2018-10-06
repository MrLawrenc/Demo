package demo.LambdaDemo;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 对象可能为空时，Optional避免空指针
 *
 * @author: Liu Mingyao
 * @create: 2018-08-15 19:38
 **/
public class OptionalDemo {
    public  String name;
    public Integer age;

    public OptionalDemo(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        String a[] = {"a", "b"};
        Optional<String[]> fullName = Optional.ofNullable(a);
        System.out.println("Full Name is set? " + fullName.isPresent());//true 传入的a不为空
        System.out.println(fullName.map(s -> "Hey 不为空  长度为" + s.length + "!").orElse("传入的参数为空"));


        //filter过滤条件，过滤空字符串
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        filtered.forEach(System.out::println);


        Random random = new Random();
        //10个随机数
        random.ints().limit(10).forEach(System.out::println);


        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // map获取对应的平方数,distinct()可以去重
        List<Integer> squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        squaresList.forEach(System.out::println);


        List<String> strings2 = Arrays.asList("abc", "", "bc", "", "abcd", "", "jkl");
        // 获取空字符串的数量
        System.out.println(strings2.stream().filter(string -> string.isEmpty()).count());


        // limit 方法用于获取指定数量的流。 以下代码片段使用 limit 方法打印出 10 条数据：
        Random random1 = new Random();
        random1.ints().limit(10).forEach(System.out::println);

        // sorted 方法用于对流进行排序。以下代码片段使用 sorted 方法对输出的 10 个随机数进行排序：
        System.out.println("sorted()对流排序");
        Random random2 = new Random();
        random2.ints(5).sorted().forEach(System.out::println);


        System.out.println("parallelStream并行流");
        //parallelStream 是流并行处理程序的代替方法。以下实例我们使用 parallelStream 来输出空字符串的数量：
        List<String> strings3 = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        // 获取空字符串的数量
        System.out.println(strings3.parallelStream().filter(string -> string.isEmpty()).count());


        //Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。Collectors 可用于返回列表或字符串：
        List<String> strings4 = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> filtered2 = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println("筛选列表: " + filtered2);
        String mergedString = strings4.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);


        //一些产生统计结果的收集器也非常有用。它们主要用于int、double、long等基本类型上，它们可以用来产生类似如下的统计结果。
        System.out.println("统计流==============================");
        List<Integer> numbers1 = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        IntSummaryStatistics stats = numbers1.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
        //向上取整
        System.out.println(Math.ceil(3.2));


        System.out.println("代替传统的for循环================");
        int[] bb = {1, 5, 9, 8};
        IntStream.range(0, 4)
                .forEach(aa -> System.out.println(bb[aa]));


        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .anyMatch(s -> {//最终操作,wei真时返回true，结束执行
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A");
                });


        /**
         * @Description: toMap两个参数当key重复的时候会抛异常IllegalStateException，因此设置第三个参数防止该情况
         * 当然map中的key由于是不同对象中的，因此hashcode值是不同的
         * @Author: Liu Ming
         */
        OptionalDemo[] optionalDemos={new OptionalDemo("Peter",18),new OptionalDemo("Lucy",18)};
        Map<Integer, String> map = Arrays.asList(optionalDemos).stream()
                .collect(Collectors.toMap(
                        p -> p.age,
                        p -> p.name,
                        (name1, name2) -> name1 + "或者" + name2));

        System.out.println(map);
       Arrays.asList(map.get(18).split("或者")).forEach(System.out::println);

    }
}
