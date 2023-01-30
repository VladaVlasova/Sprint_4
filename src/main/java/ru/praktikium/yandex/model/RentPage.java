package ru.praktikium.yandex.model;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.sql.Driver;

public class RentPage {

    private WebDriver driver;

    public RentPage(WebDriver driver) {
        this.driver = driver;
    }

    private static final By DATE_INPUT = By.cssSelector("[placeholder='* Когда привезти самокат']"); // поле ввода даты
    private static final By RENT_TIME_INPUT = By.className("Dropdown-placeholder"); // поле выбора срока аренды
    private static final By CHECKBOX_COLOR_BLACK = By.xpath(".//input[@id = 'black']"); // чебкос для черного цвета самоката
    private static final By CHECKBOX_COLOR_GREY = By.xpath(".//input[@id = 'grey']"); // чебкос для серого цвета
    private static final By COMMENT_INPUT = By.cssSelector("[placeholder='Комментарий для курьера']"); // поле ввода комментария к заказу
    private static final By RENT_ORDER_BUTTON = By.xpath(".//div[2]/div[3]/button[2]"); // Про аренду - кнопка "Заказать"
    private static final By CALENDAR = By.cssSelector("[placeholder='* Когда привезти самокат']"); // выпадающий календарь в поле ввода даты

    private static final By YES_BUTTON_IN_WINDOW = By.xpath(".//div[2]/div[5]/div[2]/button[2]"); // "ДА" в окошке подтвержденя заказа
    private static final By SUCCESS_WINDOW = By.className("Order_ModalHeader__3FDaJ"); // окно успешного оформления заказа


    public void insertDate(String date) {
        driver.findElement(DATE_INPUT).sendKeys(date);
        driver.findElement(CALENDAR).sendKeys(date, (Keys.ENTER));
    }

    public void insertRentTime(int time) {

        driver.findElement(RENT_TIME_INPUT).click();
        driver.findElement(By.xpath(".//div[@class='Dropdown-option']"
                + "[" + time + "]")).click();

    }

    public void insertOrderComment(String comment) {
        driver.findElement(COMMENT_INPUT).sendKeys(comment);
    }

    public void pickColor(String colour) {
        if (colour.equals("black"))  driver.findElement(CHECKBOX_COLOR_BLACK).click();
        else if (colour.equals("grey")) driver.findElement(CHECKBOX_COLOR_GREY).click();
    }

    public void clickOrderButton() {
        driver.findElement(RENT_ORDER_BUTTON).click();

    }

    public void pressYesInWindow() {
        driver.findElement(YES_BUTTON_IN_WINDOW).click();

    }

    public String getSuccessText() {
        //return driver.findElement(SUCCESS_WINDOW).isDisplayed();
        return driver.findElement(SUCCESS_WINDOW).getText();
    }


    public void makeOrder(String date, int rentTime, String color, String comment) {
        insertDate(date);
        insertRentTime(rentTime);
        pickColor(color);
        insertOrderComment(comment);
        driver.findElement(RENT_ORDER_BUTTON).click();
        driver.findElement(YES_BUTTON_IN_WINDOW).click();
    }

}
