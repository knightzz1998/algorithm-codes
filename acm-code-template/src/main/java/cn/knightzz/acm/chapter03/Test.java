package cn.knightzz.acm.chapter03;

/**
 * @author 王天赐
 * @title: Test
 * @description:
 * @create: 2023-09-26 10:52
 */
public class Test {

    public void test() {
        int j = 0 ;
        for (int i = 0; i < 100; i++) {
            j = j++;
        }
        System.out.println(j);
    }

    // 局部变量表 0
    // 操作数栈
    public static void main(String[] args) {

        Test scratch = new Test();
        scratch.test();

    }
}
