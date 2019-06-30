package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoikrugStrategy implements Strategy {
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36";
    private static final int TIMEOUT = 3 * 1000;

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> resultList = new ArrayList<>();
        int pageNumber = 0;
        try {
//            Document document = Jsoup.connect(String.format(URL_FORMAT, "Kiev", 1))
            while (true) {
                Document document = getDocument(searchString, pageNumber++);
                String html = document.html();
                if (document == null)
                    break;
                String moikrugHtml = document.html();
                Elements elements = document.getElementsByClass("job");
                if (elements.size() == 0)
                    break;
                for (Element element : elements) {
                    Vacancy vacancy = new Vacancy();
                    //set Title of vacancy
                    vacancy.setTitle(element.getElementsByClass("title").first().text());
                    //set Salary of vacancy
                    String salary = element.getElementsByClass("salary").first().text();
                    vacancy.setSalary(salary.length() == 0 ? "" : salary);
                    //set City of vacancy
                    Elements cities =  element.getElementsByClass("location");
                    vacancy.setCity(cities.size() == 0 ? "" : cities.first().text());
                    //set Owner of vacancy
                    vacancy.setCompanyName(element.getElementsByClass("company_name").first().text());
                    //set siteName of vacancy
                    vacancy.setSiteName(URL_FORMAT);
                    //set URL of vacancy
                    vacancy.setUrl("https://moikrug.ru/vacancies/" + element.id().substring(4));
                    //добавление вакансии в окончательный список
                    resultList.add(vacancy);
                }
            }
        } catch (IOException e) {
//            e.printStackTrace();
            System.err.println("OШИБКА, pageNumber = " + pageNumber);
        }
        return resultList;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        Document doc = null;
        try {
            doc = Jsoup.connect(String.format(URL_FORMAT,searchString, page))
                    .userAgent(USER_AGENT)
                    .referrer("no-referrer-when-downgrade")
                    .timeout(TIMEOUT)
                    .get();
        } catch (IOException e) {
//            e.printStackTrace();
        }
        return doc;
    }
}
