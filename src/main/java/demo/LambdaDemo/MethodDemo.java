package demo.LambdaDemo;

import java.util.function.Supplier;

/**
 * @author: Liu Mingyao
 * @create: 2018-08-15 19:02
 **/
public class MethodDemo {
    public static void main(String[] args) {

        new MethodDemo().doHomeWork(B::new);

        Runnable aNew = C::new;
        System.out.println(aNew.getClass());

    }

    public void doHomeWork(A a) {
        System.out.println(a + "==========执行doHomeWork方法====");
        // a.demo();
    }

    public void aaa(Integer a) {
        System.out.println("==传进来的a===" + a);

    }
}

interface A {
    void demo();
}

class B implements A {
    static {
        System.out.println("b静态代码块");
    }

    public B() {
        System.out.println("b构造器");
    }

    @Override
    public void demo() {
        System.out.println("我是B");
    }
}

class C {
}

