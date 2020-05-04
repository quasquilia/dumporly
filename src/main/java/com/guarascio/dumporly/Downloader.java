package com.guarascio.dumporly;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Path;
import java.util.*;

public class Downloader {

    private static final String EXTENSION = "mp4";
    private final WebDriver driver;

    public Downloader(WebDriver driver) {
        this.driver = driver;
    }


    public void download(String url, String fileName) throws InterruptedException {

        ((JavascriptExecutor) driver).executeScript("window.open()");
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

        System.out.print("Downloading " + fileName + " ");
        String downloadFolder = System.getProperty("user.home") + "/Downloads";
        do {
            System.out.print(".");
            Thread.sleep(2000);
        } while (findDownloadingFiles(downloadFolder, EXTENSION).length > 0);
        System.out.println("done");

        File downloadedFile = getMostRecentFile(downloadFolder, EXTENSION).get();
        downloadedFile.renameTo(new File(downloadedFile.getParent(), fileName+ "." + EXTENSION));
    }

    private File[] findDownloadingFiles(String dirName, String extension) {
        File dir = new File(dirName);
        return dir.listFiles((dir1, filename) -> filename.endsWith("." + extension + ".crdownload"));

    }

    private Optional<File> getMostRecentFile(String dirName, String extension) {
        File directory = new File(dirName);
        File[] files = directory.listFiles(pathname
                -> pathname.isFile()
                && pathname.getName().toLowerCase().endsWith(extension.toLowerCase())
        );

        return Arrays.stream(files)
                .max(Comparator.comparing(File::lastModified));
    }
}
