package demo.MySpringMVC.dao;

import demo.MySpringMVC.annotation.MyDao;

/**
 * @author: Liu Mingyao
 * @create: 2018-08-19 16:30
 **/
@MyDao("userDaoImpl")
public class UserDaoImpl {
    public void information(String name, String age) {
        System.out.println("{name:" + name + ",age:" + age + "}");
    }
}
