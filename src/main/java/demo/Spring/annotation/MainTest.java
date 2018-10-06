package demo.Spring.annotation;

import demo.Spring.annotation.bean.Person;
import demo.Spring.annotation.config.MySpringConfig;
import demo.Spring.annotation.config.MySpringConfig2;
import demo.Spring.annotation.config.MySpringConfig3;
import demo.Spring.annotation.controller.BookController;
import demo.Spring.annotation.service.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author: Liu Mingyao
 * @create: 2018-08-04 20:09
 **/
public class MainTest {


    public static void main(String[] args) {

        //通过配置类得到注入spring容器的bean
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MySpringConfig.class);

        Object persion1 = applicationContext.getBean("persion");
        Person persion2 = applicationContext.getBean(Person.class);
        String[] beans = applicationContext.getBeanNamesForType(Person.class);
        for (String bean : beans) {
            System.out.println(bean);
        }
        System.out.println(persion1 + "====" + persion2);
        System.out.println("================================================================");

        for (String s : applicationContext.getBeanDefinitionNames()) {
            System.out.println(s);
        }
    }

    @Test
    public void tes2() throws Exception {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MySpringConfig2.class);
        Person bean = applicationContext.getBean(Person.class);
        Person bean2 = applicationContext.getBean(Person.class);

        System.out.println(bean == bean2);
    }


    @Test
    public void test3() throws Exception {
        //获取系统运行环境
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("os.name");
        //Windows 8.1
        System.out.println(property);


        String[] names = applicationContext.getBeanNamesForType(Person.class);
        for (String name : names) {
            System.out.println(name);
        }
    }

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MySpringConfig3.class);

    @Test
    public void testImport() throws Exception {
        getBeans();
    }

    public void getBeans() {
        String[] names = applicationContext.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }

}
