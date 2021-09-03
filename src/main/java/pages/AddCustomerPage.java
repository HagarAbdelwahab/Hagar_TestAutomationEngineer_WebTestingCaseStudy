package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.asserts.SoftAssert;

import javax.swing.*;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static util.datareaders.ElementReader.readElement;

public class AddCustomerPage {
    WebDriver driver;
    private static final SoftAssert softAssertions = new SoftAssert();
    public static Wait wait;
    public static String firstName;
    public static String lastName;
    public static String postalCode;
    public static String customerID;

    public AddCustomerPage(WebDriver driver) {
        this.driver = driver;

    }

    public void fillInCustomerForm() {
        firstName = "Hagar";
        lastName = "Abdelwahab";
        postalCode = "1122";
        wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(12))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(Exception.class);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(readElement("addCustomerButtonLocator"))));
        driver.findElement(By.xpath(readElement("addCustomerButtonLocator"))).click();
        Helper.takeScreenShot("customer", driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(readElement("firstNameXPathLocator"))));
        driver.findElement(By.xpath(readElement("firstNameXPathLocator"))).sendKeys(firstName);
        //  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(readElement("secondNameXPathLocator"))));
        driver.findElement(By.xpath(readElement("secondNameXPathLocator"))).sendKeys(lastName);
        driver.findElement(By.xpath(readElement("postalCodeXPathLocator"))).sendKeys(postalCode);
        driver.findElement(By.xpath(readElement("addCustomer"))).click();
        String text = driver.switchTo().alert().getText();
        customerID = text.split(":", 0)[1];
        System.out.println(customerID);
        driver.switchTo().alert().accept();
        driver.findElement(By.xpath(readElement("customersTab"))).click();

//        WebElement slider = driver.findElement(By.className("marTop ng-scope"));
//        Actions move = new Actions(driver);
//        Action action = (Action) move.clickAndHold().moveToElement(driver.findElement(By.xpath("//td[contains(.,'Hagar')]")));

//        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("marTop ng-scope")));
//        WebElement horizontalbar = driver.findElement(By.className("marTop ng-scope") );
//        Actions action = new Actions(driver);
//        Actions moveToElement = action.moveToElement( horizontalbar );
//        for (int i = 0; i < 5; i++) {
//            moveToElement.sendKeys(Keys.DOWN).build().perform();
//        }
    }

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
//        for (WebElement element2 : elementList2) {
//            System.out.println(element2.getText());
//            if (element2.getText().equalsIgnoreCase(firstName))
//                System.out.println("elemet is :" + elementList2.indexOf(element2));
//            softAssertions.assertEquals(elementList2.indexOf(element2), Integer.parseInt(customerID) - 1);
//            softAssertions.assertAll();
//        }
        //  List<WebElement> elementList = driver.findElements(By.xpath(readElement("customerRow")));
//        softAssertions.assertEquals(elementList.get(Integer.parseInt(customerID) - 1).findElement(By.xpath("./td[1]")).getText(), firstName);
//        softAssertions.assertEquals(elementList.get(Integer.parseInt(customerID) - 1).findElement(By.xpath("./td[2]")).getText(), lastName);
//        softAssertions.assertEquals(elementList.get(Integer.parseInt(customerID) - 1).findElement(By.xpath("./td[3]")).getText(), postalCode);
//        softAssertions.assertAll();
    }

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
}
