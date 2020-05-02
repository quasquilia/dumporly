package com.guarascio.dumporly;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().version("81").setup();
        WebDriver driver = new ChromeDriver();

        String baseUrl = "https://learning.oreilly.com/videos/clean-code/9780134661742/9780134661742-CODE_01_00_00";

        driver.get(baseUrl);

        // TODO Perform authentication

        while(true) {
            System.out.println(driver.getCurrentUrl());
            Thread.sleep(5000);
            WebElement element = driver.findElement(By.className("ContentNavigation-title-101_S"));
            element.click();
        }

        //driver.close();
    }
}
