package pages;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static pages.Helper.readFileTestResources;
import static pages.Helper.scrollToElement;
import static util.datareaders.ElementReader.readElement;

public class HomePage {
    WebDriver driver;
    private static final SoftAssert softAssertions = new SoftAssert();

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void openHomePage() {
        driver.get(readFileTestResources("URL.properties", "url"));
    }

    /**
     * this is to check the suggestions used for easing the search for the used by listing the most matching keywords
     *
     */


    public void openBankingLink(){
        scrollToElement(readElement("homePageFooterIDLocator"),driver);
        driver.findElement(By.xpath(readElement("bankingXpathLocator"))).click();
    }
}
