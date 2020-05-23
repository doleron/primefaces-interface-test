package org.primefaces.component.fileupload;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;

public abstract class FileUploadTest {
    
    protected WebDriver driver;
    protected URL baseUrl;
    private boolean quitBrowserAfterRun;

    @Before
    public void setUp() throws Exception {
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("window-size=1024x768");

        String headless = System.getProperty("selenium.run.headless");
        if (headless == null || headless.equalsIgnoreCase("true")) {
            options.addArguments("headless");
        }
        
        String baseUrlParam = System.getProperty("selenium.base.url");
        if (baseUrlParam == null || baseUrlParam.trim().isEmpty()) {
            throw new RuntimeException("selenium.base.url not found in System.getProperty");
        }
        this.baseUrl = new URL(baseUrlParam.trim());

        String quitBrowserAfterRunParam = System.getProperty("selenium.quit.browser.after.run");
        this.quitBrowserAfterRun = quitBrowserAfterRunParam == null
                || quitBrowserAfterRunParam.equalsIgnoreCase("true");

        this.driver = new ChromeDriver(options);
        this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    
    protected void init(String viewId) throws MalformedURLException {
        URL pageAddress = new URL(this.baseUrl, viewId);
        this.driver.get(pageAddress.toString());
    }

    @Test
    public void test() throws Exception {
        this.execute();
    }

    public abstract void execute() throws Exception;

    @After
    public void tearDown() throws Exception {
        try {
            if (this.quitBrowserAfterRun) {
                this.driver.quit();
            }
        } catch (Error e) {
            e.printStackTrace();
        }
    }
    
    protected void input(String clientId, String ... relativeFilePaths) {
        WebElement inputElement = this.driver.findElement(By.id(clientId));
        StringBuilder absolutePaths = new StringBuilder();
        for(int i = 0; i < relativeFilePaths.length; ++i) {
            String relativeFilePath = relativeFilePaths[i];
            Path path = Paths.get(relativeFilePath);
            absolutePaths.append(path.toAbsolutePath().toString());
            if(i < relativeFilePaths.length - 1) {
                absolutePaths.append(" \n ");
            }
        }
        inputElement.sendKeys(absolutePaths.toString());
    }
    
    protected void clickButton(By by) {
        WebElement button = this.driver.findElement(by);
        button.click();
    }
    
    protected void assertContainsText(By by, String ... texts) {
        WebElement element = this.driver.findElement(by);
        String fullText = element.getText();
        for(String text : texts) {
            assertTrue(fullText.contains(text));
        }
    }
    
    protected void assertNotContainsText(By by, String ... texts) {
        WebElement element = this.driver.findElement(by);
        String fullText = element.getText();
        for(String text : texts) {
            assertFalse(fullText.contains(text));
        }
    }
    
    protected void waitUntil(By by, String query) {
        this.waitUntil(by, query, Duration.ofSeconds(1), Duration.ofMillis(100));
    }
    
    protected void waitUntil(By by, String query, Duration timeout, Duration pollingInterval) {
        new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(2)).pollingEvery(Duration.ofMillis(100))
                .until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        Boolean result;
                        try {
                            WebElement element = driver.findElement(by);
                            String text = element.getText();
                            result = text != null && text.contains(query);
                        } catch (NoSuchElementException e) {
                            result = Boolean.FALSE;
                        }
                        return result;
                    }
                });
    }

}
