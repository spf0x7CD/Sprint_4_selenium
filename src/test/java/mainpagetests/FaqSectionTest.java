package mainpagetests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.MainPage;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FaqSectionTest {

    private ChromeOptions options;
    private WebDriver driver;
    private MainPage mainPage;
    private WebDriverWait w8;
    List<WebElement> questionElements;
    List<WebElement> answerElements;
    HashMap<String, String> answerQuestionMap;

    @Before
    public void presetting() {
        options = new ChromeOptions().addArguments("--disable-cookies");
        driver = new ChromeDriver(options);
        driver.get(MainPage.MAIN_PAGE_URL);
        mainPage = new MainPage(driver);
        w8 = new WebDriverWait(driver, 3);
        questionElements = mainPage.getFaqQuestionsList();
        answerElements = mainPage.getAnswersList();
        answerQuestionMap = mainPage.getAnswerQuestionMap();
    }
    // ВАРИАНТ 1 -------------------------------------------------------------------------------------------------------
/*    @Test
    public void answersShouldOpenAfterClickAtQuestion() {
        for (int i = 0; i < questionElements.size(); i++) {
            WebElement question = questionElements.get(i);
            WebElement answer = answerElements.get(i);
            mainPage.scrollToElement(question);
            question.click();
            try {
                w8.until(ExpectedConditions.attributeToBe(question, "aria-expanded", "true"));
            } catch (TimeoutException e) {
                System.out.printf("Ответ на вопрос \"%s\" не раскрылся.\n", question.getText());
                continue;
            }
            System.out.printf("[%d]Вопрос: %s\n[%d]Ответ: %s\n", i, question.getText(), i, answer.getText());
            assertEquals(
                    String.format("Ответ не соотвествует вопросу[%d]", i),
                    answerQuestionMap.getOrDefault(question.getText(), ""),
                    answer.getText()
            );
            System.out.println("-------------------------------------------------------------------------------------");
        }
    }
*/
    // ВАРИАНТ 2 -------------------------------------------------------------------------------------------------------
    @Test
    public void firstAnswerShouldCorrespondFirstQuestion() {
        WebElement question = questionElements.get(0);
        WebElement answer = answerElements.get(0);
        mainPage.scrollToElement(question);
        question.click();
        try {
            w8.until(ExpectedConditions.attributeToBe(question, "aria-expanded", "true"));
        } catch (TimeoutException e) {
            System.out.printf("Ответ на вопрос \"%s\" не раскрылся.\n", question.getText());
        }
        System.out.printf("Вопрос: %s\nОтвет: %s\n",question.getText(), answer.getText());
        assertEquals(
                "Ответ не соотвествует вопросу",
                answerQuestionMap.getOrDefault(question.getText(), ""),
                answer.getText()
        );
    }

    @Test
    public void secondAnswerShouldCorrespondSecondQuestion() {
        WebElement question = questionElements.get(1);
        WebElement answer = answerElements.get(1);
        mainPage.scrollToElement(question);
        question.click();
        try {
            w8.until(ExpectedConditions.attributeToBe(question, "aria-expanded", "true"));
        } catch (TimeoutException e) {
            System.out.printf("Ответ на вопрос \"%s\" не раскрылся.\n", question.getText());
        }
        System.out.printf("Вопрос: %s\nОтвет: %s\n",question.getText(), answer.getText());
        assertEquals(
                "Ответ не соотвествует вопросу",
                answerQuestionMap.getOrDefault(question.getText(), ""),
                answer.getText()
        );
    }
    @Test
    public void thirdAnswerShouldCorrespondThirdQuestion() {
        WebElement question = questionElements.get(2);
        WebElement answer = answerElements.get(2);
        mainPage.scrollToElement(question);
        question.click();
        try {
            w8.until(ExpectedConditions.attributeToBe(question, "aria-expanded", "true"));
        } catch (TimeoutException e) {
            System.out.printf("Ответ на вопрос \"%s\" не раскрылся.\n", question.getText());
        }
        System.out.printf("Вопрос: %s\nОтвет: %s\n",question.getText(), answer.getText());
        assertEquals(
                "Ответ не соотвествует вопросу",
                answerQuestionMap.getOrDefault(question.getText(), ""),
                answer.getText()
        );
    }

    @Test
    public void fourthAnswerShouldCorrespondFourthQuestion() {
        WebElement question = questionElements.get(3);
        WebElement answer = answerElements.get(3);
        mainPage.scrollToElement(question);
        question.click();
        try {
            w8.until(ExpectedConditions.attributeToBe(question, "aria-expanded", "true"));
        } catch (TimeoutException e) {
            System.out.printf("Ответ на вопрос \"%s\" не раскрылся.\n", question.getText());
        }
        System.out.printf("Вопрос: %s\nОтвет: %s\n",question.getText(), answer.getText());
        assertEquals(
                "Ответ не соотвествует вопросу",
                answerQuestionMap.getOrDefault(question.getText(), ""),
                answer.getText()
        );
    }

    @Test
    public void fifthAnswerShouldCorrespondFifthQuestion() {
        WebElement question = questionElements.get(4);
        WebElement answer = answerElements.get(4);
        mainPage.scrollToElement(question);
        question.click();
        try {
            w8.until(ExpectedConditions.attributeToBe(question, "aria-expanded", "true"));
        } catch (TimeoutException e) {
            System.out.printf("Ответ на вопрос \"%s\" не раскрылся.\n", question.getText());
        }
        System.out.printf("Вопрос: %s\nОтвет: %s\n",question.getText(), answer.getText());
        assertEquals(
                "Ответ не соотвествует вопросу",
                answerQuestionMap.getOrDefault(question.getText(), ""),
                answer.getText()
        );
    }

    @Test
    public void sixthAnswerShouldCorrespondSixthQuestion() {
        WebElement question = questionElements.get(5);
        WebElement answer = answerElements.get(5);
        mainPage.scrollToElement(question);
        question.click();
        try {
            w8.until(ExpectedConditions.attributeToBe(question, "aria-expanded", "true"));
        } catch (TimeoutException e) {
            System.out.printf("Ответ на вопрос \"%s\" не раскрылся.\n", question.getText());
        }
        System.out.printf("Вопрос: %s\nОтвет: %s\n",question.getText(), answer.getText());
        assertEquals(
                "Ответ не соотвествует вопросу",
                answerQuestionMap.getOrDefault(question.getText(), ""),
                answer.getText()
        );
    }

    @Test
    public void seventhAnswerShouldCorrespondSeventhQuestion() {
        WebElement question = questionElements.get(6);
        WebElement answer = answerElements.get(6);
        mainPage.scrollToElement(question);
        question.click();
        try {
            w8.until(ExpectedConditions.attributeToBe(question, "aria-expanded", "true"));
        } catch (TimeoutException e) {
            System.out.printf("Ответ на вопрос \"%s\" не раскрылся.\n", question.getText());
        }
        System.out.printf("Вопрос: %s\nОтвет: %s\n",question.getText(), answer.getText());
        assertEquals(
                "Ответ не соотвествует вопросу",
                answerQuestionMap.getOrDefault(question.getText(), ""),
                answer.getText()
        );
    }

    @Test
    public void eightAnswerShouldCorrespondEightQuestion() {
        WebElement question = questionElements.get(7);
        WebElement answer = answerElements.get(7);
        mainPage.scrollToElement(question);
        question.click();
        try {
            w8.until(ExpectedConditions.attributeToBe(question, "aria-expanded", "true"));
        } catch (TimeoutException e) {
            System.out.printf("Ответ на вопрос \"%s\" не раскрылся.\n", question.getText());
        }
        System.out.printf("Вопрос: %s\nОтвет: %s\n",question.getText(), answer.getText());
        assertEquals(
                "Ответ не соотвествует вопросу",
                answerQuestionMap.getOrDefault(question.getText(), ""),
                answer.getText()
        );
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
