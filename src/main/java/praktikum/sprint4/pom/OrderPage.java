package praktikum.sprint4.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.util.*;

public class OrderPage implements Scrollable {

    private final WebDriver driver;
    public static final String ORDER_PAGE_URL = "https://qa-scooter.praktikum-services.ru/order";
    // Поле ввода имени
    private static final By NAME_INPUT_FIELD = By.xpath(".//input[@placeholder='* Имя']");
    // Поле ввода фамилии
    private static final By SURNAME_INPUT_FIELD = By.xpath(".//input[@placeholder='* Фамилия']");
    // Поле ввода адреса
    private static final By ADDRESS_INPUT_FIELD = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    // Поле с выпадающим списком станций
    private static final By METRO_STATION_INPUT_FIELD = By.xpath(".//input[@placeholder='* Станция метро']/parent::*/parent::*");
    // Выпадающий список станций метро
    private static final By METRO_STATIONS_LIST = By.xpath(".//div[@class='Order_Text__2broi']/parent::*/parent::*");
    // Поле номера телефона
    private static final By PHONE_NUMBER_INPUT_FIELD = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка далее
    private static final By NEXT_BUTTON_ORDER_PAGE = By.xpath(".//button[text()='Далее']");
    // Поле с выпадающим календарем
    public static final By CALENDAR_INPUT_FIELD = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    // Поле с выпадающим списком срока аренды
    public static final By RENT_DROPDOWN_FIELD = By.xpath(".//div[@class='Dropdown-root']");
    // Список сроков аренды
    public static final By RENT_DROPDOWN_LIST = By.xpath(".//div[@class='Dropdown-menu']/div");
    // Кнопка Заказать экрана заказа
    public static final By BOT_ORDER_BUTTON_ORDER_PAGE = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    // Кнопка Да
    public static final By BUTTON_YES_CONFIRM_ORDER_WINDOW = By.xpath(".//button[text()='Да']");
    // Заголовок Заказ оформлен
    public static final By HEADER_ORDER_HAS_BEEN_PLACED = By.xpath(".//div[text()='Заказ оформлен']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getNameInputField() {
        return driver.findElement(NAME_INPUT_FIELD);
    }

    public WebElement getSurnameInputField() {
        return driver.findElement(SURNAME_INPUT_FIELD);
    }

    public WebElement getAddressInputField() {
        return driver.findElement(ADDRESS_INPUT_FIELD);
    }

    public WebElement getMetroStationInputField() {
        return driver.findElement(METRO_STATION_INPUT_FIELD);
    }

    public WebElement getMetroStation(String metroStationName) {
        return driver
                .findElements(METRO_STATIONS_LIST)
                .stream()
                .filter(element1 -> element1.getText().equalsIgnoreCase(metroStationName))
                .findFirst().orElse(null);
    }

    public WebElement getPhoneNumberInputField() {
        return driver.findElement(PHONE_NUMBER_INPUT_FIELD);
    }

    public WebElement getNextButtonOrderPage() {
        return driver.findElement(NEXT_BUTTON_ORDER_PAGE);
    }

    public WebElement getCalendarInputField() {
        return driver.findElement(CALENDAR_INPUT_FIELD);
    }

    public void setCalendarDate(int daysAfterToday) {
        ZonedDateTime plusDate = new Date()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .plusDays(Math.max(daysAfterToday, 0));
        int requiredYear = plusDate.getYear();

        String requiredMonth = plusDate
                .getMonth()
                .getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru"));
        int requiredDay = plusDate.getDayOfMonth();

        WebElement currMonthElem = driver
                .findElement(By.xpath(".//div[@class='react-datepicker__current-month']"));
        WebElement nextMonthButton = driver
                .findElement(By.xpath(".//button[@aria-label='Next Month']"));

        while (!currMonthElem.getText().equals(String.format("%s %d", requiredMonth, requiredYear))) {
            nextMonthButton.click();
        }
        driver
                .findElement(By.xpath(String.format(".//div[@class='react-datepicker__month']/div/div[not(contains(@class, '--outside-month')) and text()=%d]", requiredDay)))
                .click();
    }

    public WebElement getRentDropdownField() {
        return driver.findElement(RENT_DROPDOWN_FIELD);
    }

    public WebElement getRentalPeriod(RentalDays count) {
        return driver.findElements(RENT_DROPDOWN_LIST).get(count.getIndex());
    }

    public WebElement getBotOrderButtonOrderPage() {
        return driver.findElement(BOT_ORDER_BUTTON_ORDER_PAGE);
    }

    public WebElement getButtonYesConfirmOrderWindow() {
        return driver.findElement(BUTTON_YES_CONFIRM_ORDER_WINDOW);
    }

    public WebElement getHeaderOrderHasBeenPlaced() {
        return driver.findElement(HEADER_ORDER_HAS_BEEN_PLACED);
    }

    @Override
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void fillNameField(String name) {
        getNameInputField().sendKeys(name);
    }

    public void fillSurnameField(String surname) {
        getSurnameInputField().sendKeys(surname);
    }

    public void fillAddressField(String address) {
        getAddressInputField().sendKeys(address);
    }

    public void selectMetroStation(String metroStation) {
        getMetroStationInputField().click();
        WebElement metroStationElement = getMetroStation(metroStation);
        scrollToElement(metroStationElement);
        metroStationElement.click();
    }

    public void fillPhoneNumberField(String phoneNumber) {
        getPhoneNumberInputField().sendKeys(phoneNumber);
    }

    public void selectDeliveryDate(int days) {
        getCalendarInputField().click();
        setCalendarDate(days);
    }

    public void selectRentalPeriod(RentalDays days) {
        getRentDropdownField().click();
        WebElement rentalPeriodElement = getRentalPeriod(days);
        scrollToElement(rentalPeriodElement);
        rentalPeriodElement.click();
    }

    public void clickNextButton() {
        getNextButtonOrderPage().click();
    }

    public void clickBotOrderButton() {
        getBotOrderButtonOrderPage().click();
    }

    public void clickYesButton() {
        getButtonYesConfirmOrderWindow().click();
    }
}
