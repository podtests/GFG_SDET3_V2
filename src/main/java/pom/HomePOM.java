package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class HomePOM {

    private WebDriver wd;  //null

    private String pageURL = "https://demo.evershop.io/";

    private String itemTextLinkXPathLocator = "//div[@class='listing-tem']//a/span[text()='^^']";

    public void clickItem(String itemName) {
        String itemTextLinkLocator = itemTextLinkXPathLocator.replace("^^", itemName);
        By itemTextLink = By.xpath(itemTextLinkLocator);
        wd.findElement(itemTextLink).click();
       // return this;
    }

    //check whether the page loaded/rendred or not
    public HomePOM validatePage(FluentWait fw, String itemName) {
        String itemTextLinkLocator = itemTextLinkXPathLocator.replace("^^", itemName);
        By itemTextLink = By.xpath(itemTextLinkLocator);
        fw.until(ExpectedConditions.elementToBeClickable(itemTextLink));
        return this;
    }

    //constructor
    public HomePOM(WebDriver wd) {
        this.wd = wd;
    }


    public HomePOM get() {
        wd.navigate().to(pageURL);
        return this;
    }
}
