package BringItOnPOM.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class PasteBinResultPage {
    WebDriver driver;


    public PasteBinResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getSyntaxHighlighting(By checkHighLighting) {
        return driver.findElement(checkHighLighting).getText();
    }

    public String getPasteText(By checkPastedText) {
        return driver.findElement(checkPastedText).getText();
    }

}