package com.guarascio.dumporly;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().version("81").setup();
        WebDriver driver = new ChromeDriver();

        String baseUrl = "http://www.repubblica.it";

        // launch Fire fox and direct it to the Base URL
        driver.get(baseUrl);

        Thread.sleep(5000);

        driver.close();
    }
}
