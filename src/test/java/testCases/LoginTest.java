package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.CommonMethods;
import utils.configReader;

public class LoginTest extends CommonMethods {
    @Test
    public void adminLogin(){
        LoginPage loginPage = new LoginPage();
        sendText(loginPage.usernamebox, configReader.getPropertyValue("username"));
        sendText(loginPage.passwordbox, configReader.getPropertyValue("password"));
        clickMethod(loginPage.loginBtn);
        DashboardPage dashboardPage = new DashboardPage();
        Assert.assertTrue(dashboardPage.welcomeMessage.isDisplayed());
    }
    @DataProvider
    public Object[][] invalidData(){
        Object[][] data = {
                {"James", "123!", "Invalid credentials"},
                {"Admin1", "Hum@nhrm123", "Invalid credentials"},
                {"Admin", "", "Password cannot be empty"},
                {"", "Hum@nhrm123", "Username cannot be empty"}
        };
        return data;
    }

    @Test(dataProvider = "invalidData")
    public void invalidLoginErrorMessageValidation(String username, String password, String message){
        LoginPage loginPage = new LoginPage();
        loginPage.login(username, password);
        String actualError = loginPage.errorMessage.getText();
        Assert.assertEquals(actualError, message, "Error message does not match");
    }


}
