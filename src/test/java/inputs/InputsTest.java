package inputs;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Objects;

public class InputsTest {
    WebDriver driver;


    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/inputs");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void inputs(){
        WebElement inputField = driver.findElement(By.tagName("input"));

        // Проверка ввода цифровых значений
        inputField.sendKeys("123"); // Ввод числа
        inputField.sendKeys(Keys.ARROW_UP);// Увеличение числа
        assert Objects.equals(inputField.getAttribute("value"), "124")
                : "Значение должно быть 124 после Arrow Up";

        inputField.sendKeys(Keys.ARROW_DOWN); // Уменьшение числа
        assert Objects.equals(inputField.getAttribute("value"), "123")
                : "Значение должно быть 123 после Arrow Down";

        inputField.clear();
        inputField.sendKeys("abc"); // Ввод текста
        assert Objects.requireNonNull(inputField.getAttribute("value")).isEmpty()
                : "Поле ввода должно быть пустым для нечисловых значений";

        // Проверка частичного ввода чисел и букв
        inputField.clear();
        inputField.sendKeys("12abc"); // Ввод числа и текста
        assert Objects.equals(inputField.getAttribute("value"), "12")
                : "Поле ввода должно содержать только число 12";
    }

    @AfterMethod
    public void quit() {
        driver.quit();
    }
}
