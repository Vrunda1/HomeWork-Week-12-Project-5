package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

public class LoginTest extends Utility {
    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void openUrl() {
        openBrowser(baseUrl);
    }

    @Test
    public void userSholdLoginSuccessfullyWithValidCredentials() {
        //Enter  username
        sendTextToElement(By.xpath("//input[@id='username']"), "tomsmith");

        //Enter password
        sendTextToElement(By.xpath("//input[@id='password']"), "SuperSecretPassword!");

        //Click on ‘LOGIN’ button
        clickOnElement(By.xpath("//i[contains(text(),'Login')]"));

        //Verify the text “Secure Area”
        String expectedAreaResult = "Secure Area";

        // WebElement area = driver.findElement(By.xpath("//body[1]/div[2]/div[1]/div[1]/h2[1]"));
        String actualAreaResult = getTextFromElement(By.xpath("//body[1]/div[2]/div[1]/div[1]/h2[1]"));

        Assert.assertEquals(expectedAreaResult, actualAreaResult);
    }

    @Test
    public void verifyTheUsernameErrorMessage() {

        //Enter  username incorrect
        sendTextToElement(By.xpath("//input[@id='username']"), "tomsmith1");
        //Enter password
        sendTextToElement(By.xpath("//input[@id='username']"), "SuperSecretPassword");
        //Click on ‘LOGIN’ button
        clickOnElement(By.xpath("//i[contains(text(),'Login')]"));
        //Verify the error message
        String expectedAreaResult = "Your username is invalid!\n" +
                "×";
        String actualMessage = getTextFromElement(By.xpath("//div[@id='flash']"));

        Assert.assertEquals(expectedAreaResult, actualMessage);
    }

    @Test
    public void verifyThePasswordErrorMessage() {
        //Enter  username
        sendTextToElement(By.xpath("//input[@id='username']"), "tomsmith");
        //Enter password
        sendTextToElement(By.xpath("//input[@id='password']"), "SuperSecretPassword");
        //Click on ‘LOGIN’ button
        clickOnElement(By.xpath("//i[contains(text(),'Login')]"));
        //Verify the error message
        String expectedResult = "Your password is invalid!\n" +
                "×";
        String actualMessage = getTextFromElement(By.xpath("//div[@id='flash']"));
        Assert.assertEquals(expectedResult, actualMessage);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }

}