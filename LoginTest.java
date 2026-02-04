package com.mycompany.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {

    String baseURL = "http://example.com/login";
    String uName = "testUser";
    String pWord = "password123";

    @Test
    public void loginTest_1() throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get(baseURL);

        Thread.sleep(2000);

        driver.findElement(By.id("username-field")).sendKeys(uName);
        driver.findElement(By.id("password-field")).sendKeys(pWord);
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains("dashboard"), "Login failed! Did not navigate to dashboard.");

        driver.quit();
    }

    @Test
    public void loginTest_2() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseURL);

        Thread.sleep(3000);

        driver.findElement(By.id("username-field")).sendKeys("anotherUser");
        driver.findElement(By.id("password-field")).sendKeys("secureP@ss");
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        String errorMessage = driver.findElement(By.className("error-message")).getText();
        Assert.assertEquals(errorMessage, "Invalid credentials.", "Error message text is wrong.");

        driver.quit();
    }
}