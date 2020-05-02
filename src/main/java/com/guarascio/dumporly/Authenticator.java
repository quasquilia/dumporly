package com.guarascio.dumporly;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Authenticator {
    private final WebDriver driver;

    public Authenticator(WebDriver driver) {
        this.driver = driver;
    }

    public void authenticate() {

        WebElement element = driver.findElement(By.className("t-sign-in"));
        element.click();
    }
}
