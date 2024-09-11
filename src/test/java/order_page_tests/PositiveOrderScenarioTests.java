package order_page_tests;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_object.RentalDays;
import page_object.MainPage;
import page_object.OrderPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class PositiveOrderScenarioTests {
    private WebDriver driver;
    private OrderPage orderPage;
    private MainPage mainPage;
    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final int calendarDaysAfterToday;
    private final RentalDays rentalDaysCount;

    public PositiveOrderScenarioTests(
            String name,
            String surname,
            String address,
            String metroStation,
            String phoneNumber,
            int calendarDaysAfterToday,
            RentalDays rentalDaysCount
    ) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.calendarDaysAfterToday = calendarDaysAfterToday;
        this.rentalDaysCount = rentalDaysCount;
    }


    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"Алёша", "Попович", "ул. Пушкина, д. Колотушкина", "Лихоборы", "88005553535", 999, RentalDays.SEVEN}
        };
    }

    @Test
    public void shouldMakeOrderViaTopButtonFromMainPage() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
        WebDriverWait w8 = new WebDriverWait(driver, 3);

        mainPage.clickTopOrderButton();
        orderPage.fillNameField(name);
        orderPage.fillSurnameField(surname);
        orderPage.fillAddressField(address);
        orderPage.selectMetroStation(metroStation);
        orderPage.fillPhoneNumberField(phoneNumber);
        orderPage.clickNextButton();
        orderPage.selectDeliveryDate(calendarDaysAfterToday);
        orderPage.selectRentalPeriod(rentalDaysCount);
        orderPage.clickBotOrderButton();
        orderPage.clickYesButon();
        assertTrue( orderPage.getHeaderOrderHasBeenPlaced().isDisplayed());
    }


    @After
    public void teardown() {
        driver.quit();
    }
}
