package demo.collections;

import java.util.HashSet;
import java.util.Set;

/**
 * 模拟HashMap
 * <p>
 * <p>保证hashmap安全的方法：
 * 1.java.util.Hashtable类
 * 2.使用 java.util.Collections.synchronizedMap(Map<K,V>) 方法进行封装。
 * 3.使用 java.util.concurrent.ConcurrentHashMap 类。
 * </p>
 *
 * @author: Liu Mingyao
 * @create: 2018-07-29 19:56
 **/
public class MyHashMap<K, V> implements demo.collections.MyMap<K, V> {

    //存放数组元素,即存放的是每一个链表。采懒加载的方式
    Node<K, V>[] tables = null;

    //table已经存储了的元素数量
    private int size;

    //map中默认的初始容量,二进制运算性能比较高
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    //实际的数组容量大小
    int threshold;

    //默认的初始化最大容量2的30次方
    static final int MAXIMUM_CAPACITY = 1 << 30;

    //加载因子，越低Hash碰撞的几率就越低.
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * @Description: 并不是数组长度,因为链表上可能有多个元素，而是数组已经存储了元素的位置的总数
     * @Author: Liu Ming
     */
    @Override
    public int size() {
        return this.size;
    }


    /**
     * @return null表示没有发生hash冲突，返回其他值则是覆盖掉的值
     * @Description: 往map中存放Key, Value类型的数据
     * @Author: Liu Ming
     */
    @Override
    public V put(K key, V value) {
        return putVal(key, value);
    }

    public V putVal(K key, V value) {
        //先判断数组大小是否初始化了
        if (tables == null) {
            threshold = DEFAULT_INITIAL_CAPACITY;
            tables = new Node[threshold];
        }
        //判断数组是否需要扩容
        if (size >= threshold * DEFAULT_LOAD_FACTOR) {
            resize();
        }
        //先得到key的hashcode值，再根据取模运算得到所在tables数组下标的值
        int index = getHash(key, threshold);
        Node<K, V> node = tables[index];
        //判断在数组中该下标的位置是否已经存在元素
        if (node == null) {
            //代表该下标没有存放元素,创建一个节点对象放入数组下标为index的位置中
            node = new Node<>(key, value, null);
            tables[index] = node;
            size++;
            return null;
        } else {
            Node nowNode = isSameObj(key, node);
            if (nowNode != null) {
                //是同一个对象，直接覆盖前面存入数组的元素.返回的是被覆盖的元素
                V oldValue = (V) nowNode.setValue(value);
                return oldValue;
            } else {
                System.out.println("发生hash碰撞但不是同一个对象可以存入map中,已经存在的key=" + node.getKey() + "传进来的key=" + key);
                //不是同一个对象，添加到链表中,将原来存在的传进去代表作为新node的下一个节点。
                node = new Node(key, value, node);
                tables[index] = node;
                size++;
                return null;
            }

        }


    }

    @Override
    public V get(Object key) {
        int index = getHash(key, threshold);
        Node<K, V> node = tables[index];
        if (node == null) {
            return null;
        } else {
            //存在该key对应的value值.还需要遍历该下标处的数组元素(是一个node组成的链表)
            while (node != null) {
                if (node.getKey().equals(key)) {
                    return node.getValue();
                }
                node = node.next;
            }
            return null;
        }

    }

    /**
     * @Description: 通过key和当前map容量得到该key所在的数组索引位置
     * @Param:
     * @return:
     * @Author: Liu Ming
     */
    private int getHash(Object key, int defaultSize) {
        if (key == null) {
            //如果key为null,name放在数组第一个位置
            return 0;
        }
        int index = key.hashCode() & defaultSize - 1;//为了提高效率采用：h & (length-1); 按位与  的方式计算,这样得到的index更均匀
        return index;
    }

    /**
     * @Description: 判断传进来的key是不是和已经存在节点的key是同一个对象
     * @Param:
     * @return: null 表示之前没有存入过key
     * @Author: Liu Ming
     */
    private Node isSameObj(K key, Node nowNode) {
        //需要判断新加入的对象和node是不是同一个对象
        if (key.equals(nowNode.getKey()) || key == nowNode.getKey()) {
            return nowNode;
        } else {
            //如果第一个节点不是同一个对象，而且该节点还有下一个node就需要递归，再次判断链表的其他是不是和该key是同一对象
            if (nowNode.next != null) {
                //递归,并且还需要接收递归的返回值node，表示找到了相同的对象
                Node sameObj = isSameObj(key, nowNode.next);
                return sameObj;
            }
            return null;
        }
    }

