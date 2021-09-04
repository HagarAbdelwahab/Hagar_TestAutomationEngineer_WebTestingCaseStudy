package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static pages.HomePage.*;
import static util.datareaders.ElementReader.readElement;

public class AddCustomerPage {
    WebDriver driver;
    public static String firstName;
    public static String lastName;
    public static String postalCode;
    public static String customerID;

    public AddCustomerPage(WebDriver driver) {
        this.driver = driver;

    }

    /**
     * this method is to fill in the customer form to register it in the bank system
     */
    public void fillInCustomerForm() {
        firstName = "hagar";
        lastName = "abd";
        postalCode = "1122";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(readElement("addCustomerButtonLocator"))));
        driver.findElement(By.xpath(readElement("addCustomerButtonLocator"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(readElement("firstNameXPathLocator"))));
        driver.findElement(By.xpath(readElement("firstNameXPathLocator"))).sendKeys(firstName);
        driver.findElement(By.xpath(readElement("secondNameXPathLocator"))).sendKeys(lastName);
        driver.findElement(By.xpath(readElement("postalCodeXPathLocator"))).sendKeys(postalCode);
        driver.findElement(By.xpath(readElement("addCustomer"))).click();
        String text = driver.switchTo().alert().getText();
        customerID = text.split(":", 0)[1];
        System.out.println(customerID);
        driver.switchTo().alert().accept();
        driver.findElement(By.xpath(readElement("customersTab"))).click();

    }


}
