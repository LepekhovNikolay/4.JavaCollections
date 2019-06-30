package com.javarush.task.task28.task2810;

import com.javarush.task.task28.task2810.model.*;
import com.javarush.task.task28.task2810.view.HtmlView;
/**
 * Дополнительные задания:
 * Aggregator (17)
 * Что можешь сделать самостоятельно (тестов на этот пункт нет):
 * 1. добавить еще 100500 других сайтов для агрегирования вакансий.
 * Нужно всего лишь создать стратегию, а потом добавить в модель провайдер с этой стратегией.
 * 2. отсортировать все вакансии, например, по дате создания (придется распарсить дату в полученном html).
 * 3. создать свою вью, например, на свинге. Подменить в main HtmlView на SwingView.
 * Подключать либы ты уже умеешь. Сделать, чтоб запрос приходил с swing-формы.
 * 4. собрать приложение в war-ник, развернуть Томкат, задеплоить приложение туда. Сделать, чтоб запрос приходил с браузера.
 *
 * Твои достижения:
 * 1. разобрался с паттерном Strategy,
 * 2. разобрался с самым популярным паттерном MVC (его очень часто спрашивают на собеседовании),
 * 3. научился парсить HTML,
 * 4. получил опыт работы с библиотекой Jsoup,
 * 5. научился подключать внешние либы в IDEA,
 * 6. получил опыт работы с внешними библиотеками,
 * 7. написал крутую архитектуру,
 * 8. стал больше знать и уметь,
 * 9. увидел, как раскладывать задачу на подзадачи,
 * 10. продвинулся на шаг ближе к работе джава программистом.

 */
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
