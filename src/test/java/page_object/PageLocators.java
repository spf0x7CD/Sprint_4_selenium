package page_object;

import org.openqa.selenium.By;

public class PageLocators {
    // <========================================= ГЛАВНАЯ СТРАНИЦА ====================================================>

    // Кликабельная секция с вопросами
    public static final By faqQuestionsList = By.xpath(".//div[@aria-expanded]");
    // Кнопка Заказать вверху экрана
    public static final By topOrderButton = By.xpath(".//button[@class='Button_Button__ra12g']");
    // Кнопка заказать внизу экрана
    public static final By botOrderButtonMainPage = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button");

    // <========================================= СТРАНИЦА ЗАКАЗА =====================================================>
    // <------------------------------------------ ЭКРАН ЗАКАЗА 1 ----------------------------------------------------->

    // Поле ввода имени
    public static final By nameInputField = By.xpath(".//input[@placeholder='* Имя']");
    // Поле ввода фамилии
    public static final By surnameInputField = By.xpath(".//input[@placeholder='* Фамилия']");
    // Поле ввода адреса
    public static final By addressInputField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    // Поле с выпадающим списком станций
    public static final By metroStationInputField = By.xpath(".//input[@placeholder='* Станция метро']/parent::*/parent::*");
    // Выпадающий список станций метро
    public static final By metroStationsList = By.xpath(".//div[@class='Order_Text__2broi']/parent::*/parent::*");
    // Поле номера телефона
    public static final By phoneNumberInputField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка далее
    public static final By nextButtonOrderPage = By.xpath(".//button[text()='Далее']");

    // <------------------------------------------ ЭКРАН ЗАКАЗА 2 ----------------------------------------------------->

    // Поле с выпадающим календарем
    public static final By calendarInputField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    // Поле с выпадающим списком срока аренды
    public static final By rentDropdownField = By.xpath(".//div[@class='Dropdown-root']");
    // Список сроков аренды
    public static final By rentDropdownList = By.xpath(".//div[@class='Dropdown-menu']/div");
    // Кнопка Заказать экрана заказа
    public static final By botOrderButtonOrderPage  = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");

    // <------------------------------------- ОКНО "ХОТИТЕ ОФОРМИТЬ ЗАКАЗ" ------------------------------------------->

    // Окно Хотите оформить заказ
    public static final By popUpWindowConfirmOrder = By.xpath(".//div[@class='Order_Modal__YZ-d3']");
    // Кнопка Да
    public static final By buttonYesConfirmOrderWindow = By.xpath(".//button[text()='Да']");
    // Заголовок Заказ оформлен
    public static final By headerOrderHasBeenPlaced = By.xpath(".//div[text()='Заказ оформлен']");
}
