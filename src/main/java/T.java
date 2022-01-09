/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author zhuiz
 */
public class T {
    
    public static void main(String[] args) {
        System.out.println(digit(103,2));
        System.out.println(digit(103,3));
        System.out.println(digit(103,1));
        System.out.println(digit(103,4));
    }
    public static int digit(int num, int digit) {
        int mod = 1;
        for (int i = 1; i <= digit; i++) {
            mod *= 10;
        }
        return num / mod;
    }
}
