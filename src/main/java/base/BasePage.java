package base;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;

    // Common elements
    @FindBy(id = "header")
    private WebElement header;

    @FindBy(id = "footer")
    private WebElement footer;

    // Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Common methods
    public void clickElement(WebElement element) {
        element.click();
    }

    public void enterText(WebElement element, String text) {
        element.sendKeys(text);
    }

    public void waitForElementToBeVisible(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOf(element));
    }

    // Navigation
    public void navigateTo(String url) {
        driver.get(url);
    }

    // Utility methods
    public void executeJavaScript(String script) {
        ((JavascriptExecutor) driver).executeScript(script);
    }

    public String getAlertText() {
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    public boolean isPageLoaded() {
        return header.isDisplayed();
    }
    
    public WebElement getFooter() {
    	System.out.println("footer");
        return footer;
    }
}
