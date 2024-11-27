package addRemoveElements;

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

public class AddRemoveElementsTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void addRemoveElements(){
        By addElementButton = By.xpath("//button[text()='Add Element']");
        By deleteButton = By.xpath("//button[text()='Delete']");

        WebElement addButton = driver.findElement(addElementButton);
        addButton.click();
        addButton.click();

        List<WebElement> deleteButtons = driver.findElements(deleteButton);
        Assert.assertEquals(deleteButtons.size(), 2,
                "Ожидается 2 кнопки 'Delete' после добавления.");

        deleteButtons.get(0).click();

        deleteButtons = driver.findElements(deleteButton);
        Assert.assertEquals(deleteButtons.size(), 1,
                "Ожидается 1 кнопка 'Delete' после удаления.");
    }

    @AfterMethod
    public void quit() {
        driver.quit();
    }
}
