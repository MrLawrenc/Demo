package demo.collections;

import java.util.Iterator;
import java.util.function.ObjDoubleConsumer;

/**
 * 自己实现HashSet
 *
 * @author: Liu Mingyao
 * @create: 2018-08-04 14:54
 **/
public class MyHashSet<E> {
    private MyHashMap map;
    private static final Object PRESENT = new Object();

    /**
     * @Description: set集合添加方法
     * @Param: 添加进集合的对象
     * @return: true 添加失败
     * @Author: Liu Ming
     */
    public boolean add(Object obj) {
        return map.put(obj, PRESENT) == null;
    }

    /**
     * @Description: 得到set集合中的所有元素
     * @return: 一个包含所有集合元素的迭代器对象
     * @Author: Liu Ming
     */
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }
}
