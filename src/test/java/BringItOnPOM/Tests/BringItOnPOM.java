package BringItOnPOM.Tests;

import BringItOnPOM.Pages.PasteBinHomePage;
import BringItOnPOM.Pages.PasteBinResultPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

public class BringItOnPOM {
    WebDriver driver;
    Actions actions;

    PasteBinHomePage objPasteBinHomePage;
    PasteBinResultPage objPasteBinResultPage;

    private final String pasteNameText = "how to gain dominance among developers";
    private final String textToEnter = "git config --global user.name  \"New Sheriff in Town\"\n" +
            "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
            "git push origin master --force";
    private final String ValueOfHighlighting = "Bash";
    private final String ValueOfPastExpiration = "10 Minutes";

    By ExpandSyntaxHighlightingDropdown = By.xpath("//label[text()='Syntax Highlighting:']/..//span[@class='select2-selection__arrow']");
    By ValueOfHighlightingDropDown = By.xpath("//ul[@id='select2-postform-format-results']");
    By ExpandPasteExpirationDropdown = By.xpath("//label[text()='Paste Expiration:']/..//span[@class='select2-selection__arrow']");
    By ValueOfExpirationDropDown = By.xpath("//ul[@id='select2-postform-expiration-results']");
    By resultPresence = By.xpath("//div[@class='post-view']");
    By CreateButton = By.xpath("//button[text()='Create New Paste']");
    By PasteTextSection = By.id("postform-text");
    By ValueOfPasteNameField = By.id("postform-name");
    By SyntaxHighlightingValue = By.xpath("//div[@class='highlighted-code']//div[@class='left']/a");
    By PasteText = By.xpath("//div[@class='highlighted-code']//div[@class='source']");

    @BeforeTest
    public void browserSetup() {
        System.setProperty("webdriver.chrome.driver","D:\\_webdriver\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        actions = new Actions(driver);
        driver.get("https://pastebin.com");
        objPasteBinHomePage = new PasteBinHomePage(driver);
        actions.sendKeys(Keys.ESCAPE);
        objPasteBinResultPage = new PasteBinResultPage(driver);
    }

    @Test (priority = 3, description = "Check that Page Title correspond to Paste Name")
    public void PasteNameIsInTitle() {
        objPasteBinHomePage.PutValueIntoPasteTextArea(textToEnter, PasteTextSection);
        objPasteBinHomePage.SelectValueInDropdown(ValueOfHighlighting, ExpandSyntaxHighlightingDropdown, ValueOfHighlightingDropDown);
        objPasteBinHomePage.SelectValueInDropdown(ValueOfPastExpiration, ExpandPasteExpirationDropdown, ValueOfExpirationDropDown);
        objPasteBinHomePage.SpecifyPasteNameField(pasteNameText, ValueOfPasteNameField);
        objPasteBinHomePage.ClickButton(CreateButton, resultPresence);
        Assert.assertEquals(objPasteBinResultPage.getPageTitle(), pasteNameText + " - Pastebin.com", "Page Title is not correct");
    }

    @Test (priority = 3, description = "Check that Syntax Highlighting is valid")
    public void SyntaxHighlightingIsCorrect() {
        Assert.assertEquals(objPasteBinResultPage.getSyntaxHighlighting(SyntaxHighlightingValue), ValueOfHighlighting, "Syntax Highlighting is invalid");
    }

    @Test (priority = 3, description = "Check that Paste Text is valid")
    public void PasteTextIsCorrect(){
        Assert.assertEquals(objPasteBinResultPage.getPasteText(PasteText), textToEnter, "Paste Text is invalid");
    }

    @AfterTest
    public void browserQuit() {
        driver.quit();
        driver=null;
    }
}
