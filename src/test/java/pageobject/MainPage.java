package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class MainPage implements Scrollable {

    private final WebDriver driver;
    public static final String MAIN_PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    private static final By FAQ_QUESTIONS_LIST = By.xpath(".//div[@aria-expanded]");
    private static final By FAQ_ANSWERS_LIST = By.xpath(".//div[@class='accordion__panel']");
    private static final By TOP_ORDER_BUTTON = By.xpath(".//button[@class='Button_Button__ra12g']");
    private static final By BOT_ORDER_BUTTON = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getFaqQuestionsList() {
        return driver.findElements(FAQ_QUESTIONS_LIST);
    }

    public List<WebElement> getAnswersList() {
        return driver.findElements(FAQ_ANSWERS_LIST);
    }

    public WebElement getTopOrderButton() {
        return driver.findElement(TOP_ORDER_BUTTON);
    }

    public WebElement getBotOrderButton() {
        return driver.findElement(BOT_ORDER_BUTTON);
    }

    public void clickTopOrderButton() {
        getTopOrderButton().click();
    }

    public void clickBotOrderButton() {
        getBotOrderButton().click();
    }

    public HashMap<String, String> getAnswerQuestionMap() { // Дальше бога нет
        WebDriverWait w8 = new WebDriverWait(driver, 3);
        List<String> questionsList = driver.findElements(FAQ_QUESTIONS_LIST)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        List<String> answersList = driver.findElements(FAQ_ANSWERS_LIST)
                .stream()
                .map(element -> {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute(arguments[1]);", element, "hidden");
                    w8.until(ExpectedConditions.visibilityOf(element));
                    String text = element.getText();
                    ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element, "hidden", "");
                    return text;
                })
                .collect(Collectors.toList());
        HashMap<String, String> answerQuestionMap = new HashMap<>();
        for (int i = 0; i < questionsList.size(); i++) {
            answerQuestionMap.put(questionsList.get(i), answersList.get(i));
        }
        return answerQuestionMap;
    }

    @Override
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }
}
