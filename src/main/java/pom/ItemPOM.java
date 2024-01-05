package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class ItemPOM {

    private WebDriver wd;  //null

    //private String pageURL = "https://demo.evershop.io/";

    //constructor
    public ItemPOM(WebDriver wd) {
        this.wd = wd;
    }

    private String baseURL = "https://demo.evershop.io/";

    private final By itemNameText = By.xpath("//h1[@class='product-single-name']");
    private final By quantityTextBox = By.xpath("//input[@name='qty']");

    private final By addToCartButton = By.xpath("//button[@type='button']");

    private String itemSizeXPathLocator =  "(//ul[contains(@class, 'variant-option-list')])[1]/li/a[text()='^^']";

    private String itemColorXPathLocator =  "(//ul[contains(@class, 'variant-option-list')])[2]/li/a[text()='^^']";

    private final By itemColor = By.xpath("");

    public ItemPOM validatePage(FluentWait fw, String color) {
        String itemColorLocator = itemColorXPathLocator.replace("^^", color);
        By itemColor = By.xpath(itemColorLocator);
        WebElement listTag = wd.findElement(itemColor).findElement(By.xpath("//parent::li"));

        fw.until(ExpectedConditions.attributeContains(listTag,"class", "selected" ));
        return this;
    }

    public String getItemName() {
        return wd.findElement(itemNameText).getText();
    }

    public ItemPOM fillQuantity(String quantity) {
        wd.findElement(quantityTextBox).clear();
        wd.findElement(quantityTextBox).sendKeys(quantity);
        return this;
    }

    public ItemPOM selectSize(String size) {
        String itemSizeLocator = itemSizeXPathLocator.replace("^^", size);
        By itemSize = By.xpath(itemSizeLocator);

        wd.findElement(itemSize).click();
        return this;
    }

    public ItemPOM selectColor(String color) {
        String itemColorLocator = itemColorXPathLocator.replace("^^", color);
        By itemColor = By.xpath(itemColorLocator);
        wd.findElement(itemColor).click();
        return this;
    }

    public ItemPOM clickAddToCart() {
        wd.findElement(addToCartButton).click();
        return this;
    }


}
