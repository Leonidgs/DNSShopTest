package ru.appline.framework.managers;

import ru.appline.framework.pages.*;

public class PageManager {

    /**
     * Менеджер страничек
     */
    private static PageManager pageManager;
    /**
     * Стартовая страничка
     */
    private StartPage startPage;

    /**
     * Страничка страхование путешественников
     */
    private ListOfConsolesPage listOfConsolesPage;

    /**
     * Конструктор специально был объявлен как private (singleton паттерн)
     *
     * @see PageManager#getPageManager()
     */
    //Страничка просмотра консоли
    private OpenPsPage psPage;
    //Страничка для работы с игрой
    private ChooseGamePage gamePage;
    //Для работы с корзиной в финале
    private BasketPage basketPage;

    private PageManager() {
    }

    /**
     * Ленивая инициализация PageManager
     *
     * @return PageManager
     */
    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    /**
     * Ленивая инициализация {@link ru.appline.framework.pages.StartPage}
     *
     * @return StartPage
     */
    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new StartPage();
        }
        return startPage;
    }

    /**
     * Ленивая инициализация {@link ListOfConsolesPage}
     *
     * @return ListOfConsolesPage
     */
    public ListOfConsolesPage getInsurancePage() {
        if (listOfConsolesPage == null) {
            listOfConsolesPage = new ListOfConsolesPage();
        }
        return listOfConsolesPage;
    }

    public OpenPsPage getOpenPsPage() {
        if (psPage == null) {
            psPage = new OpenPsPage();
        }
        return psPage;
    }

    public ChooseGamePage getOpenGamePage() {
        if (gamePage == null) {
            gamePage = new ChooseGamePage();
        }
        return gamePage;
    }
    public BasketPage getBasketPage() {
        if (basketPage == null) {
            basketPage = new BasketPage();
        }
        return basketPage;
    }
}
