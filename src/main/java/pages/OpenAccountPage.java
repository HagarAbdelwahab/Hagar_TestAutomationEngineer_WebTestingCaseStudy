package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import static pages.AddCustomerPage.*;

import java.time.Instant;

import static util.datareaders.ElementReader.readElement;

public class OpenAccountPage {
    WebDriver driver;
    public static String accountNumber;
    private static final SoftAssert softAssertions = new SoftAssert();

    public OpenAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addAccountToCustomer() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(readElement("openAccount"))));
        driver.findElement(By.xpath(readElement("openAccount"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(readElement("customerNameSelectMenu"))));
        Select customerDropDownMenu = new Select(driver.findElement(By.id(readElement("customerNameSelectMenu"))));
        customerDropDownMenu.selectByVisibleText("Hagar Abdelwahab");
        Select currencyDropDownMenu = new Select(driver.findElement(By.id(readElement("currencyDropSelectMenu"))));
        currencyDropDownMenu.selectByIndex(Helper.getRandomNumber(1, 3));
        driver.findElement(By.xpath(readElement("processButton"))).click();
        String text = driver.switchTo().alert().getText();
        accountNumber = text.split(":", 0)[1];
        System.out.println(accountNumber);
        driver.switchTo().alert().accept();
        driver.findElement(By.xpath(readElement("customersTab"))).click();
    }

}
