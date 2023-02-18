package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;

import static common.Config.PLATFORM_AND_BROWSER;
import static constants.Constant.TimeOutVariable.IMPLICITLY_WAIT;

public class CommonActions {

    public static WebDriver createDriver(){
        WebDriver driver = null;



        switch (PLATFORM_AND_BROWSER){
            case "win_chrome":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver-110.0.5481.exe");
                driver = new ChromeDriver();
                break;
            default:
                Assert.fail("Incorrect platform, or browser name : " + PLATFORM_AND_BROWSER);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT));


        return driver;
    }
}
