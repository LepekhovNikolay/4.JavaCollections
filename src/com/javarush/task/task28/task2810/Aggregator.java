package com.javarush.task.task28.task2810;

import com.javarush.task.task28.task2810.model.*;
import com.javarush.task.task28.task2810.view.HtmlView;

public class Aggregator {

    public static void main(String[] args) {
        HtmlView htmlView = new HtmlView();
        Strategy hhStrategy = new HHStrategy();
        Strategy moikrugStrategy = new MoikrugStrategy();
        Provider hhProvider = new Provider(hhStrategy);
        Provider moikrugProvider = new Provider(moikrugStrategy);
        Model model = new Model(htmlView, hhProvider, moikrugProvider);
        Controller controller = new Controller(model);
        htmlView.setController(controller);
        htmlView.userCitySelectEmulationMethod();
    }
}
