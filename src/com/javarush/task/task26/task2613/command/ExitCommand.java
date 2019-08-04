package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.ResourceBundleFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class ExitCommand implements Command {

    @Override
    public void execute() throws InterruptOperationException {
        ResourceBundle res = ResourceBundleFactory.getResourceBundleByKey("exit");
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
        String command = ConsoleHelper.readString();
        if (command.equals("y"))
            ConsoleHelper.writeMessage(res.getString("thank.message"));

    }
}
