package ru.appline.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class OpenPsPage extends BasePage{

    public static Integer beforeCost = 0;
    public static Integer afterCost = 0;

    @FindBy(xpath = "//a[@data-role='credit-calculator-open-link']")
    private WebElement getCost;

    @FindBy(xpath = "//select[@class='ui-input-select product-warranty__select']")
    private WebElement guarantee;

    //span[@class='product-card-price__current product-card-price__current_active']

    @FindBy(xpath = "//span[@class='product-card-price__current product-card-price__current_active']")
    private WebElement costAfterGuaranty2;

    @FindBy(xpath = "//option[@class='ui-input-select__option']")
    private List<WebElement> selectGuarantee;
    //option[@value='1']

    //button[text()='Купить']
    @FindBy(xpath = "//button[text()='Купить']")
    private WebElement clickBuy;

    //Окно поиска
    @FindBy(xpath = "//*[@placeholder='Поиск по сайту']")
    private WebElement search;

    //Запомнить цену до гарантии
    public String rememberCost() {
        return getCost.getAttribute("data-price");
    }

    //Кликнуть по полю выбора гарантий
    //select[@class='ui-input-select product-warranty__select']
    public OpenPsPage clickGuarantee() {
        System.out.println("Стоимость до гарантии " + rememberCost());
        beforeCost = Integer.parseInt(rememberCost());
        elementToBeClickable(guarantee).click();
        return this;
    }

    //Выбираем гарантию
    public OpenPsPage selectGuarantee(String periodGyarantee) {
        for (WebElement menuItem : selectGuarantee) {
            if (menuItem.getText().trim().equalsIgnoreCase(periodGyarantee)) {
                elementToBeClickable(menuItem).click();
                rememberCostAfterGuarante2();
                return this;
            }
        }
        Assert.fail("Меню '" + periodGyarantee + "' не было найдено в списке гарантий!");
        return this;
    }


    //Получаем новую цену с гарантией
    public String rememberCostAfterGuarante2() {
        //System.out.println("Стоимость после выбора гарантии " + costAfterGuaranty2.getText()
         //       .substring(0,7).replaceAll(" ", ""));
        afterCost = Integer.parseInt(costAfterGuaranty2.getText().substring(0,6).replaceAll(" ", ""));
        return costAfterGuaranty2.getAttribute("textContent");
    }


    //Клик купить
    public OpenPsPage buyProduct() {
        elementToBeClickable(clickBuy).click();
        return this;
    }

    //Выбрать в поиске игру
    public ChooseGamePage searchGame(String thing) {
        if (elementToBeClickable(search).equals(search)) {
            search.sendKeys(thing + "\n");
            return app.getOpenGamePage();
        }
        Assert.fail(thing + " Игра не была найдена");
        return app.getOpenGamePage();
    }

}
