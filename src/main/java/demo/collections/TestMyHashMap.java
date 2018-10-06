package demo.collections;


import java.util.Iterator;

/**
 * 自己仿写实现HashMap的测试
 *
 * @author: Liu Mingyao
 * @create: 2018-07-30 14:45
 **/
public class TestMyHashMap {
    MyHashMap map;

    //@Test
    public void testPut() throws Exception {
        map = new MyHashMap<String, Object>();
        map.put("a", "6666666");
        map.put("16", "1699999");
        map.put("c", "aaaaa");
        map.put(0, "000000");
        map.put("e", "aaaaa");
        map.put("f", "fffffffff");
        map.put("g", "aaaaa");
        map.put("17", "17aaaa");
        map.put("1", "1aaaa");
        map.put("我是", "我是");
        map.put("java", "java");


        Object put = map.put("a", "bbb");
        Object f = map.put("f", "woff");
//        System.out.println("覆盖旧的值是====》"+put);
//        System.out.println("覆盖旧的值是====》"+f);
//        System.out.println("map的存的数量是===="+map.size());
//
//        System.out.println(map.get("17"));
//        System.out.println(map.get("f"));
//        System.out.println(map.get("a"));
//        System.out.println(map.get("1"));
        System.out.println("=====================我是扩容之前的====================");
        map.getItems();
        map.put("python", "python");
        map.put("年", "年");
        map.put("你啊啊啊", "1aaa你啊啊啊a");
        System.out.println("=====================我是扩容之后的====================");
        map.getItems();
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next() + "@@@@@@@@@@@@@@");
        }

    }

    public static void main(String[] args) throws Exception {
        new TestMyHashMap().testPut();
    }
}
