package ru.praktikium.yandex.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class OrderPage {
    private static final By ORDER_USER_NAME_INPUT = By.cssSelector("[placeholder='* Имя']"); // поле ввода имени
    private static final By ORDER_SURNAME_INPUT = By.cssSelector("[placeholder='* Фамилия']"); // поле ввода фамилии
    private static final By ORDER_ADRESS_INPUT = By.cssSelector("[placeholder='* Адрес: куда привезти заказ']"); // поле ввода адрес
    private static final By ORDER_METRO_STATION_INPUT = By.className("select-search"); // поле выбора станции метро
    private static final By ORDER_PHONE_INPUT = By.cssSelector("[placeholder='* Телефон: на него позвонит курьер']"); // поле ввода номера телефона
    private static final By MAKE_NEXT_BUTTON = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM"); // конпка "Далее"
    private final WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void insertName(String name) {
        driver.findElement(ORDER_USER_NAME_INPUT).sendKeys(name);
    }

    public void insertSurname(String surname) {
        driver.findElement(ORDER_SURNAME_INPUT).sendKeys(surname);
    }

    public void insertAdress(String adress) {
        driver.findElement(ORDER_ADRESS_INPUT).sendKeys(adress);
    }

    public void insertMetroStation(int stationNum) {
        driver.findElement(ORDER_METRO_STATION_INPUT).click();
        driver.findElement(By.xpath(".//div[@class='select-search__select']/ul/li"
                + "[" + stationNum + "]")).click();
    }

    public void insertNumber(String number) {
        driver.findElement(ORDER_PHONE_INPUT).sendKeys(number);
    }

    public void fillUserData(String name, String surname, String address, int stationNumber, String phoneNumber) {
        insertName(name);
        insertSurname(surname);
        insertAdress(address);
        insertMetroStation(stationNumber);
        insertNumber(phoneNumber);
        pressOrderButton();
    }

    public void pressOrderButton() {
        driver.findElement(MAKE_NEXT_BUTTON).click();
    }
}
