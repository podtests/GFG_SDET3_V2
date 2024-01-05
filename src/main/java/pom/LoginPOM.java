package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPOM {

    private WebDriver wd;  //null

    public LoginPOM() {

    }

    //constructor
    public LoginPOM(WebDriver wd) {
        this.wd = wd;
    }

 //Properties

    //UN
    private final By submitButton = By.xpath("//button[@type='submit']");
    private final By userNameText = By.name("email");
    private final By passwordText = By.name("password");

    private final String pageURL = "https://demo.evershop.io/account/login";

    public LoginPOM clickLogin() {
        wd.findElement(submitButton).click();
        return this;
    }

    public LoginPOM fillCredentials(String UN, String PW) {
        wd.findElement(userNameText).sendKeys(UN);
        wd.findElement(passwordText).sendKeys(PW);
        return this;
    }

    public LoginPOM get() {
        wd.get(pageURL);
        return this;
    }

    /*
    public void doItAll(WebDriver wd, String applicationURL) {

        wd.get(applicationURL);

        wd.findElement(By.name("email123")).sendKeys("Akhiljda@gmail.com");
        wd.findElement(By.name("password")).sendKeys("Password");


        wd.findElement(submitButton).click();
    }
    */



}
