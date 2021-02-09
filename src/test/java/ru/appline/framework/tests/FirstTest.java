package ru.appline.framework.tests;

import org.junit.Test;
import ru.appline.framework.basetestsclass.BaseTests;

public class FirstTest extends BaseTests {

    @Test
    public void startTest() {
        app.getStartPage()
                .searchMeth("playstation 4")
                .selectThing("playstation 4")
                .selectPS4()
                .clickGuarantee()
                .selectGuarantee("2 года")
                .buyProduct()
                .searchGame("Detroit")
                .rememberCost()
                .buyProduct()
                .checkCostBacket()
                .clickOnBasket()
                .checkGuaranteeType()
                .checkCostOfProducts()
                .dltProductInCartByName("Игра Detroit: Стать человеком (PS4)")
                .checkDltGameFromBasket("Игра Detroit: Стать человеком (PS4)")
                .checkCostBasketAfterDlt("35 399")
                .enhanceQuantityProduct()
                .costConsole()
                .returnDltProduct("Игра Detroit: Стать человеком (PS4)")
                .totalAmountBasket("108 796 ₽");
    }
}
