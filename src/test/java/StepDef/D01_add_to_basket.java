package StepDef;

// Importing Cucumber annotations for defining steps
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

// Importing Allure annotations for adding descriptions, features, and stories
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

// Importing Selenium WebDriver and other utility classes for browser interaction
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Importing TestNG assertion library for validation
import org.testng.Assert;

// Importing Java Duration class for timeout configuration
import java.time.Duration;

// This class defines the step definitions for adding an iPhone to the shopping basket on Vodafone Czech Republic's website
public class D01_add_to_basket {

    // WebDriver instance to control the browser
    private WebDriver driver;

    // WebDriverWait instance for implementing explicit waits
    private WebDriverWait wait;

    // Step to navigate to the Vodafone Czech Republic homepage
    @Given("the user is on the Vodafone Czech Republic homepage")
    @Description("Navigate to Vodafone Czech Republic homepage.")
    @Feature("Shopping Cart")
    @Story("Visit Homepage")
    public void userIsOnHomepage() {
        // Set system property for the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/driver/chromedriver.exe");
        driver = new ChromeDriver();// Launch the Chrome browser
        driver.manage().window().maximize();// Maximize the browser window
        driver.get("https://www.vodafone.cz/");// Open the Vodafone homepage

        // Initialize the WebDriverWait with a timeout of 10 seconds
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Accept cookies
        WebElement cookiesPopUp = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id='homepage']/div[8]/div/p[2]/button[1]")));
        cookiesPopUp.click();
    }

    // Step to navigate to the iPhone product page
    @When("the user navigates to the iPhone product page")
    @Description("Hover over 'Obchod' and navigate to the iPhone page.")
    public void userNavigatesToIphonePage() {
        // Locate the 'Obchod' menu and hover over it using Actions
        WebElement obchod = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Obchod']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(obchod).perform();// Perform hover action

        // Click on the iPhone link from the dropdown menu
        WebElement iphone = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='vf-header']/div[3]/div/div[1]/div[2]/div/nav/ul/li[4]/ul/li[2]/ul/li[1]/a")));
        actions.click(iphone).perform();// Perform click action
    }

    // Step to add the iPhone to the shopping basket
    @When("the user adds the iPhone to the basket")
    @Description("Select the iPhone product and add it to the basket.")
    public void userAddsIphoneToBasket() {
        // Locate the first iPhone product and click on it
        WebElement firstIphone = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id='product_apple-iphone-16-128-gb:iphone16128b']/div/div[2]/div/p[1]/a")));
        firstIphone.click();

        // Locate and click the "Add to Cart" button
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@class='add_to_cart_button button smallTypo' and contains(@href, 'cart=add')]")));
        addButton.click();// Click to add the product to the basket
    }

    // Step to verify that the basket reflects the newly added iPhone
    @Then("the basket should show the new iPhone added")
    @Description("Verify the basket reflects the added iPhone.")
    public void basketShouldShowIphoneAdded() throws InterruptedException {
        int attempts = 0; // Counter for attempts
        boolean elementFound = false; // Flag to track success

        // Retry mechanism for locating the element
        while (attempts < 2 && !elementFound) {
            try {
                Thread.sleep(2000); // Wait before trying again
                WebElement headerElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//*[@id=\"__next\"]/div[2]/div[2]/div/div/div/div[2]/div[1]/div/div[2]/div/div[2]/div/div/div[2]/h1")));
                String actualText = headerElement.getText(); // Extract text
                Assert.assertEquals(actualText, "Do košíku jste vložili novou položku", "The header text does not match.");
                elementFound = true; // Element was successfully located and validated
            } catch (Exception e) {
                attempts++; // Increment attempt counter
                if (attempts == 2) {
                    // Throw an exception if all attempts fail
                    throw new RuntimeException("Element not found after two attempts: " + e.getMessage());
                }
            }
        }

        driver.quit(); // Quit the browser session
    }
}
