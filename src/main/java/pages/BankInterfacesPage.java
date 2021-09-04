package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.*;

import java.util.ArrayList;

import static pages.HomePage.*;
import static util.datareaders.ElementReader.readElement;

public class BankInterfacesPage {
    WebDriver driver;

    public BankInterfacesPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * this method is to login as a manage to add,view users and add accounts to them
     */
    public void loginAsAManager() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1)); //Tab number
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(readElement("bankMangerLoginLocator"))));
        driver.findElement(By.xpath(readElement("bankMangerLoginLocator"))).click();
    }
}
