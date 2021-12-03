package BringItOnPOM.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PasteBinHomePage {
    WebDriver driver;
    Actions actions;
    By ElementPush = By.xpath("//div[@class='notice -post-form']");

    public PasteBinHomePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    public void PutValueIntoPasteTextArea(String textValueTextArea, By presenceTextArea) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(presenceTextArea));
        driver.findElement(presenceTextArea).sendKeys(textValueTextArea);
    }

    public void SelectValueInDropdown(String textValueDropdown, By clickDropdownXPath, By presenceDropdownValue) {
        WebElement moving = driver.findElement(ElementPush);
        actions.moveToElement(moving);
        actions.perform();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(clickDropdownXPath));

        driver.findElement(clickDropdownXPath).click();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(presenceDropdownValue));

        driver.findElement(By.xpath("//li[text()='" + textValueDropdown + "']")).click();
    }

    public void SpecifyPasteNameField(String textValuePasteName, By PasteNAmeField) {
        driver.findElement(PasteNAmeField).sendKeys(textValuePasteName);
    }

    public void ClickButton(By clickButton, By resultSectionXPath) {
        driver.findElement(clickButton).click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(resultSectionXPath));
    }
}
