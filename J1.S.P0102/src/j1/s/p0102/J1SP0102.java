/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.s.p0102;

import java.util.Scanner;

public class J1SP0102 {

    public static int checkYear(int a) {
        // kiem tra nam nhuan tra ve 0 la nam khong nhuan, tra ve 1 la nam nhuan
        if (a % 400 == 0) {
            return 1;
        }
        if (a % 4 == 0 && a % 100 != 0) {
            return 1;
        }
        return 0;
    }

    public static int check(String str) {
        //retrurn ve cac so tu nhien -1(dinh dang khong hop le), 1(gia tri nam khong hop le), 2(gia tri thang khong hop le), 3(gia tri ngay khong hop le)
        String[] arr = new String[3];
        char[] b = new char[str.length()];
        int count = 0;
        int a;
        if (!(str.contains("/"))) {
            return -1;
        } else {
            for (int i = 0; i < str.length(); i++) {
                b = str.toCharArray();
                if (b[i] == '/') {
                    count += 1;
                }
            }
            if (count != 2) {
                return -1;
            }
            arr = str.split("/");
            try {
                a = Integer.parseInt(arr[2]);
            } catch (NumberFormatException nfe) {
                return 1;
            }
            try {
                a = Integer.parseInt(arr[1]);
            } catch (NumberFormatException nfe) {
                return 2;
            }
            try {
                a = Integer.parseInt(arr[0]);
            } catch (NumberFormatException nfe) {
                return 3;
            }
            if (Integer.parseInt(arr[2]) <= 0 || Integer.parseInt(arr[2]) > 9999) {
                return 1;
            }
            if (Integer.parseInt(arr[1]) <= 0 || Integer.parseInt(arr[1]) > 12) {
                return 2;
            }
            if (Integer.parseInt(arr[0]) <= 0 || Integer.parseInt(arr[0]) > 31) {
                return 3;
            }
            if (Integer.parseInt(arr[1]) == 2 && Integer.parseInt(arr[0]) > 29) {
                return 3;
            }
            if (checkYear(Integer.parseInt(arr[2])) == 0 && Integer.parseInt(arr[1]) == 2 && Integer.parseInt(arr[0]) > 28) {
                return 3;
            }
        }
        return 0;
    }

    public static String determineDate(String str) {
        //tinh toan va tra ve kieu int (0-6) voi thu tu tuong ung (CN-Mon-...-Sat)
        String[] arr = str.split("/");
        String s = null;
        int day, month, year, n;
        day = Integer.parseInt(arr[0]);
        month = Integer.parseInt(arr[1]);
        year = Integer.parseInt(arr[2]);
        if (month < 3) {
            month = month + 12;
            year = year - 1;
        }
        n = (day + 2 * month + (3 * (month + 1)) / 5 + year + year / 4) % 7;
        switch (n) {
            case 0:
                s = "Sunday";
                break;
            case 1:
                s = "Monday";
                break;
            case 2:
                s = "Tuesday";
                break;
            case 3:
                s = "Wednesday";
                break;
            case 4:
                s = "Thursday";
                break;
            case 5:
                s = "Friday";
                break;
            case 6:
                s = "Saturday";
                break;
            default:
                break;
        }
        return s;
    }

    public static void main(String[] args) {
        //trinh tu chuong trinh:
        //b1. nhap vao date
        //b2. kiem tra input
        //b3. tinh toan
        //b4. tra ve ket qua
        Scanner s = new Scanner(System.in);
        String str, a;
        //nhap vao date va kiem tra
        do {
            System.out.println("Enter the date with format [dd/mm/yyy]:");
            str = s.nextLine();
            if (check(str) == -1) {
                System.out.println("Wrong format of date!");
            }
            if (check(str) == 1) {
                System.out.println("value of year is invalid!");
            }
            if (check(str) == 2) {
                System.out.println("value of month is invalid!");
            }
            if (check(str) == 3) {
                System.out.println("value of day is invalid!");
            }
        } while (check(str) != 0);
        //tinh toan va tra ve
        a = determineDate(str);
        System.out.println("Your day is " + a);
    }
}
