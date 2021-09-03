package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static util.datareaders.ElementReader.readElement;

public class BankInterfacesPage {
    WebDriver driver;

    public BankInterfacesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void loginAsAManager() {
        Wait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(12))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(Exception.class);
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1)); //Tab number
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(readElement("bankMangerLoginLocator"))));
        driver.findElement(By.xpath(readElement("bankMangerLoginLocator"))).click();
    }
}
