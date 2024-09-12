package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class MainPage implements Scrollable {

    private final WebDriver driver;
    public static final String url = "https://qa-scooter.praktikum-services.ru/";
    private static final By faqQuestionsList = By.xpath(".//div[@aria-expanded]");
    private static final By topOrderButton = By.xpath(".//button[@class='Button_Button__ra12g']");
    private static final By botOrderButton = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getFaqQuestionsList() {
        return driver.findElements(faqQuestionsList);
    }

    public WebElement getTopOrderButton() {
        return driver.findElement(topOrderButton);
    }

    public WebElement getBotOrderButton() {
        return driver.findElement(botOrderButton);
    }

    public void clickTopOrderButton() {
        getTopOrderButton().click();
    }

    public void clickBotOrderButton() {
        getBotOrderButton().click();
    }

    @Override
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }
}
