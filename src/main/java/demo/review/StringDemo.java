package demo.review;

/**
 * @author: Liu Mingyao
 * @create: 2018-08-20 14:30
 **/
public class StringDemo {


    public static void main(String[] args) {
        String s1 = new String("abc");
        String s2 = "abc";
        String s3 = new String("abc");
        System.out.println(s1 == s2);
        System.out.println(s3 == s2);
        System.out.println(s3 == s1);
        System.out.println("******************************************");

        /**
         *intern遵循以下原则:对于任意两个字符串s和t，当且仅当s.equalse(t)为true时,s1.intern()才等于s2.intern()
         *在常量池里面找
         */
        System.out.println(s1 == s1.intern());
        System.out.println(s2 == s2.intern());
        System.out.println(s2.intern() == s1.intern());
        System.out.println("******************************************");


        String s4 = "java";
        String s5 = "ja";
        String s6 = "va";
        System.out.println(s4 == "java");
        System.out.println(s4 == s5 + s6);
        System.out.println(s4 == "ja" + s6);
        System.out.println("******************************************");
    }
}
