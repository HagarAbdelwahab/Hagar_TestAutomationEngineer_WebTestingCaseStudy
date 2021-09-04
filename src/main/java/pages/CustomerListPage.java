package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static pages.HomePage.*;
import static pages.AddCustomerPage.*;
import static pages.OpenAccountPage.accountNumber;
import static util.datareaders.ElementReader.readElement;

public class CustomerListPage {
    WebDriver driver;

    public CustomerListPage(WebDriver driver) {
        this.driver = driver;
    }

    private static final SoftAssert softAssertions = new SoftAssert();

    /**
     * this method is to check the order of the just registered user to make sure that its order in the list matched customer number
     */
    public void checkUserOrder() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(readElement("searchCustomersField"))));
        System.out.println("custmer id: " + customerID);
        List<WebElement> elementList2 = driver.findElements(By.xpath(readElement("firstNameInCustomerSearchResults")));
        List<WebElement> elementList3 = driver.findElements(By.xpath(readElement("lastNameInCustomerSearchResults")));
        List<WebElement> elementList4 = driver.findElements(By.xpath(readElement("postalCodeInCustomerSearchResults")));
        for (int i = 0; i < elementList2.size(); i++) {
            if (elementList2.get(i).getText().equalsIgnoreCase(firstName) && elementList3.get(i).getText().equalsIgnoreCase(lastName) && elementList4.get(i).getText().equalsIgnoreCase(postalCode)) {
                System.out.println("indec pd " + i);
                softAssertions.assertEquals(i + 1, Integer.parseInt(customerID));
                softAssertions.assertAll();
            }
        }
    }

    /**
     * this method is to check customer info first, second, postal code in when search for it
     */
    public void checkCustomerInfo() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(readElement("searchCustomersField"))));
        System.out.println(driver.findElement(By.xpath(readElement("searchCustomersField"))).getAttribute("placeholder"));
        driver.findElement(By.xpath(readElement("searchCustomersField"))).click();
        driver.findElement(By.xpath(readElement("searchCustomersField"))).sendKeys("Hagar");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(readElement("firstNameInCustomerSearchResults"))));
        softAssertions.assertEquals(driver.findElement(By.xpath(readElement("firstNameInCustomerSearchResults"))).getText(), firstName);
        softAssertions.assertEquals(driver.findElement(By.xpath(readElement("lastNameInCustomerSearchResults"))).getText(), lastName);
        softAssertions.assertEquals(driver.findElement(By.xpath(readElement("postalCodeInCustomerSearchResults"))).getText(), postalCode);
        softAssertions.assertAll();
    }

    /*
     * this method is to check customer account number returned while adding a new account by bank manager
     */
    public void checkCustomerAccountNumber() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(readElement("searchCustomersField"))));
        driver.findElement(By.xpath(readElement("searchCustomersField"))).sendKeys("Hagar");
        softAssertions.assertEquals(driver.findElement(By.xpath(readElement("accountNumberSearchResults"))).getText(), accountNumber);
        softAssertions.assertAll();
    }

    /**
     * this method is to delete account by bank manager
     */
    public void deleteTheCreatedCustomer() {
        driver.findElement(By.xpath(readElement("searchCustomersField"))).clear();
        driver.findElement(By.xpath(readElement("searchCustomersField"))).sendKeys("Hagar");
        driver.findElement(By.xpath(readElement("deleteButton"))).click();
        softAssertions.assertTrue(driver.findElements(By.xpath(readElement("firstNameInCustomerSearchResults"))).isEmpty());
        softAssertions.assertAll();
    }
}
