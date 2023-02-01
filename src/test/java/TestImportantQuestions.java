import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.praktikium.yandex.model.MainPage;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestImportantQuestions {
    private final int question_id;
    private final int answer_id;
    private final String answer_text;
    private WebDriver driver;

    public TestImportantQuestions(int question_id, int answer_id, String answer_text) {
        this.question_id = question_id;
        this.answer_id = answer_id;
        this.answer_text = answer_text;
    }

    @Parameterized.Parameters
    public static Object[][] getExpandText() {
        return new Object[][]{
                {0, 0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {6, 6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
        };
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
    }

    @Test
    public void testQuestionsExpandOpenCheckText() {
        MainPage homePageExpand = new MainPage(driver);
        homePageExpand.openMainPage();
        homePageExpand.scrollToQuestions();
        homePageExpand.clickExpand(By.id("accordion__heading-" + this.question_id));
        homePageExpand.getText(By.id("accordion__panel-" + this.answer_id));
        assertEquals(homePageExpand.getText(By.id("accordion__panel-" + this.answer_id)), answer_text);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
