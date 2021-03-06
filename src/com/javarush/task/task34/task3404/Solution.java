package com.javarush.task.task34.task3404;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

/*
Рекурсия для мат. выражения
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recurse("sin(2*(-5+1.5*4)+28)", 0); //expected output 0.5 6
//        solution.recurse("10 * sin(90)", 0);
    }

    public void recurse(final String expression, int countOperation) {
        //implement
        String expressionWOSpace = expression.replaceAll(" ", "");
        // Подсчёт знаков`
        if (countOperation == 0){
            String test = expressionWOSpace;
            while (test.contains("sin") || test.contains("cos") || test.contains("tan")){
                test = test.replace("sin","s");
                test = test.replace("cos","c");
                test = test.replace("tan","t");
            }
            for (char d : test.toCharArray()){
                switch (d){
                    case 'c':
                        countOperation++;
                        break;
                    case 's':
                        countOperation++;
                        break;
                    case 't':
                        countOperation++;
                        break;
                    case '*':
                        countOperation++;
                        break;
                    case '/':
                        countOperation++;
                        break;
                    case '^':
                        countOperation++;
                        break;
                    case '+':
                        countOperation++;
                        break;
                    case '-':
                        countOperation++;
                        break;
                }
            }
        }


        List<String> commonArr = new ArrayList<>();
        String s = expressionWOSpace;

        String[] numbers;

        if (expressionWOSpace.contains("(") && expressionWOSpace.contains(")")) {
            s = expressionWOSpace.substring(expressionWOSpace.lastIndexOf("(") + 1);
            s = s.substring(0, s.indexOf(")"));
        }
        String s2 = s;

        while(s.contains("sin") || s.contains("cos") || s.contains("tan")) {
            if (s.contains("sin-")) {
                s = s.replace("sin-","s-");
            } else if (s.contains("sin")) {
                s = s.replace("sin","s");
            }
            if (s.contains("cos-")) {
                s = s.replace("cos-","c-");
            } else if (s.contains("cos")) {
                s = s.replace("cos", "c");
            }
            if (s.contains("tan-")) {
                s = s.replace("tan-","t-");
            } else if (s.contains("tan")) {
                s = s.replace("tan", "t");
            }
        }
        String s1 = s;

        //"разбиение" строки на элементы (числа и мат. действия) и помещение их в commonArr
        s = s.startsWith("-") ? s.substring(1,s.length()) : s;
        numbers = s.split("[s,c,t/^,/*,//,+,/-]");
        int i = 0;
        for (Character ch : s1.toCharArray()) {
            if (ch == '^' || ch == '*' || ch == '/' || ch == '+' || ch == '-') {
                if (s1.startsWith("-")) {
                    commonArr.add(String.valueOf(ch));
                    commonArr.add(numbers[i]);
                } else {
                    commonArr.add(numbers[i]);
                    commonArr.add(String.valueOf(ch));
                }
                i++;
            }
            if (ch == 's' || ch == 'c' || ch == 't'){
                commonArr.add(String.valueOf(ch));
                commonArr.add(numbers[i]);
                i++;
            }

            if (i == numbers.length - 1 && !s1.startsWith("-")) {
                commonArr.add(numbers[i]);
                break;
            }
        }

        // для случая, когда подряю идут два знака
        if (commonArr.contains("")) {
            for (int j = 0; j < commonArr.size(); j++) {
                if (commonArr.get(j).equals("")) {
                    if (commonArr.get(j+1).equals("-")){
                        double r = - Double.valueOf(commonArr.get(j+2));
                        commonArr.set(j+1,String.valueOf(r));
                        commonArr.remove(j+2);
                    }
                    commonArr.remove(j);
                    j = 0;
                }
            }
        }


        // вычисление sin, cos, tan
        if (commonArr.contains("s") || commonArr.contains("c") || commonArr.contains("t")){
            double x;
            for (int j = 0; j < commonArr.size(); j++) {
                switch (commonArr.get(j)){
                    case "s":
                        if (j > 0 && commonArr.get(j-1).equals("-")) {
                            x = -Math.sin(Math.toRadians(Double.valueOf(commonArr.get(j + 1))));
                        }else x = Math.sin(Math.toRadians(Double.valueOf(commonArr.get(j + 1))));
                        commonArr.set(j,String.valueOf(x));
                        commonArr.remove(j+1);
                        commonArr.set(j,String.valueOf(x));
                        if (j > 0 && commonArr.get(j-1).equals("-")) commonArr.remove(j-1);
                        break;
                    case "c":
                        if (j > 0 && commonArr.get(j-1).equals("-")) {
                            x = -Math.cos(Math.toRadians(Double.valueOf(commonArr.get(j + 1))));
                        }else x = Math.cos(Math.toRadians(Double.valueOf(commonArr.get(j + 1))));
                        commonArr.set(j,String.valueOf(x));
                        commonArr.remove(j+1);
                        commonArr.set(j,String.valueOf(x));
                        if (j > 0 && commonArr.get(j-1).equals("-")) commonArr.remove(j-1);
                        break;
                    case "t":
                        if (j > 0 && commonArr.get(j-1).equals("-")) {
                            x = -Math.tan(Math.toRadians(Double.valueOf(commonArr.get(j + 1))));
                        }else x = Math.tan(Math.toRadians(Double.valueOf(commonArr.get(j + 1))));
                        commonArr.set(j,String.valueOf(x));
                        commonArr.remove(j+1);
                        commonArr.set(j,String.valueOf(x));
                        if (j > 0 && commonArr.get(j-1).equals("-")) commonArr.remove(j-1);
                        break;
                }
            }
        }

        // возведение в степень
        if (commonArr.contains("^")){
            for (int j = 0; j < commonArr.size(); j++) {
                if (commonArr.get(j).equals("^")) {
                    commonArr.set(j - 1, String.valueOf(Math.pow(Double.valueOf(commonArr.get(j - 1)),Double.valueOf(commonArr.get(j + 1)))));
                    commonArr.remove(j + 1);
                    commonArr.remove(j);
                    j = 0;
                }
            }
        }

        // перемножение и деление элементов commonArr
        if (commonArr.contains("*") || commonArr.contains("/")) {
            for (int j = 0; j < commonArr.size(); j++) {
                double z;
                switch (commonArr.get(j)) {
                    case "*":
                        z = Double.valueOf(commonArr.get(j - 1)) * Double.valueOf(commonArr.get(j + 1));
                        commonArr.set(j - 1, String.valueOf(z));
                        commonArr.remove(j + 1);
                        commonArr.remove(j);
                        j = 0;
                        break;
                    case "/":
                        z = Double.valueOf(commonArr.get(j - 1)) / Double.valueOf(commonArr.get(j + 1));
                        commonArr.set(j - 1, String.valueOf(z));
                        commonArr.remove(j + 1);
                        commonArr.remove(j);
                        j = 0;
                        break;
                }
            }
        }

        // Расстановка "минусов"
        if (commonArr.contains("-") || commonArr.contains("+")) {
            for (int j = 0; j < commonArr.size(); j++) {
                double x;
                switch (commonArr.get(j)) {
                    case "-":
                        x = Double.valueOf(commonArr.get(j + 1)) * (-1);
                        commonArr.set(j + 1, String.valueOf(x));
                        commonArr.remove(j);
                        j = 0;
                        break;
                    case "+":
                        x = Double.valueOf(commonArr.get(j + 1));
                        commonArr.set(j + 1, String.valueOf(x));
                        commonArr.remove(j);
                        j = 0;
                        break;
                }
            }
        }

        double res = 0.0;

        // Сложение всех элементов commonArr
        for (String numb : commonArr) res += Double.valueOf(numb);

        if (expressionWOSpace.contains("(") && expressionWOSpace.contains(")")) {
            String jp = expressionWOSpace.replace("(" + s2 + ")", String.valueOf(res));
            recurse(jp, countOperation);
        } else {
            String k = new BigDecimal(res).setScale(2, RoundingMode.HALF_UP).toString();
            k = new DecimalFormat("#.##").format(Double.valueOf(k));
            System.out.print(k.replace(",", ".") + " " + countOperation);
            return;
        }
    }

    public Solution() {
        //don't delete
    }
}