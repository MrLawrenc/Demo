package demo.Spring.annotation.config;

import demo.Spring.annotation.bean.Person;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 * @author: Liu Mingyao
 * @create: 2018-08-04 20:16
 **/
//配置类==配置文件
@Configuration  //告诉Spring这是一个配置类
//@ComponentScan  value:指定要扫描的包
//excludeFilters = Filter[] ：指定扫描的时候按照什么规则排除那些组件
//includeFilters = Filter[] ：指定扫描的时候只需要包含哪些组件
//FilterType.ANNOTATION：按照注解
//FilterType.ASSIGNABLE_TYPE：按照给定的类型；
//FilterType.ASPECTJ：使用ASPECTJ表达式
//FilterType.REGEX：使用正则指定
//FilterType.CUSTOM：使用自定义规则
@ComponentScans(
        value = {
                @ComponentScan(value = "demo.Spring.annotation.", includeFilters = {
/*						@Filter(type=FilterType.ANNOTATION,classes={Controller.class}),
                        @Filter(type=FilterType.ASSIGNABLE_TYPE,classes={BookService.class}),*/
                        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = {MyTypeFilter.class})
                }, useDefaultFilters = false)
        }
)
public class MySpringConfig {

    /**
     * @Description:注入bean的id默认是方法名称，bean注解的value值是id
     */
    @Bean("persion")
    public Person persion01() {
        return new Person("王二", 18);
    }
}
