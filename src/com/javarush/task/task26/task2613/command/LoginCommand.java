package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.Language;
import com.javarush.task.task26.task2613.ResourceBundleFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command{


    private ResourceBundle res =
            ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en", CashMachine.getCashMashineLocale());


    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before.multilanguage"));
        String language = ConsoleHelper.readString();
        if (language.equals("ru"))
            CashMachine.setCashMashineLocale(Language.getLocale(Language.RUSSIAN));
        else
            CashMachine.setCashMashineLocale(Language.getLocale(Language.ENGLISH));
        ResourceBundle validCreditCards = ResourceBundleFactory.getResourceBundleByKey("verifiedCards");
        res = ResourceBundleFactory.getResourceBundleByKey("login");
        ConsoleHelper.setRes(ResourceBundleFactory.getResourceBundleByKey("common"));
        while (true) {
            ConsoleHelper.writeMessage(res.getString("before"));
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String processNumberCard = ConsoleHelper.readString();
            String processPinCard = ConsoleHelper.readString();
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
