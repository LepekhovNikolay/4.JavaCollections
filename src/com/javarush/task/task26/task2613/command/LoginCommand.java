package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;

public class LoginCommand implements Command{

    private Locale enUsLocale = new Locale("en", "US");

    private ResourceBundle validCreditCards =
            ResourceBundle.getBundle( CashMachine.class.getPackage().getName() +".resources.verifiedCards", enUsLocale);

    private ResourceBundle res =
            ResourceBundle.getBundle(CashMachine.class.getPackage().getName() +".resources.login_en", enUsLocale);


    private static final String CARD_NUMBER = "123456789012";
    private static final String CARD_PIN = "1234";
    @Override
    public void execute() throws InterruptOperationException {
        while (true) {
            ConsoleHelper.writeMessage(res.getString("before"));
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String processNumberCard = ConsoleHelper.readString();
            String processPinCard = ConsoleHelper.readString();
            String processString = processNumberCard + " " + processPinCard;
            if (validCreditCards.containsKey(processNumberCard) &&
                    validCreditCards.getString(processNumberCard).equals(processPinCard)) {
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), processNumberCard));
                return;
            }
            ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), processNumberCard));
            ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
        }


    }
}
