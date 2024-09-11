package main_page_tests;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_object.MainPage;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class FaqSectionTest {

    private WebDriver driver;
    @Test
    public void answersShouldOpenAfterClickAtQuestion() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage mainPage = new MainPage(driver);
        WebDriverWait w8 = new WebDriverWait(driver, 3);
        List<WebElement> questionElements = mainPage.getFaqQuestionsList();

        for (WebElement question : questionElements) {
            mainPage.scrollToElement(question);
            question.click();
            w8.until(ExpectedConditions.attributeToBe(question, "aria-expanded", "true"));
            assertTrue(String.format("Ответ на вопрос \"%s\" не раскрылся", question.getText()), mainPage.isQuestionExpanded(question));
            System.out.printf("Ответ на вопрос \"%s\" раскрылся\n", question.getText());
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
