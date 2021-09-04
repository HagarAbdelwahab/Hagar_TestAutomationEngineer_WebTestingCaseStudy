package pages;

import org.openqa.selenium.*;
import util.constants.Constants;
import util.datareaders.PropertiesReader;


public class Helper {

    /**
     * this method is to read properties files in the test folder
     *
     * @param fileName     name of the property file in the property folder
     * @param propertyName name of the property needed to return its value
     */
    public static String readFileTestResources(String fileName, String propertyName) {
        PropertiesReader.readProperties(Constants.TEST_RESOURCES_PROPERTIES + fileName);
        return PropertiesReader.getPropertyValue(propertyName);
    }

    /**
     * this method is to scroll to specific element in pom
     *
     * @param locator of the element needed to scroll to
     */
    public static void scrollToElement(String locator, WebDriver driver) {
        WebElement element;
        if (locator.contains("//"))
            element = driver.findElement(By.xpath(locator));
        else
            element = driver.findElement(By.id(locator));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    /**
     * this method is to generate a random number in a range between min and max number
     *
     * @param min min value a random number can be equal to
     * @param max max value a random number can be equal to
     */
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}
