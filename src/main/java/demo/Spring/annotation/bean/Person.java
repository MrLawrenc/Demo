package demo.Spring.annotation.bean;

import lombok.*;

/**
 * @author: Liu Mingyao
 * @create: 2018-08-04 20:12
 **/
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String name;
    private Integer age;
}
