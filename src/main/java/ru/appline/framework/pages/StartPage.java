package ru.appline.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class StartPage extends BasePage {

    @FindBy(xpath = "//ul[contains(@class,'kitt-top-menu__list')]//a[@aria-label and @role='button']")
    private List<WebElement> menuBaseList;

    @FindBy(xpath = "//*[@placeholder='Поиск по сайту']")
    private WebElement search;

    @FindBy(xpath = "//*[@class='ui-link presearch__suggest']")
    private List<WebElement> listofThing;

    //Search on DNS
    public StartPage searchMeth(String thing) {
        if (elementToBeClickable(search).equals(search)) {
            search.sendKeys(thing);
            return this;
        }
        Assert.fail("Меню '" + "' не было найдено на стартовой странице!");
        return this;
    }
    //Выбираем вещь
    public ListOfConsolesPage selectThing(String nameThing) {

        for (WebElement menuItem : listofThing) {
            if (menuItem.getAttribute("outerText").equalsIgnoreCase(nameThing)) {
                elementToBeVisible(menuItem);
                elementToBeClickable(menuItem).click();
                return app.getInsurancePage().checkOpenPlayStationsPage();
            }
            System.out.println(menuItem.getAttribute("outerText"));
        }
        Assert.fail(nameThing + "' не была найдена в поиске!");
        return app.getInsurancePage();
    }

}
