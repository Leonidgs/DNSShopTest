package ru.appline.framework.managers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static ru.appline.framework.managers.DriverManager.getDriver;
import static ru.appline.framework.managers.DriverManager.quitDriver;
import static ru.appline.framework.utils.PropConst.*;

public class InitManager {
    /**
     * Менеджер properties
     *
     * @see TestPropManager#getTestPropManager()
     */
    private static final TestPropManager props = TestPropManager.getTestPropManager();

    /**
     * Инициализация framework и запуск браузера со страницей приложения
     *
     * @see DriverManager#getDriver()
     * @see TestPropManager#getProperty(String)
     * @see ru.appline.framework.utils.PropConst
     */
    public static void initFramework() {
        getDriver().manage().window().maximize();
        new WebDriverWait(getDriver(), 1).until(
                webDriver -> ((JavascriptExecutor) webDriver).
                        executeScript("return document.readyState").equals("complete"));
        getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(props.getProperty(IMPLICITLY_WAIT)), TimeUnit.SECONDS);
        //getDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(props.getProperty(PAGE_LOAD_TIMEOUT)), TimeUnit.SECONDS);

    }

    /**
     * Завершения работы framework - гасит драйвер и закрывает сессию с браузером
     *
     * @see DriverManager#quitDriver()
     */
    public static void quitFramework() {
        quitDriver();
    }
}
