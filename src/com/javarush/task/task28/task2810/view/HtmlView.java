package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.lang.model.util.Elements;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class HtmlView implements View {
    private Controller controller;
    private List<Vacancy> vacancies;
    private final String filePath = String.format("./4.JavaCollections/src/%s/vacancies.html", this.getClass().getPackage().getName().replace(".", "/"));

    @Override
    public void update(List<Vacancy> vacancies) {
        try {
            String updateFileContent = getUpdatedFileContent(vacancies);
            updateFile(updateFileContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        Document document = null;
        try {
            document = getDocument();
            Element example = document.getElementsByClass("template").first();
            Element element = example.clone();
            element.removeAttr("style");
            element.removeClass("template");
            document.select("tr[class=vacancy]").remove().not("tr[class=vacancy template]");
            System.out.println(1);
            for (Vacancy vacancy : vacancies) {
                Element elementClone = element.clone();
                element.getElementsByClass("title").first().text(vacancy.getTitle());
                element.getElementsByClass("title").first().attr("href", vacancy.getUrl());
                element.getElementsByClass("city").first().text(vacancy.getCity());
                element.getElementsByClass("companyName").first().text(vacancy.getCompanyName());
                element.getElementsByClass("salary").first().text(vacancy.getSalary());
                example.before(element.outerHtml());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Some exception occurred";
        }

        return document.html();
    }

    protected Document getDocument() throws IOException{
        return Jsoup.parse(new File(filePath), "UTF-8");
    }

    private void updateFile(String string){
        try (FileWriter fileWriter = new FileWriter(new File(filePath))) {
            fileWriter.write(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("java Dnepropetrovsk");
    }
}
