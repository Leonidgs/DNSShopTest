package ru.appline.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BasketPage extends BasePage{

    //Проверка гарантии
    @FindBy(xpath = "//span[contains(@class, 'base-ui-radio-button__icon')]")
    private List<WebElement> guaranteeSelect;
    //Стоимость корзины
    @FindBy(xpath = "//span[@class='price__current']")
    private List<WebElement> checkCostProducts;
    //Элементы товаров в корзине
    @FindBy(xpath = "//div[@data-cart-product-id]")
    private List<WebElement> listCardProduct;
    //Наименования товаров в корзине
    @FindBy(xpath = "//a[@class='cart-items__product-name-link']")
    private List<WebElement> listOfProductsInBasket;
    //Стоимость корзины
    @FindBy(xpath = "//span[@class='cart-link__price']")
    private WebElement costBasketAfterDlt;
    //Плюсик для добавления товара
    @FindBy(xpath = "//i[@class='count-buttons__icon-plus']")
    private WebElement plusForEnhanceProduct;
    //Счетчик товаров в корзине
    @FindBy(xpath = "//input[@class='count-buttons__input']")
    private WebElement productCounter;
    //Стоимость консолей х3
    @FindBy(xpath = "//div[@class='cart-items__product-block-amount']")
    private WebElement consoleCost;
    //Второй клик по плюсику
    @FindBy(xpath = "//i[@class='count-buttons__icon-plus']")
    private WebElement secondClickOnPlus;
    //Проверка стоимости 3-ёх консолей
    @FindBy(xpath = "//div[@class='cart-items__product-block-amount']//span[@class='price__current']")
    private WebElement checkCost3PS;
    //Вернуть удалённый товар
    @FindBy(xpath = "//span[contains(@class, 'group')]//span[contains(@class, 'restore')]")
    private WebElement returnDlt;
    //Сумма корзины после всех покупок с гарантией
    @FindBy(xpath = "//div[@class='total-amount-block total-amount__info-block']//span[@class='price__current']")
    private WebElement totalAmount;

    //Проверка гарантии
    public BasketPage checkGuaranteeType() {
        for (WebElement elem : guaranteeSelect) {
            if (elem.getText().equalsIgnoreCase("+ 24 мес.")) {
                elem.getAttribute("isConnected").equalsIgnoreCase("true");
                System.out.println(elem.getText());
                return this;
            }
        }
        Assert.fail("Выбрана неверная гарантия");
        return this;
    }
    //Сравниваем цену всех товаров в корзине
    public BasketPage checkCostOfProducts() {
        ArrayList<Integer> list = new ArrayList();
        ArrayList<Integer> list1 = new ArrayList();
        list1.add(OpenPsPage.beforeCost);
        list1.add(ChooseGamePage.totalCost);
        list1.add(ChooseGamePage.gameCost);

        for (WebElement elem: checkCostProducts) {
            //System.out.println(elem.getText().substring(0,6).replaceAll(" ", ""));
            list.add(Integer.parseInt(elem.getText().substring(0,6).replaceAll(" ", "")));
        }
        Collections.sort(list);
        Collections.sort(list1);
        if (list.equals(list1)) {
            return this;
        }
        //System.out.println(list);
        //System.out.println(list1);
        Assert.fail("Некорректная стоимость товаров в корзине");
        return this;
    }
    //Удаляем продукт - игру
      public BasketPage dltProductInCartByName(String nameProduct) {
        WebElement name;
        WebElement dltElem;
        for (WebElement elem : listCardProduct) {
            name = elem.findElement(By.xpath(".//div[@class='cart-items__product-name']/a"));
            if (name.getText().contains(nameProduct)) {
                dltElem = elem.findElement(By.xpath(".//button[.='Удалить']"));
                dltElem.click();
                return this;
            }
        }
        Assert.fail("Такой продукт с наименованием " + nameProduct + " не найден в корзине");
        return this;
    }
    //Проверям что игра удалилась
    public BasketPage checkDltGameFromBasket(String productName) {
        for(WebElement elem : listOfProductsInBasket) {
            if (!elem.getText().equalsIgnoreCase(productName)) {
                return this;
            }
        }
        Assert.fail("Продукт с наименованием " + productName + "не был удалён" );
        return this;
    }
    //Проверяем стоимость корзины после удаления игры
    public BasketPage checkCostBasketAfterDlt(String costValue) {
        attributeToBe(costBasketAfterDlt, "textContent", costValue);
        if (costBasketAfterDlt.getText().substring(0,6).replaceAll(" ", "").
                equalsIgnoreCase(OpenPsPage.afterCost.toString())) {
            return this;
        }
        Assert.fail("Некорректная стоимость товаров в корзине");
        return this;
    }
    //Увеличиваем число товара в корзине
    public BasketPage enhanceQuantityProduct(){
        plusForEnhanceProduct.click();
        attributeToBe(productCounter, "value", "2");
        secondClickOnPlus.click();
        attributeToBe(productCounter, "value", "3");
        System.out.println(productCounter.getAttribute("value"));
        return this;
    }
    //Проверяем стоимость 3-ёх консолей
    public BasketPage costConsole() {
        System.out.println(checkCost3PS.getText());
        return this;
    }
    //Возвращаем удалённый продукт
    public BasketPage returnDltProduct(String nameProduct) {
        returnDlt.click();
        attributeToBe(totalAmount, "outerText", "108 796 ₽");
        WebElement name;
        for (WebElement elem : listCardProduct) {
            name = elem.findElement(By.xpath(".//div[@class='cart-items__product-name']/a"));
            if (name.getText().contains(nameProduct)) {
                return this;
            }
        }
        Assert.fail("Продукт с наименованием " + nameProduct + "не возвращен в корзину" );
        return this;
    }
    //Проверяем финальную стоимость корзины
    public BasketPage totalAmountBasket(String basketCostAtTheLast) {
        if (totalAmount.getText().equalsIgnoreCase(basketCostAtTheLast)) {
            return this;
        }
        Assert.fail("Стоимость корзины не соотвествует ожидаемой = " + basketCostAtTheLast);
        return this;
    }
}
