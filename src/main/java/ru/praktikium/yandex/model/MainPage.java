package ru.praktikium.yandex.model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    private static final By QUESTIONS_BLOCK = By.className("Home_FourPart__1uthg"); // весь блок с выпадающими вопросами
    private static final By ORDER_BUTTON_HEADER = By.className("Button_Button__ra12g"); // "Заказать" в хедере
    private static final By ORDER_BUTTON_2 = By.xpath("/.//div[4]/div[2]/div[5]/button"); // "Заказать" в середине страницы
    private static final By COOKIE_BUTTON = By.className("App_CookieButton__3cvqF"); // кнопка принять куки
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openMainPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    public void closeCookie() {
        driver.findElement(COOKIE_BUTTON).click();
    }

    public void scrollToQuestions() {
        WebElement element = driver.findElement(QUESTIONS_BLOCK);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickExpand(By element) {
        driver.findElement(element).click();
    }

    public String getText(By element) {
        return driver.findElement(element).getText();
    }

    public void clickOrderButtonInHeader() {
        driver.findElement(ORDER_BUTTON_HEADER).click();
    }

    public void clickMiddleOrderButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(ORDER_BUTTON_2));
        driver.findElement(ORDER_BUTTON_2).click();
    }
}
