package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

import static pages.Helper.readFileTestResources;
import static pages.Helper.scrollToElement;
import static util.datareaders.ElementReader.readElement;

public class HomePage {
    WebDriver driver;
    public static Wait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * this method is to open the home page of the website
     */
    public void openHomePage() {
        wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(4))
                .ignoring(Exception.class);
        driver.get(readFileTestResources("URL.properties", "url"));
    }

    /**
     * this method is to open the banking link
     */
    public void openBankingLink() {
        scrollToElement(readElement("homePageFooterIDLocator"), driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(readElement("bankingXpathLocator"))));
        driver.findElement(By.xpath(readElement("bankingXpathLocator"))).click();
    }
}
