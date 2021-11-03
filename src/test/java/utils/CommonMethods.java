package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class CommonMethods {

    public static WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void openBrowser(){
        configReader.readProperties("C:\\Users\\pc\\IdeaProjects\\Batch10 TestNG\\src\\config\\config.properties");
        switch (configReader.getPropertyValue("browser")) {
            case "chrome":
               // System.setProperty("webdriver.chrome.driver", "C:\\Users\\pc\\Downloads\\chromedriver_win32 (1)version 94\\chromedriver.exe");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
               WebDriverManager.firefoxdriver().setup();
                break;
            default:
                throw new RuntimeException("Invalid browser name");
        }
            driver.get(configReader.getPropertyValue("url"));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }

        public static void sendText(WebElement element, String textTosend){
        element.clear();
        element.sendKeys(textTosend);

        }

        public static void clickMethod(WebElement element){
        element.click();
        }

        public static WebDriverWait getWait() {
            WebDriverWait wait = new WebDriverWait(driver, constants.EXPLICIT_WAIT);
            return wait;
        }

    public static void waitForClickability(WebElement element){
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void click(WebElement element){
        waitForClickability(element);
        element.click();
    }

    public static JavascriptExecutor getJSWExecuter(){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        return javascriptExecutor;
    }

    public static void jsClick(WebElement element){
        getJSWExecuter().executeScript("arguments[0].click()", element);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }

    }



