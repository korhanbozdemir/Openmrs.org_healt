package utility;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

import static pages.Parent.waiting;

public class BaseDriver {
    public static final org.apache.logging.log4j.Logger logger4j = LogManager.getLogger();

    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeClass
    public void setUpProcess() {
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.SEVERE);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    @AfterClass
    public void tearDownProcess() {
        waiting(5);
        driver.quit();
    }

    @BeforeMethod
    public void beforeMethod() {

        logger4j.info("test metodu başladı");
        logger4j.warn("warning mesaj test başladı");
    }

    @AfterMethod
    public void afterMethod(ITestResult sonuc) {
        logger4j.info(sonuc.getName() + " test metodu bitti " + (sonuc.getStatus() == 1 ? " passed " : "fail"));
        logger4j.warn("warning mesaj test bitti");
    }
}
