package com.guarascio.dumporly;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Authenticator {
    private final WebDriver driver;

    public Authenticator(WebDriver driver) {
        this.driver = driver;
    }

    public void authenticate(String userName, String password) {
        WebElement element = driver.findElement(By.className("t-sign-in"));
        element.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(d -> d.findElement(By.name("email")));

        WebElement mail = driver.findElement(By.name(
                "email"));
        mail.sendKeys(userName);

        WebElement pwd = driver.findElement(By.name(
                "password"));
        pwd.sendKeys(password);

        WebElement signIn = driver.findElement(By.xpath(
                "//button[text()='Sign In']"));
        signIn.click();
    }
}
