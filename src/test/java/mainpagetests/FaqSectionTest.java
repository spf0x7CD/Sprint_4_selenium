package mainpagetests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.MainPage;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FaqSectionTest {

    private ChromeOptions options;
    private WebDriver driver;
    private MainPage mainPage;
    private WebDriverWait w8;

    @Before
    public void presetting() {
        options = new ChromeOptions().addArguments("--disable-cookies"); // Не уверен что работает, но индус на ютубе сказал что работает. Не знаю как проверить.
        driver = new ChromeDriver(options);
        driver.get(MainPage.url);
        mainPage = new MainPage(driver);
        w8 = new WebDriverWait(driver, 3);

    }

    @Test
    public void answersShouldOpenAfterClickAtQuestion() {
        List<WebElement> questionElements = mainPage.getFaqQuestionsList();
        int totalQuestions = questionElements.size();
        int expandedQuestionsCount = 0;

        for (WebElement question : questionElements) {
            mainPage.scrollToElement(question);
            question.click();
            // Решил эту проблему обработкой исключения, надеюсь так можно 😰
            try {
                w8.until(ExpectedConditions.attributeToBe(question, "aria-expanded", "true"));
                expandedQuestionsCount += 1;
            } catch (TimeoutException e) {
                System.out.printf("Ответ на вопрос \"%s\" не раскрылся.\n", question.getText());
                continue;
            }
            System.out.printf("Ответ на вопрос \"%s\" раскрылся.\n", question.getText());
        }
        assertEquals(String.format("%d вопрос(-а, -ов) не были развернуты.", totalQuestions - expandedQuestionsCount), totalQuestions, expandedQuestionsCount);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
