package praktikum.sprint4.orderpagetests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.sprint4.pom.RentalDays;
import praktikum.sprint4.pom.MainPage;
import praktikum.sprint4.pom.OrderPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class PositiveOrderScenarioTests {

    private ChromeOptions options;
    private WebDriver driver;
    private OrderPage orderPage;
    private MainPage mainPage;
    private WebDriverWait w8;
    private final String NAME;
    private final String SURNAME;
    private final String ADDRESS;
    private final String METRO_STATION;
    private final String PHONE_NUMBER;
    private final int CALENDAR_DAYS_AFTER_TODAY;
    private final RentalDays RENTAL_DAYS;

    public PositiveOrderScenarioTests(
            String NAME,
            String SURNAME,
            String ADDRESS,
            String METRO_STATION,
            String PHONE_NUMBER,
            int CALENDAR_DAYS_AFTER_TODAY,
            RentalDays RENTAL_DAYS
    ) {
        this.NAME = NAME;
        this.SURNAME = SURNAME;
        this.ADDRESS = ADDRESS;
        this.METRO_STATION = METRO_STATION;
        this.PHONE_NUMBER = PHONE_NUMBER;
        this.CALENDAR_DAYS_AFTER_TODAY = CALENDAR_DAYS_AFTER_TODAY;
        this.RENTAL_DAYS = RENTAL_DAYS;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"Алёша", "Попович", "ул. Пушкина, д. Колотушкина", "Лихоборы", "88005553535", 99, RentalDays.SEVEN},
                {"Йеллоу", "Кард", "Оушен Авеню", "Деловой центр", "+79999999999", 2003, RentalDays.ONE},
        };
    }

    @Before
    public void presetting() {
        options = new ChromeOptions().addArguments("--disable-cookies");
        driver = new ChromeDriver(options);
        driver.get(MainPage.MAIN_PAGE_URL);
        mainPage = new MainPage(driver);
        w8 = new WebDriverWait(driver, 3);
    }

    @Test
    public void shouldMakeOrderViaMainPageTopOrderButton() {
        orderPage = new OrderPage(driver);
        mainPage.clickTopOrderButton();
        w8.until(ExpectedConditions.urlToBe(OrderPage.ORDER_PAGE_URL));
        orderPage.fillNameField(NAME);
        orderPage.fillSurnameField(SURNAME);
        orderPage.fillAddressField(ADDRESS);
        orderPage.selectMetroStation(METRO_STATION);
        orderPage.fillPhoneNumberField(PHONE_NUMBER);
        orderPage.clickNextButton();
        orderPage.selectDeliveryDate(CALENDAR_DAYS_AFTER_TODAY);
        orderPage.selectRentalPeriod(RENTAL_DAYS);
        orderPage.clickBotOrderButton();
        orderPage.clickYesButton();
        assertTrue(orderPage.getHeaderOrderHasBeenPlaced().isDisplayed());
    }

    @Test
    public void shouldMakeOrderViaMainPageBotOrderButton() {
        mainPage.scrollToElement(mainPage.getBotOrderButton());
        mainPage.clickBotOrderButton();
        w8.until(ExpectedConditions.urlToBe(OrderPage.ORDER_PAGE_URL));
        orderPage.fillNameField(NAME);
        orderPage.fillSurnameField(SURNAME);
        orderPage.fillAddressField(ADDRESS);
        orderPage.selectMetroStation(METRO_STATION);
        orderPage.fillPhoneNumberField(PHONE_NUMBER);
        orderPage.clickNextButton();
        orderPage.selectDeliveryDate(CALENDAR_DAYS_AFTER_TODAY);
        orderPage.selectRentalPeriod(RENTAL_DAYS);
        orderPage.clickBotOrderButton();
        orderPage.clickYesButton();
        assertTrue(orderPage.getHeaderOrderHasBeenPlaced().isDisplayed());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
