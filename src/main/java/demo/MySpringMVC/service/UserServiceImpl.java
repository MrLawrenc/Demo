package demo.MySpringMVC.service;

import demo.MySpringMVC.annotation.MyAutowired;
import demo.MySpringMVC.annotation.MyService;
import demo.MySpringMVC.dao.UserDaoImpl;

/**
 * @author: Liu Mingyao
 * @create: 2018-08-19 16:33
 **/
@MyService("userServiceImpl")
public class UserServiceImpl {
    @MyAutowired
    private UserDaoImpl userDaoImpl;

    public void information(String name, String age) {
        userDaoImpl.information(name, age);
    }
}
