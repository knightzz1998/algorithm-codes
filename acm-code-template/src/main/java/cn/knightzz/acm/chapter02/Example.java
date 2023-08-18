package cn.knightzz.acm.chapter02;

import java.util.*;

/**
 * @author 王天赐
 * @title: Example
 * @description:
 * @create: 2023-08-14 18:20
 */
public class Example {


    /*
        2
        asdf
        yu
        rtyu
        HJK

     */

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();
        ArrayList<String[]> list = new ArrayList<>();
        for(int i = 0 ; i < n ; i++) {
            String[] tmp = new String[2];
            tmp[0] = sc.nextLine();
            tmp[1] = sc.nextLine();
            list.add(tmp);
        }
        System.out.println(list.size());
        for(String[] strs : list) {
            System.out.println(strs[0]);
            System.out.println(strs[1]);
        }
        ArrayList
    }
}