    /**
     * @Description: 获取数组中存储的每一个node元素
     * @Param:
     * @return:
     * @Author: Liu Ming
     */
    public void getItems() {
        for (int i = 0; i < tables.length; i++) {
            Node<K, V> node = tables[i];
            System.out.print("数组的下标是====》" + i);
            while (node != null) {
                System.out.print(" [该数组对应的node的key是 = " + node.getKey() + "   value是 = " + node.getValue() + "]");
                node = node.next;
            }
            System.out.println("");
        }
    }

    /**
     * @Description: 当数组容量达到 最大*加载因子 这里即是16*0.75=12的时候数组就会扩容，扩为原来的两倍
     * @Param:
     * @return:
     * @Author: Liu Ming
     */
    public void resize() {
        //扩容为原来的两倍
        Node[] newTables = new Node[threshold << 1];
        for (int i = 0; i < tables.length; i++) {
            Node<K, V> oldNode = tables[i];
            tables[i] = null;//垃圾回收，并且不清空在扩容之后节点会一直存在，只有覆盖的才会消失
            while (oldNode != null) {
                //得到扩容之后新数组该key的下标
                int newIndex = getHash(oldNode.getKey(), newTables.length);
                Node<K, V> oldNextNode = oldNode.next;

                //改变老的node的下一个引用为新数组存放的node
                oldNode.next = newTables[newIndex];

                //再改变了老node的下一个引用之后再将其赋给新的table。相当于每次存放的node都是放在链表的最前面
                newTables[newIndex] = oldNode;
                //判断是否需要再循环遍历
                oldNode = oldNextNode;
            }
        }
        //扩容之后将新的table赋给老table，并改变容量
        tables = newTables;
        threshold = newTables.length;
        newTables = null;//垃圾回收
    }

    /**
     * @Description: 移除map中key对应的元素
     * @Param: 传入的key
     * @return: 删除key对应的value值，如果为null则表示没有与该key对应的值
     * @Author: Liu Ming
     */
    public V removeNode(K k) {
        V v = get(k);
        if (v != null) {
            int index = getHash(k, threshold);
            tables[index] = null;
        } else {
            throw new RuntimeException("该key没有对应的value值");
        }
        return null;

    }

    /**
     * @Description: 删除该map中所有的映射(key, value)
     * @Param:
     * @return:
     * @Author: Liu Ming
     */
    public void clear() {
        this.tables = null;
    }

    /**
     * @Description: 返回此map包含的所有key
     * @Param:
     * @return:
     * @Author: Liu Ming
     */
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        Node<K, V> node;
        for (int i = 0; i < this.tables.length; i++) {
            node = this.tables[i];
            while (node != null) {
                keySet.add(node.getKey());
                node = node.next;
            }
        }
        return keySet;
    }


    /**
     * @Description: 仅仅当key和value对应时才删除该条映射
     * @return: true 删除成功
     * @Author: Liu Ming
     */
    public boolean remove(K key, V value) {
        V v = this.get(key);
        if (v != null) {
            if (v.equals(value)) {
                put(key, null);
            } else {
                return false;
            }
        } else {
            if (value != null)
                return false;
        }
        return true;
    }

    /**
     * @Description: 判断map是否为空
     * @return: true 为空
     * @Author: Liu Ming
     */
    public Boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * @Description: 返回所有的node节点
     * @return:
     * @Author: Liu Ming
     */
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> nodes = new HashSet<>();
        for (int i = 0; i < this.tables.length; i++) {
            nodes.add(this.tables[i]);
        }
        return nodes;
    }

    /**
     * @Description: 一个Node节点 j8之后好像是Entry
     * @Author: Liu Ming
     */
    static class Node<K, V> implements Entry<K, V> {
        // 存放Map 集合 key
        private K key;
        // 存放Map 集合 value
        private V value;
        // 下一个节点Node
        private Node<K, V> next;

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override

        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }
}


