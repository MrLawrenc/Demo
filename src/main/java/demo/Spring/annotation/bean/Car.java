package demo.Spring.annotation.bean;

/**
 * @author: Liu Mingyao
 * @create: 2018-08-05 10:40
 **/
public class Car {
    public Car() {
        System.out.println("注册bean==========car constructor...");
    }

    private void init() {
        System.out.println("初始化bean");
    }

    private void detory() {
        System.out.println("销毁");
    }
}
