package testCases;

import org.testng.annotations.Test;
import pages.AddEmployeePage;
import pages.DashboardPage;
import pages.LoginPage;
import utils.CommonMethods;
import utils.configReader;

public class AddEmployeeTest extends CommonMethods {

    @Test
    public void addEmployee(){
        //login function
        LoginPage loginpage = new LoginPage();
        loginpage.login(configReader.getPropertyValue("username"), configReader.getPropertyValue("password"));

        DashboardPage dashBoardPage = new DashboardPage();
        click(dashBoardPage.PIM);
        click(dashBoardPage.addEmployee);

        //add employee page
        AddEmployeePage addEmployeePage = new AddEmployeePage();
        sendText(addEmployeePage.firstName, "test9090112");
        sendText(addEmployeePage.middleName, "9090test134");
        sendText(addEmployeePage.lastName, "test90test901435");
        click(addEmployeePage.saveButton);
    }

}
