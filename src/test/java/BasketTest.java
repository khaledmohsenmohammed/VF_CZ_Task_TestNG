// Import Allure annotations for reporting test metadata
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
// Import Selenium classes for browser interaction
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
// Import TestNG for managing test lifecycle and assertions
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
// Import Java time package for timeout durations
import java.time.Duration;

// Class definition for testing the basket functionality
public class BasketTest {
    // Define WebDriver instance to interact with the browser
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Set the ChromeDriver executable path for browser automation
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        // Maximize the browser window for better visibility
        driver.manage().window().maximize();
        // Open the Vodafone Czech Republic website
        driver.get("https://www.vodafone.cz/");
    }
    @Test
    @Description("Test to verify adding an iPhone to the basket.")
    @Feature("Shopping Cart")
    @Story("User should be able to add an iPhone to the cart.")
    public void addToBasket() throws InterruptedException {
        // Initialize explicit wait with a timeout of 10 seconds
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the cookies pop-up to appear and click the accept button
        WebElement cookiesPopUp = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id='homepage']/div[8]/div/p[2]/button[1]")));
        cookiesPopUp.click();

        // Wait for the "Obchod" (Shop) menu item to appear and hover over it
        WebElement obchod = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Obchod']")));
        System.out.println("*********************************>> " + obchod.getText());
        Actions actions = new Actions(driver);
        actions.moveToElement(obchod).perform();

        // Wait for the iPhone menu item to become clickable and click it
        WebElement iphone = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='vf-header']/div[3]/div/div[1]/div[2]/div/nav/ul/li[4]/ul/li[2]/ul/li[1]/a")));
        System.out.println("*********************************>> " + iphone.getText());
        actions.click(iphone).perform();

        // Wait for the first iPhone product link to appear and click it
        WebElement firstIphone = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id='product_apple-iphone-16-128-gb:iphone16128b']/div/div[2]/div/p[1]/a")));
        firstIphone.click();

        // Validate that the page title contains "iPhone"
        try {
            String pageTitle = driver.getTitle();
            Assert.assertTrue(pageTitle.contains("iPhone"), "The page title does not contain 'iPhone'.");
            System.out.println("Assertion passed. The page title contains 'iPhone'.");
        } catch (AssertionError e) {
            System.err.println("Assertion failed: " + e.getMessage());
        }

        // Wait for the "Add to Cart" button to be clickable and click it
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@class='add_to_cart_button button smallTypo' and contains(@href, 'cart=add')]")));
        addButton.click();
        Thread.sleep(2000);
        // Wait for header element on the new page and assert its text
        try {
            WebElement headerElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//*[@id=\"__next\"]/div[2]/div[2]/div/div/div/div[2]/div[1]/div/div[2]/div/div[2]/div/div/div[2]/h1")));
            String actualText = headerElement.getText();
            System.out.println("************************ === " + actualText);
            Assert.assertEquals(actualText, "Do košíku jste vložili novou položku", "The header text does not match.");
            System.out.println("Assertion passed. The header contains the expected text.");
        } catch (AssertionError e) {
            System.err.println("Assertion failed: " + e.getMessage());
            throw e; // Rethrow to fail test
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            throw new RuntimeException("Test failed due to an unexpected error.", e);
        }
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser after each test
        driver.quit();
    }
}
