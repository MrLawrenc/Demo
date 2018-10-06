package demo;


import com.sun.org.apache.xpath.internal.SourceTree;

/**
 * @author: Liu Mingyao
 * @create: 2018-10-04 22:27
 * 亚信线上测试题：判断传入的字符串数组是否能首位相连
 **/
public class YaXin {

    public static void main(String[] args) {
        /**
         * bug1:{"fbc", "cdf", "jfrh", "rij"}这种形成环形的会判断失误
         */
        String str[] = {"abc", "cdf", "jfrh", "fij"};
        boolean isCan = isCan(str);
        System.out.println(isCan == true ? "能首尾相连" : "不能首尾相连");
    }

    private static boolean isCan(String[] str) {
        String befor[] = new String[str.length];
        String after[] = new String[str.length];
        for (int i = 0; i < str.length; i++) {
            befor[i] = str[i].substring(0, 1);
            after[i] = str[i].substring(str.length - 2, str.length - 1);
            System.out.println("===========存入首字母：" + befor[i] + "尾字母：" + after[i]);
        }


        int count = 0;
        for (int i = 0; i < str.length; i++) {
            String s1 = after[i];//尾
            boolean can = false;
            for (int j = 0; j < str.length; j++) {
                can = false;
                if (i != j) {
                    String s2 = befor[j];//首
                    if (s1.equals(s2)) {
                        can = true;
                        break;
                    } else {
                        continue;
                    }
                }
            }
            if (!can) count++;
        }
        System.out.println("===================:" + count);
        if (count >= 2)
            return false;
        return true;
    }
}
