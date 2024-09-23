package praktikum.sprint4.mainpagetests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.sprint4.pom.MainPage;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FaqSectionTest {

    private WebDriver driver;
    private MainPage mainPage;
    private WebDriverWait w8;
    private final String questionText;
    private final String expectedAnswerText;

    public FaqSectionTest(String questionText, String expectedAnswerText) {
        this.questionText = questionText;
        this.expectedAnswerText = expectedAnswerText;

    }

    @Before
    public void presetting() {
        ChromeOptions options = new ChromeOptions().addArguments("--disable-cookies");
        driver = new ChromeDriver(options);
        driver.get(MainPage.MAIN_PAGE_URL);
        mainPage = new MainPage(driver);
        w8 = new WebDriverWait(driver, 3);
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {"Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"Я живу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Test
    public void firstAnswerShouldCorrespondFirstQuestion() {
        WebElement questionElement = mainPage.getQuestionElementWithText(questionText);
        mainPage.scrollToElement(questionElement);
        questionElement.click();
        mainPage.waitQuestionExpand(w8, questionElement);
        WebElement answerElement = mainPage.getExpandedAnswer();
        mainPage.waitVisibilityOfAnswer(w8, answerElement);
        assertEquals(
                "Ответ не соотвествует вопросу",
                expectedAnswerText,
                answerElement.getText()
        );
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
