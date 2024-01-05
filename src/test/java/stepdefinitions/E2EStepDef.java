package stepdefinitions;

import io.cucumber.java.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import manager.WebDriverManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import pom.HomePOM;
import pom.ItemPOM;
import pom.LoginPOM;

import java.time.Duration;

public class E2EStepDef {

    static WebDriver wd ;
    static LoginPOM login;

    static HomePOM home;

    static ItemPOM item;

    static FluentWait fluentWait;

    @BeforeAll
    public static void before_all() {
        System.out.println("Running BeforeAll");

        wd =WebDriverManager.createDriver();
        login  = new LoginPOM(wd);

        home = new HomePOM(wd);

        item = new ItemPOM(wd);

        fluentWait =  new FluentWait(wd)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
    }

    @Before
    public void beforeScenarioHook() {
        System.out.println("running beforeScenarioHook");
    }

    @BeforeStep
    public void beforeStepHook() {
        System.out.println("running beforeStepHook");
    }

    @Given("User is already loggedIn with credentials as {string} and {string}")
    public void userLogin(String UN, String PW) {
        login.get().fillCredentials(UN, PW).clickLogin();
    }

    @Given("User clicks on Item {string}")
    public void clickItem(String itemName) {
        home.validatePage(fluentWait, itemName).clickItem(itemName);
    }

    @When("User selects the item options size as {string} and color as {string}")
    public void selectItemOptions(String size, String color) {
        item.fillQuantity("2").selectSize("L").selectColor("Green");
    }

    @When("User clicks on AddToCart")
    public void clickAddToCart() throws InterruptedException {
        Thread.sleep(4000);

        item.clickAddToCart();
    }

    @AfterStep
    public void afterStepHook() {
        System.out.println("running afterStepHook");
    }

    @After
    public void afterScenarioHook() {
        System.out.println("running afterScenarioHook");
    }

    @AfterAll
    public static void after_all() {

        System.out.println("Running AfterAll");

        WebDriverManager.closeSession();

         wd = null;
         login = null;

         home = null;

         item = null;

         fluentWait = null;

    }

}
