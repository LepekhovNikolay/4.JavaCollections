package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));


    private static ResourceBundle res =
            ResourceBundle.getBundle( CashMachine.class.getPackage().getName() +".resources.common_en", Locale.US);

    public static void setRes(ResourceBundle res) {
        ConsoleHelper.res = res;
    }

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException{
        try {
            String command = bis.readLine();
            if (command != null && command.toUpperCase().equals("EXIT")) {
                writeMessage(res.getString("the.end"));
                throw new InterruptOperationException();
            }
            return command;
        } catch (IOException e) {
//            e.printStackTrace();
        }
        return null;
    }



    public static String askCurrencyCode() throws InterruptOperationException{
        while (true) {
            writeMessage(res.getString("choose.currency.code"));

            String currencyCode = readString();
            if (currencyCode != null && currencyCode.length() == 3)
                return currencyCode.toUpperCase();
            writeMessage(res.getString("invalid.data"));
        }
    }


    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException{

        while (true) {
            writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));

            String nomAndCount = readString();
            if (!nomAndCount.matches("^[1-9]\\d* [1-9]\\d*"))
                writeMessage(res.getString("invalid.data"));
            else
                return nomAndCount.split(" ");
        }

    }

    public static Operation askOperation() throws InterruptOperationException{
        while (true) {
            try {
                writeMessage(res.getString("choose.operation"));
                Operation op = Operation.getAllowableOperationByOrdinal(Integer.parseInt(readString()));
                switch(op) {
                    case INFO:
                        writeMessage(res.getString("operation.INFO"));
                        break;
                    case DEPOSIT:
                        writeMessage(res.getString("operation.DEPOSIT"));
                        break;
                    case WITHDRAW:
                        writeMessage(res.getString("operation.WITHDRAW"));
                        break;
                    case EXIT:
                        writeMessage(res.getString("operation.EXIT"));

                }
                return op;
            } catch (IllegalArgumentException e) {
                writeMessage(res.getString("invalid.data"));
            }
        }
    }

    public static void printExitMessage() {
        writeMessage(res.getString("the.end"));
    }
}
