package demo.MySpringMVC.annotation;


import java.lang.annotation.*;

/**
 * @Author Liu Mingyao
 * @Date 2018/8/19
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRequestMapping {
      String value() default  "";
}
