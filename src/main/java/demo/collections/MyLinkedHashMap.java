package demo.collections;

/**
 * Entry继承了Node，除了维护了下一个节点信息，还有一个指向上一次存储的数据和之后存储的数据
 *
 * @author: Liu Mingyao
 * @create: 2018-08-04 16:09
 **/
public class MyLinkedHashMap<K, V> extends MyHashMap<K, V> {

    class Entry<K, V> extends MyHashMap.Node<K, V> {
        //维护了前后关系，保证了linkedhashmap的顺序
        Entry<K, V> before, after;

        Entry(K key, V value, Node<K, V> next) {
            super(key, value, next);
        }
    }

    @Override
    public V putVal(K key, V value) {
        return super.putVal(key, value);
    }
}
