import manager.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pom.ExcelManager;
import pom.HomePOM;
import pom.ItemPOM;
import pom.LoginPOM;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class LoginTest {

    WebDriverWait wait;
    ChromeOptions chromeOptions;
    FirefoxOptions firefoxOptions;

    WebDriver wd;
    FluentWait fluentWait;

    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void BC(String browserName) throws MalformedURLException {
        System.out.println("Value of Browser is: "+ browserName);

        switch (browserName) {
            case "chrome": {
                chromeOptions = new ChromeOptions();
                wd = new RemoteWebDriver( new URL("http://localhost:4444/"), chromeOptions);
                break;
            }
            case "firefox": {
                firefoxOptions = new FirefoxOptions();
                wd = new RemoteWebDriver( new URL("http://localhost:4444/"), firefoxOptions);
                break;
            }
            default: {
                chromeOptions = new ChromeOptions();
                wd = new RemoteWebDriver( new URL("http://localhost:4444/"), chromeOptions);
                break;
            }
        }



    }


    @BeforeMethod(alwaysRun = true)
    public void preMethodsteps() throws MalformedURLException {

       // wd.get("https://demo.evershop.io/account/login");

    }


    @DataProvider(name = "LoginData")
    public Object[][] generateData() throws IOException {
        return ExcelManager.getData();

    }

    @Test(groups = {"Sanity"}, priority = 10, dataProvider = "LoginData")
    public void successLogin(String UN, String PW) throws MalformedURLException {
       // fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));

       // wd1 = new RemoteWebDriver( new URL("http://localhost:4444/"), chromeOptions);
        WebDriver wd1 = WebDriverManager.createDriver();
        wd1.get("https://demo.evershop.io/account/login");
        wd1.findElement(By.name("email")).sendKeys(UN);
        wd1.findElement(By.name("password")).sendKeys(PW);
        wd1.findElement(By.xpath("//button[@type='submit']")).click();

    }

    @Test(priority = 15, enabled = false)
    public void unsuccessLogin() throws MalformedURLException {

      //  fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));

       // wd2 = new RemoteWebDriver( new URL("http://localhost:4444/"), chromeOptions);
        WebDriver wd2 =  WebDriverManager.createDriver();
        wd2.get("https://demo.evershop.io/account/login");
        wd2.findElement(By.name("email")).sendKeys("Akhiljda@gmail.com");
        wd2.findElement(By.name("password")).sendKeys("Pas123sword");
        wd2.findElement(By.xpath("//button[@type='submit']")).click();
    }



    @Parameters({"Username", "Password", "ItemName"})
    @Test(enabled = false)
    public void loginTest2(String UN, String PW, String itemName) throws InterruptedException, MalformedURLException {


        //wd3 = new RemoteWebDriver( new URL("http://localhost:4444/"), chromeOptions);
//        wd3 = wd;
        LoginPOM login = new LoginPOM(wd);
        HomePOM home = new HomePOM(wd);
        ItemPOM item = new ItemPOM(wd);

        login.get().fillCredentials(UN, PW).clickLogin(); //homepage

        home.validatePage(fluentWait, itemName).clickItem(itemName); //itemPage

        Assert.assertEquals(item.getItemName().toLowerCase(), itemName.toLowerCase());  //hard assert

        item.fillQuantity("2").selectSize("L").selectColor("Green");

        Thread.sleep(4000);

        item.clickAddToCart();



        //

    }

    @AfterClass(alwaysRun = true)
    public void tearDown () throws InterruptedException {
        Thread.sleep(10000);
        //wd.quit();
    }




}
