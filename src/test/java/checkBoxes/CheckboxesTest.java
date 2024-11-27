package checkBoxes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class CheckboxesTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void checkBoxes() {
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("[type=checkbox]"));

        WebElement firstCheckbox = checkboxes.get(0);
        Assert.assertFalse(firstCheckbox.isSelected(), "Первый чекбокс уже отмечен!");

        firstCheckbox.click();
        Assert.assertTrue(firstCheckbox.isSelected(), "Первый чекбокс не был отмечен!");

        WebElement secondCheckbox = checkboxes.get(1);
        Assert.assertTrue(secondCheckbox.isSelected(), "Второй чекбокс не был изначально отмечен!");

        secondCheckbox.click();
        Assert.assertFalse(secondCheckbox.isSelected(), "Второй чекбокс не был снят!");
    }

    @AfterMethod
    public void quit() {
        driver.quit();
    }
}
