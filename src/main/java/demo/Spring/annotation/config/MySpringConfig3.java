package demo.Spring.annotation.config;

import demo.Spring.annotation.bean.Color;
import demo.Spring.annotation.bean.Person;
import demo.Spring.annotation.bean.Red;
import demo.Spring.annotation.condition.LinuxCondition;
import demo.Spring.annotation.condition.WindowsCondition;
import org.springframework.context.annotation.*;
/**
 * 给容器中注册组件；
 * 1）、包扫描+组件标注注解（@Controller/@Service/@Repository/@Component）[自己写的类]
 * 2）、@Bean[导入的第三方包里面的组件]
 * 3）、@Import[快速给容器中导入一个组件]
 * 1）、@Import(要导入到容器中的组件)；容器中就会自动注册这个组件，id默认是全类名
 * 2）、ImportSelector:返回需要导入的组件的全类名数组；
 * 3）、ImportBeanDefinitionRegistrar:手动注册bean到容器中
 * 4）、使用Spring提供的 FactoryBean（工厂Bean）;
 * 1）、默认获取到的是工厂bean调用getObject创建的对象
 * 2）、要获取工厂Bean本身，我们需要给id前面加一个&
 * &colorFactoryBean
 */

/**
 * @author: Liu Mingyao
 * @create: 2018-08-04 20:16
 **/
@Configuration
@ComponentScan(value = "demo.Spring.annotation", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {MySpringConfig.class, MySpringConfig2.class}),
})
@Import({Color.class, Red.class})//id默认是全限定名demo.Spring.annotation.bean.Color
public class MySpringConfig3 {

    @Bean("person")
    public Person persion01() {
        return new Person("王二哇", 18);
    }


}
