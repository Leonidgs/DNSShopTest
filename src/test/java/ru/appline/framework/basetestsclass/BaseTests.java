package ru.appline.framework.basetestsclass;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import ru.appline.framework.managers.InitManager;
import ru.appline.framework.managers.PageManager;
import ru.appline.framework.managers.TestPropManager;

import static ru.appline.framework.managers.DriverManager.getDriver;
import static ru.appline.framework.utils.PropConst.APP_URL;

public class BaseTests {

    /**
     * Менеджер страничек
     * @see PageManager#getPageManager()
     */
    protected PageManager app = PageManager.getPageManager();

    @BeforeClass
    public static void beforeAll() {
        InitManager.initFramework();
    }

    @Before
    public void beforeEach() {
        getDriver().get(TestPropManager.getTestPropManager().getProperty(APP_URL));
    }

    @AfterClass
    public static void afterAll() {
        InitManager.quitFramework();
    }
}
