package demo.collections;

/**
 * 模拟HashMap实现的接口
 * @Author Liu Mingyao
 * @Date 2018/7/29
 */
public interface MyMap<K, V> {
    int size();

    V put(K key, V value);

    V get(Object key);

    interface Entry<K, V> {

        K getKey();


        V getValue();


        V setValue(V value);

    }
}
