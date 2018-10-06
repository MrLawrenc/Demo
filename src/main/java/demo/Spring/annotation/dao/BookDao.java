package demo.Spring.annotation.dao;

import org.springframework.stereotype.Repository;

/**
 * @author: Liu Mingyao
 * @create: 2018-08-04 20:36
 **/
@Repository
public class BookDao {
    public void doHomeWork() {
        System.out.println("做作业====================");
    }

}
