package org.example;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import java.util.logging.Level;

public class SeleniumTest {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    JavascriptExecutor js;
    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "/Users/jbcrouigneau/IdeaProjects/chromedriver");
        //driver = new ChromeDriver();
        driver = new HtmlUnitDriver(true);
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);      System.setProperty("org.apache.commons.logging.Log","org.apache.commons.logging.impl.NoOpLog");
        baseUrl = "http://robot.progideo.com/Accueil.php";
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        js = (JavascriptExecutor) driver;
    }

    @Test
    public void testUntitledTest() throws Exception {
        driver.get("http://robot.progideo.com/Accueil.php");
        driver.findElement(By.id("newCarte")).click();
        assertEquals("Orientation du robot : nord", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='y\\x'])[1]/following::h3[2]")).getText());
        driver.get("http://robot.progideo.com/generer.php");
        driver.get("http://robot.progideo.com/Accueil.php");
        driver.findElement(By.linkText("Avancer")).click();
        driver.findElement(By.linkText("Reculer")).click();
        driver.findElement(By.linkText("Tourner droite")).click();
        driver.findElement(By.linkText("Tourner gauche")).click();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
