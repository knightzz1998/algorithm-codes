package cn.knightzz.acm.chapter03;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author 王天赐
 * @title: Example
 * @description:
 * @create: 2023-08-14 18:59
 */
public class Example {


    /*

        3 3 2 1
        21
        show
        delete 1
        show
        delete 2
        show
        delete 1
        show
        delete 2
        insert 2 5
        show
        insert 1 5
        show
        insert 1 7
        show
        insert 2 5
        show
        insert 3 6
        show
        insert 1 8
        show
        get 2


     */

    public static LinkedList<Integer> list = new LinkedList<>();
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        System.out.println(N);

        for(int i = 0 ; i < N; i++) {
            list.add(sc.nextInt());
        }

        // 21
        N = sc.nextInt();

        for(int i = 0 ; i < N ; i++) {
            String op = sc.next();
            switch(op){
                case "show":
                    System.out.println(op);
                    break;
                case "delete":
                    int v = sc.nextInt();
                    System.out.println(op + " " + v);
                    break;
                case "insert":
                    int idx = sc.nextInt();
                    int val = sc.nextInt();
                    System.out.println(op + " " + val + " " + idx);
                    break;
                case "get":
                    int idx_ = sc.nextInt();
                    System.out.println(op + " " + idx_);
                    break;

            }
        }
    }

}
