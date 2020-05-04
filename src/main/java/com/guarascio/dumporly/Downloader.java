package com.guarascio.dumporly;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class Downloader {

    private final WebDriver driver;

    public Downloader (WebDriver driver) {
        this.driver = driver;
    }


    public void download(String url, String fileName) {

        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        String originalTab = tabs.get(1);
        driver.switchTo().window(originalTab);
        driver.get("https://keepv.id/");

        WebElement urlInput = driver.findElement(By.id(
                "dlURL"));

        urlInput.sendKeys(url);

        WebElement goBtn = driver.findElement(By.id(
                "dlBTN1"));
        goBtn.click();

        driver.switchTo().window(originalTab);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(d -> d.findElement(By.className("table")));

        WebElement table = driver.findElement(By.className("table"));

        int index = 0;
        List<WebElement> tableRows = table.findElements(By.tagName("tr"));
        WebElement row = tableRows.get(index);
        WebElement btnDownload = row.findElement(By.xpath("//a[text()='download']"));
        Actions builder = new Actions(driver);
        Action altClick = builder
                .keyDown(Keys.ALT)
                .click(btnDownload)
                .keyUp(Keys.ALT)
                .build();

        altClick.perform();
    }



}
