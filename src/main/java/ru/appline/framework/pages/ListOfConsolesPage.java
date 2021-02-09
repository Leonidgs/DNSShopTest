package ru.appline.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ListOfConsolesPage extends BasePage {

    @FindBy(xpath = "//a[@class='ui-link' and @href='/product/08eaab57cae91b80/igrovaa-konsol-playstation-4-slim-black-1-tb--3-igry/']")
    private WebElement choosePs;

    @FindBy(xpath = "//a[@class='ui-link ui-link_gray_dark']")
    private WebElement pagePsTitle;

    /**
     * Проверка открытия страницы, путём проверки title страницы
     *
     *
     */
    //Проверка попадания на нужную страницу
    public ListOfConsolesPage checkOpenPlayStationsPage() {
        Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                "Консоли PlayStation", pagePsTitle.getText());
        return this;
    }
    //Выбираем консоль
    public OpenPsPage selectPS4() {
        elementToBeClickable(choosePs).click();
        return app.getOpenPsPage();
    }

}
