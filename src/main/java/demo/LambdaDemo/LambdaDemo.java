package demo.LambdaDemo;

import static demo.LambdaDemo.Foo.sub1;
import static demo.LambdaDemo.Foo.sub2;

/**
 * j8新特性
 * 1.拷贝小括号+写死左箭头+落地大括号
 * <p>
 * 一个接口里面只有一个接口===》函数式接口=====》才可以使用lambda表达式
 * <p>
 * 注解@FunctionalInterface==》表示函数式接口的注解，如果接口只有一个方法可以不用加，但是多个（有default方法）就必须加
 *
 * @author: Liu Mingyao
 * @create: 2018-08-11 16:57
 **/
@FunctionalInterface
interface Foo {
    //void sayHello();
    void sayHello(String msg);

    default int add(int x, int y) {
        return x + y;
    }

    static int sub1(int x, int y) {
        return x - y;
    }

    static int sub2(int x, int y) {
        return x - y + 1;
    }
}

public class LambdaDemo {
    public static void main(String[] args) {
       /* Foo foo = new Foo() {
            @Override
            public void sayHello() {
                System.out.println("hello=====>lambda");
            }
        };

        foo.sayHello();
        foo = () -> {
            System.out.println("lambda表达式=====不带参数=====");
        };
        foo.sayHello();*/

        Foo foo = (msg) -> {
            System.out.println(msg);
        };
        foo.sayHello("我是带参数的lambda表达式");

        int result = foo.add(2, 5);
        System.out.println(result + "这是使用默认方法，接口必须使用注解声明是函数式接口");

        int sub1 = sub1(10, 2);
        System.out.println(sub1);

        int sub2 = sub2(10, 2);
        System.out.println(sub2);


    }
}
