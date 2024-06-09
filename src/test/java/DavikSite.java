import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DavikSite {


    private static final String HOME_PAGE_DAVIK_URL = "https://www.daviktapes.com/";

    private static WebDriver driver;


    @BeforeAll
    public static void classSetup() {
        driver = SharedDriver.getWebDriver();
        //driver.get(HOME_PAGE_DAVIK_URL);
    }

    @BeforeEach
    public void startHomePage() {
        driver.get(HOME_PAGE_DAVIK_URL);
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body")));

    }


@Test
    public void companyActionTest() {

    WebElement companyTopMenuOption = driver.findElement(By.xpath("//*[text()='Company']"));
    assertNotNull(driver.findElement(By.xpath("//*[text()='Company']")));
    Actions actions = new Actions(driver);
    actions.moveToElement(companyTopMenuOption).build().perform();
}

    @Test
    public void productsActionTest() {

        WebElement productsTopMenuOption = driver.findElement(By.xpath("//*[text()='Products']"));
        assertNotNull(driver.findElement(By.xpath("//*[text()='Products']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(productsTopMenuOption).build().perform();
    }

    @Test
    public void industriesActionTest() {

        WebElement industriesTopMenuOption = driver.findElement(By.xpath("//*[text()='Industries']"));
        assertNotNull(driver.findElement(By.xpath("//*[text()='Industries']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(industriesTopMenuOption).build().perform();
    }

    @Test
    public void knowledgeCenterActionTest() {

        WebElement knowledgeCenterTopMenuOption = driver.findElement(By.xpath("//*[text()='Knowledge Center']"));
        assertNotNull(driver.findElement(By.xpath("//*[text()='Knowledge Center']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(knowledgeCenterTopMenuOption).build().perform();
    }

















}
