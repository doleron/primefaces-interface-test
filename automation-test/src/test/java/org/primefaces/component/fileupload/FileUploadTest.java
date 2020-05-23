package org.primefaces.component.fileupload;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;

public abstract class FileUploadTest {
	
	private static ChromeDriver chromeDriver;
	private static FirefoxDriver firefoxDriver;
    
    protected WebDriver driver;
    protected static URL baseUrl;
    private static boolean quitBrowserAfterRun;
    private static boolean headless;

    @BeforeClass
    public static void setUp() throws Exception {
        
        String baseUrlParam = System.getProperty("selenium.base.url");
        if (baseUrlParam == null || baseUrlParam.trim().isEmpty()) {
            throw new RuntimeException("selenium.base.url not found in System.getProperty");
        }
        baseUrl = new URL(baseUrlParam.trim());

        String quitBrowserAfterRunParam = System.getProperty("selenium.quit.browser.after.run");
        quitBrowserAfterRun = quitBrowserAfterRunParam == null
                || quitBrowserAfterRunParam.equalsIgnoreCase("true");

        String headlessParam = System.getProperty("selenium.run.headless");
        headless = headlessParam == null || headlessParam.equalsIgnoreCase("true");

        initializeFirefoxDriver();
        initializeChromeDriver();
    }

   private static void initializeFirefoxDriver() {
    	FirefoxOptions options = new FirefoxOptions();
    	options.addArguments("window-size=1024x768");
        if(headless) {
        	options.addArguments("--headless");
        }
    	firefoxDriver = new FirefoxDriver(options);
        commonsDriverSettings(firefoxDriver);
    }

	private static void initializeChromeDriver() throws MalformedURLException {
		ChromeOptions options = new ChromeOptions();
        options.addArguments("window-size=1024x768");
        options.addArguments("--no-sandbox");
        if(headless) {
        	options.addArguments("headless");
        }
        
        chromeDriver = new ChromeDriver(options);
        commonsDriverSettings(chromeDriver);
	}
    
    private static void commonsDriverSettings(WebDriver webDriver) {
    	webDriver.manage().deleteAllCookies();
    	webDriver.manage().timeouts().pageLoadTimeout(2, TimeUnit.SECONDS);
    	webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }
    
    protected void init(String viewId) throws MalformedURLException {
        URL pageAddress = new URL(baseUrl, viewId);
        this.driver.get(pageAddress.toString());
    }

    @Test
    public void testChrome() throws Exception {
        this.driver = chromeDriver;
        this.execute();
    }

    @Test
    public void testFirefox() throws Exception {
    	this.driver = firefoxDriver;
        this.execute();
    }

    public abstract void execute() throws Exception;

	@AfterClass
	public static void tearDown() throws Exception {
		if (quitBrowserAfterRun) {
			try {
				firefoxDriver.quit();
			} catch (Error e) {
				e.printStackTrace();
			}
			try {
				firefoxDriver.quit();
			} catch (Error e) {
				e.printStackTrace();
			}
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
                absolutePaths.append("\n");
            }
        }
        String absolutePath = absolutePaths.toString();
		inputElement.sendKeys(absolutePath);
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
