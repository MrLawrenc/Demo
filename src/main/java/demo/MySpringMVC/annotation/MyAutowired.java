package demo.MySpringMVC.annotation;


import java.lang.annotation.*;

/**
 * @Author Liu Mingyao
 * @Date 2018/8/19
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAutowired {
      String value() default  "";
}
