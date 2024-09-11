package page_object;

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
    private final By nameInputField = PageLocators.nameInputField;
    private final By surnameInputField = PageLocators.surnameInputField;
    private final By addressInputField = PageLocators.addressInputField;
    private final By metroStationInputField = PageLocators.metroStationInputField;
    private final By metroStationsList = PageLocators.metroStationsList;
    private final By phoneNumberInputField = PageLocators.phoneNumberInputField;
    private final By nextButtonOrderPage = PageLocators.nextButtonOrderPage;
    private final By calendarInputField = PageLocators.calendarInputField;
    private final By rentDropdownField = PageLocators.rentDropdownField;
    private final By rentDropdownList = PageLocators.rentDropdownList;
    private final By botOrderButtonOrderPage = PageLocators.botOrderButtonOrderPage;
    private final By buttonYesConfirmOrderWindow = PageLocators.buttonYesConfirmOrderWindow;
    private final By headerOrderHasBeenPlaced = PageLocators.headerOrderHasBeenPlaced;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getNameInputField() {
        return driver.findElement(nameInputField);
    }

    public WebElement getSurnameInputField() {
        return driver.findElement(surnameInputField);
    }

    public WebElement getAddressInputField() {
        return driver.findElement(addressInputField);
    }

    public WebElement getMetroStationInputField() {
        return driver.findElement(metroStationInputField);
    }

    public WebElement getMetroStation(String metroStationName) {
        return driver
                .findElements(metroStationsList)
                .stream()
                .filter(element1 -> element1.getText().equalsIgnoreCase(metroStationName))
                .findFirst().orElse(null);
    }

    public WebElement getPhoneNumberInputField() {
        return driver.findElement(phoneNumberInputField);
    }

    public WebElement getNextButtonOrderPage() {
        return driver.findElement(nextButtonOrderPage);
    }

    public WebElement getCalendarInputField() {
        return driver.findElement(calendarInputField);
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
        return driver.findElement(rentDropdownField);
    }

    public WebElement getRentalPeriod(RentalDays count) {
        return driver.findElements(rentDropdownList).get(count.getIndex());
    }

    public WebElement getBotOrderButtonOrderPage() {
        return driver.findElement(botOrderButtonOrderPage);
    }

    public WebElement getButtonYesConfirmOrderWindow() {
        return driver.findElement(buttonYesConfirmOrderWindow);
    }

    public WebElement getHeaderOrderHasBeenPlaced() {
        return driver.findElement(headerOrderHasBeenPlaced);
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

    public void clickYesButon() {
        getButtonYesConfirmOrderWindow().click();
    }
}
