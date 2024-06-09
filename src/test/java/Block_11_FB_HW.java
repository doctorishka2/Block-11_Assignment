import net.bytebuddy.asm.MemberSubstitution;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class Block_11_FB_HW {

    private static final String HOME_PAGE_FB_URL = "https://www.facebook.com/";

    private static WebDriver driver;


    @BeforeAll
    public static void classSetup() {
        driver = SharedDriver.getWebDriver();
        //driver.get(HOME_PAGE_FB_URL);
    }

    @BeforeEach
    public void startHomePage() {
        driver.get(HOME_PAGE_FB_URL);
    }

//    @AfterAll
//    public static void classTearDown() throws InterruptedException {
//        Thread.sleep(3000);
//        SharedDriver.closeDriver();
//    }

    @Test
    public void clickOnCreateAccountButtonTest() {
        WebElement createAccountButton = driver.findElement(By.xpath("//*[text()='Create new account']"));
        assertNotNull(createAccountButton);
        createAccountButton.click();
    }


    @Test
    public void fillSignUpFormTest() throws InterruptedException {
        clickOnCreateAccountButtonTest();
        //Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@name='websubmit']")));

        WebElement elementSignUpButton = driver.findElement(By.xpath("//*[@name='websubmit']"));
        assertNotNull(elementSignUpButton);
        elementSignUpButton.click();
    }

    @Test
    public void verifySignUpErrorMessagesTest() throws InterruptedException {
        fillSignUpFormTest();
        //'First name' field click
        WebElement firstNameField = driver.findElement(By.xpath("//*[@name='firstname']"));
        assertNotNull(firstNameField);
        firstNameField.click();

        //Wait for element
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'your name?')]")));
        //Verify error message
        WebElement firstNameFieldError = driver.findElement(By.xpath("//*[contains(text(), 'your name?')]"));
        assertNotNull(firstNameFieldError);

        //'Last name' field click
        WebElement lastNameField = driver.findElement(By.xpath("//*[@name='lastname']"));
        assertNotNull(lastNameField);
        lastNameField.click();

        //Wait for element
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'your name?')]")));

        //Verify error message
        WebElement lastNameFieldError = driver.findElement(By.xpath("//*[contains(text(), 'your name?')]"));
        assertNotNull(lastNameFieldError);

        //'Month' field click
        WebElement monthField = driver.findElement(By.xpath("//*[@name='birthday_month']"));
        assertNotNull(monthField);
        monthField.click();

        //Wait for element
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'It looks like')]")));

        //Verify error message
        WebElement monthFieldError = driver.findElement(By.xpath("//*[contains(text(), 'It looks like')]"));
        assertNotNull(monthFieldError);

        //'Mobile number or email' field click
        WebElement mobileNumberField = driver.findElement(By.xpath("//*[@name='reg_email__']"));
        assertNotNull(mobileNumberField);
        mobileNumberField.click();

        //Wait for element
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), \"You'll use this when you log in\")]")));

        //Verify error message
        WebElement mobileNumberFieldError = driver.findElement(By.xpath("//*[contains(text(), 'use this when you log in')]"));
        assertNotNull(mobileNumberFieldError);

        //'New password' field click
        WebElement newPasswordField = driver.findElement(By.xpath("//*[@name='reg_passwd__']"));
        assertNotNull(newPasswordField);
        newPasswordField.click();

        //Wait for element
        wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//*[contains(text(), 'Enter a combination')]"))));

        //Verify error message
        WebElement newPasswordFieldError = driver.findElement(By.xpath("//*[contains(text(), 'Enter a combination')]"));
        assertNotNull(newPasswordFieldError);

        //'Gender' error message "Please choose a gender"

    }

    @Test
    public void genderErrorMessageTest() {
        //Fill 'Sign up' form excluding 'Gender' then click on 'Sign Up' button: gender error message pops up. Verify genderErrorMessage
    }


    @Test
    public void monthDropDownListTest() {
        clickOnCreateAccountButtonTest();

        //Select May in month in ddl
        Select monthDropDownList = new Select(driver.findElement(By.xpath("//*[@id='month']")));
        assertNotNull(monthDropDownList);
        monthDropDownList.selectByIndex(4);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1905", "1955", "2024"})
    public void yearDropDownListTest(String yearInput) throws InterruptedException {
        clickOnCreateAccountButtonTest();

        //Select years 1905, 1955, 2024)
        driver.findElement(By.xpath("//*[@title='Year']")).click();
        driver.findElement(By.xpath("//*[text()='" + yearInput + "']")).click();
        String yearValue = driver.findElement(By.xpath("//*[@title='Year']")).getAttribute("value");
        assertEquals(yearInput, yearValue);

    }

    @Test
    public void radioButtons() throws InterruptedException {
        String femaleXpath = "//*[@name='sex' and @value=1]";
        String maleXpath = "//*[@name='sex' and @value=2]";
        String customXpath = "//*[@name='sex' and @value=-1]";


        clickOnCreateAccountButtonTest();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Sign Up')]")));

        driver.findElement(By.xpath(femaleXpath)).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath(maleXpath)).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath(customXpath)).click();
        Thread.sleep(2000);
    }

    @Test
    public void termsTest() throws InterruptedException {
        String termsLink = "//*[@href='/legal/terms/update']";

        clickOnCreateAccountButtonTest();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Sign Up')]")));

        Thread.sleep(1000);
        driver.findElement(By.xpath(termsLink)).click();

        //Verify 'Terms' page presents
        for(String str : driver.getWindowHandles()){
            driver.switchTo().window(str);
        }
        driver.getCurrentUrl();
        System.out.println(driver.getCurrentUrl());

        if (driver.getCurrentUrl().contains("https://www.facebook.com/legal/terms")) {
            System.out.println("Test PASSED");
        } else {
            System.out.println("Test FAILED");
        }

    }

    @Test
    public void privacyPolicyTest() throws InterruptedException {
        //String privacyPolicyLink = "//*[text()='Privacy Policy']";
        String privacyPolicyLink = "//*[@href='/about/privacy/update']";

        clickOnCreateAccountButtonTest();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Sign Up')]")));

        Thread.sleep(2000);
        driver.findElement(By.xpath(privacyPolicyLink)).click();

        //Verify 'Privacy Policy' page presents
        for(String str : driver.getWindowHandles()){
            driver.switchTo().window(str);
        }
        driver.getCurrentUrl();
        assertNotNull(driver.getCurrentUrl());
        System.out.println(driver.getCurrentUrl());

        if (driver.getCurrentUrl().contains("https://www.facebook.com/privacy/policy/")) {
            System.out.println("Test PASSED");
        } else {
            System.out.println("Test FAILED");
        }

    }







}


