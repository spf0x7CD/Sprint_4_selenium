package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class MainPage implements Scrollable{
    private final WebDriver driver;
    private static final By faqQuestionsList = PageLocators.faqQuestionsList;
    private static final By topOrderButton = PageLocators.topOrderButton;
    private static final By botOrderButtonMainPage = PageLocators.botOrderButtonMainPage;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getFaqQuestionsList() {
        return driver.findElements(faqQuestionsList);
    }

    public WebElement getTopOrderButton() {
        return driver.findElement(topOrderButton);
    }

    public WebElement getBotOrderButtonMainPage() {
        return driver.findElement(botOrderButtonMainPage);
    }

    public boolean isQuestionExpanded(WebElement element) {
        return Boolean.parseBoolean(element.getAttribute("aria-expanded"));
    }

    public void clickTopOrderButton() {
        getTopOrderButton().click();
    }
    @Override
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }
}
