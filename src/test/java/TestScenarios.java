import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.xml.dom.Tag;
import pages.*;

public class TestScenarios extends BaseTest {
    HomePage home;
    BankInterfacesPage banking;
    AddCustomerPage customer;
    CustomerListPage customerList;
    OpenAccountPage openAccountPage;

    /*
     *Using the same base test web driver for the pages
     */
    @BeforeClass
    public void createAnInstanceOfEachPage() {
        home = new HomePage(driver);
        banking = new BankInterfacesPage(driver);
        customer = new AddCustomerPage(driver);
        customerList = new CustomerListPage(driver);
        openAccountPage = new OpenAccountPage(driver);

    }

    @Test(priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test the first scenario of adding a new customer")
    @Tag(name = "TC_01")
    public void test1() {
        home.openHomePage();
        home.openBankingLink();
        banking.loginAsAManager();
        customer.fillInCustomerForm();
        customerList.checkUserOrder();
        customerList.checkCustomerInfo();
    }

    @Test(dependsOnMethods = {"test1"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test the second scenario of adding a bank account to the customer and validating customer info")
    @Tag(name = "TC_02")
    public void test2() {
        openAccountPage.addAccountToCustomer();
        customerList.checkCustomerAccountNumber();
    }

    @Test(dependsOnMethods = {"test2", "test1"})
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test the third scenario of deleting the user just created")
    @Tag(name = "TC_03")
    public void test3() {

        customerList.deleteTheCreatedCustomer();
    }


}
