package demo.MySpringMVC.contrller;

import demo.MySpringMVC.annotation.MyAutowired;
import demo.MySpringMVC.annotation.MyController;
import demo.MySpringMVC.annotation.MyRequestMapping;
import demo.MySpringMVC.service.UserServiceImpl;

/**
 * @author: Liu Mingyao
 * @create: 2018-08-19 16:35
 **/
@MyController("userController")
@MyRequestMapping("/user")
public class UserController {
    @MyAutowired
    private UserServiceImpl userServiceImpl;

    @MyRequestMapping("/print")
    public void printUserInformation(String name, String age) {
        userServiceImpl.information(name, age);
    }
}
