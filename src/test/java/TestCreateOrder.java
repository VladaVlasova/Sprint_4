import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.praktikium.yandex.model.MainPage;
import ru.praktikium.yandex.model.OrderPage;
import ru.praktikium.yandex.model.RentPage;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;


@RunWith(Parameterized.class)
public class TestCreateOrder {
    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final int metro;
    private final String phoneNumber;
    private final String date;
    private final String color;
    private final int rentTime;
    private final String comment;

    public TestCreateOrder(String name, String surname, String address, int metro, String phoneNumber, String date, int rentTime, String color, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.rentTime = rentTime;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] fillUserInfo() {
        return new Object[][]{
                {"Имя", "Фамилия", "Советский союз", 1, "89999999999", "10.02.2023", 1, "grey", "апп"},
                {"ЕщеИмя", "ЕщеФамилия", "Ещеадрес", 1, "87777777777", "20.02.2023", 2, "black", "пп"},
        };
    }

    @Before
    public void setUp() {
        // driver = new ChromeDriver();
        driver = new FirefoxDriver();
    }

    @Test
    public void testMakeOrderFromHeader() {
        MainPage mainPage = new MainPage(driver);
        OrderPage userDataPage = new OrderPage(driver);
        RentPage rentpage = new RentPage(driver);

        mainPage.openMainPage();
        mainPage.clickOrderButtonInHeader();
        userDataPage.fillUserData(this.name, this.surname, this.address, this.metro, this.phoneNumber);
        rentpage.makeOrder(this.date, this.rentTime, this.color, this.comment);
        rentpage.getSuccessText();
        assertThat(rentpage.getSuccessText(), containsString("Заказ оформлен"));
    }

    @Test
    public void testMakeOrderMiddleButton() {
        MainPage mainPage = new MainPage(driver);
        OrderPage userDataPage = new OrderPage(driver);
        RentPage rentpage = new RentPage(driver);

        mainPage.openMainPage();
        mainPage.closeCookie();
        mainPage.clickMiddleOrderButton();
        userDataPage.fillUserData(this.name, this.surname, this.address, this.metro, this.phoneNumber);
        rentpage.makeOrder(this.date, this.rentTime, this.color, this.comment);
        rentpage.getSuccessText();
        assertThat(rentpage.getSuccessText(), containsString("Заказ оформлен"));
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}