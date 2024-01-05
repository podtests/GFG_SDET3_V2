package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private static WebDriver getDriver() {
       return WebDriverManager.driver.get();
    }

    private static void setDriver() throws MalformedURLException {
        ChromeOptions chromeOptions = new ChromeOptions();
        driver.set(new RemoteWebDriver( new URL("http://localhost:4444/"), chromeOptions));
    }

    public static WebDriver createDriver()  {
        try {
            setDriver();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return getDriver();
    }


    public static void closeSession() {
        getDriver().quit();
        driver.remove();
    }


}
