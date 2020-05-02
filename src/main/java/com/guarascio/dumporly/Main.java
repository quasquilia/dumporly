package com.guarascio.dumporly;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        String userName = "";
        String password = "";
        String baseUrl = "https://learning.oreilly.com/videos/clean-code/9780134661742/9780134661742-CODE_01_00_00";

        WebDriverManager.chromedriver().version("81").setup();
        WebDriver driver = new ChromeDriver();

        driver.get(baseUrl);

        // TODO Perform authentication
        new Authenticator(driver).authenticate(userName, password);
/*
        while(true) {
            System.out.println(driver.getCurrentUrl());
            Thread.sleep(5000);
            WebElement element = driver.findElement(By.className("ContentNavigation-title-101_S"));
            element.click();
        }
*/
        Thread.sleep(5000);
        driver.close();
    }
}
