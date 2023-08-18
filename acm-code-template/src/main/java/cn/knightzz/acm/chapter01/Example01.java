package cn.knightzz.acm.chapter01;

import java.util.Scanner;

/**
 * @author 王天赐
 * @title: Example01
 * @description:
 * @create: 2023-08-14 17:32
 */
public class Example01 {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        while(sc.hasNextInt()) {

            int a = sc.nextInt();
            int b = sc.nextInt();

            System.out.println(a + b);
        }

    }
}
