package demo.Spring.annotation.config;

import demo.Spring.annotation.bean.Person;
import demo.Spring.annotation.condition.LinuxCondition;
import demo.Spring.annotation.condition.WindowsCondition;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * @author: Liu Mingyao
 * @create: 2018-08-04 20:16
 **/
@Configuration
@ComponentScan(value = "demo.Spring.annotation", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {MySpringConfig.class}),
})
public class MySpringConfig2 {

    //prototype:多实例 启动容器bu会自动创建实例,懒加载
    //默认单实例singleton 启动容器就会自动创建实例  注： 因为在测试类中使用的ApplicationContext容器,如果是BeanFactory单实例也是懒加载，但是使用注解类的配置没有使用BeanFactory的容器，只能用ApplicationContext容器.针对单实例也可以采用懒加载,注解
    //request同一个请求创建一个实例
    @Scope("prototype")
    @Lazy//针对单例采用该注解可以使用懒加载
    @Bean("person")
    public Person persion01() {
        return new Person("王二哇", 18);
    }

    /**
     * @Description: Conditional({condition, condition2, condition3})条件满足才会注入bean
     * @Conditional还可以贴在类上，表示满足了这个类里面注册的bean才会生效
     */
    @Conditional({WindowsCondition.class})
    @Bean("windows")
    public Person persion02() {
        return new Person("windows", 20);
    }

    @Conditional({LinuxCondition.class})
    @Bean("linux")
    public Person persion03() {
        return new Person("linux", 20);
    }
}
