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
    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final int calendarDaysAfterToday;
    private final RentalDays rentalDays;

    public PositiveOrderScenarioTests(
            String name,
            String surname,
            String address,
            String metroStation,
            String phoneNumber,
            int calendarDaysAfterToday,
            RentalDays rentalDays
    ) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.calendarDaysAfterToday = calendarDaysAfterToday;
        this.rentalDays = rentalDays;
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
        orderPage.fillNameField(name);
        orderPage.fillSurnameField(surname);
        orderPage.fillAddressField(address);
        orderPage.selectMetroStation(metroStation);
        orderPage.fillPhoneNumberField(phoneNumber);
        orderPage.clickNextButton();
        orderPage.selectDeliveryDate(calendarDaysAfterToday);
        orderPage.selectRentalPeriod(rentalDays);
        orderPage.clickBotOrderButton();
        orderPage.clickYesButton();
        assertTrue(orderPage.getHeaderOrderHasBeenPlaced().isDisplayed());
    }

    @Test
    public void shouldMakeOrderViaMainPageBotOrderButton() {
        mainPage.scrollToElement(mainPage.getBotOrderButton());
        mainPage.clickBotOrderButton();
        w8.until(ExpectedConditions.urlToBe(OrderPage.ORDER_PAGE_URL));
        orderPage.fillNameField(name);
        orderPage.fillSurnameField(surname);
        orderPage.fillAddressField(address);
        orderPage.selectMetroStation(metroStation);
        orderPage.fillPhoneNumberField(phoneNumber);
        orderPage.clickNextButton();
        orderPage.selectDeliveryDate(calendarDaysAfterToday);
        orderPage.selectRentalPeriod(rentalDays);
        orderPage.clickBotOrderButton();
        orderPage.clickYesButton();
        assertTrue(orderPage.getHeaderOrderHasBeenPlaced().isDisplayed());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
