package com.javarush.task.task39.task3904;

import java.util.HashMap;

/* 
Лестниц
а
*/
/*
public class Solution {
    private static int n = 70;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("Please enter a number of stairs: ");
            String readedLine = reader.readLine();
            if (readedLine.equals("exit"))
                break;
            int n = Integer.parseInt(readedLine);
            long time = System.nanoTime();
            System.out.println("The number of possible ascents for " + n + " steps is: " + numberOfPossibleAscents(n));
            System.out.println("calculate time is " + (System.nanoTime() - time)/1000 + "ms");
        }
    }

    public static long numberOfPossibleAscents(int n) {
        if (n < 0)
            return 0;
        if (n == 0 || n == 1)
            return 1;
        if (n == 2)
            return 2;
        if (n == 3)
            return 4;
        int stair1 = 1;
        int stair2 = 2;
        int stair3 = 4;
        int temp;
        for (int i = 0; i < n-3; i++) {
            temp = stair3;
            stair3 += stair1 + stair2;
            stair1 = stair2;
            stair2 = temp;
        }
        return  stair3;
    }
}
*/
public class Solution {
    private static int n = 70;
    static HashMap<Integer, Long> map = new HashMap<>();
    static {
        map.put(0, 1L);
        map.put(1, 1L);
        map.put(2, 2L);
        map.put(3, 4L);
    }

    public static void main(String[] args) {
        System.out.println("Number of possible runups for " + n + " stairs is: " + numberOfPossibleAscents(n));
    }

    public static long numberOfPossibleAscents(int n) {
        if (n < 0)
            return 0;

        if (map.containsKey(n)) {
            return map.get(n);
        } else {
            long fibonacciValue = ((numberOfPossibleAscents(n - 3) + numberOfPossibleAscents(n - 2))  + numberOfPossibleAscents(n - 1));
            map.put(n, fibonacciValue);
            return fibonacciValue;
        }
    }
}