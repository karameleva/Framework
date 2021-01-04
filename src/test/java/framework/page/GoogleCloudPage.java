package framework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GoogleCloudPage extends AbstractPage {

    private static final String HOMEPAGE_URL = "https://cloud.google.com";
    private static final String SEARCH_REQUEST = "Google Cloud Platform Pricing Calculator";

    @FindBy(xpath = "//input[@name='q']")
    private WebElement search;

//    @FindBy(xpath = "//div[@class='gsc-resultsbox-visible']")
//    private WebElement searchResults;
//
//    @FindBy(css = "#suggestion-product-0 .devsite-suggestion-fragment:nth-child(1)")
//    private WebElement directLinkSearchResults;

    @FindBy(xpath = "//form[@class='devsite-search-form']//*[@id='devsite-suggest-header-product']/..//*[@id='suggestion-product-0']")
    private WebElement requiredProductLink;

    public GoogleCloudPage(WebDriver driver) {
        super(driver);
    }

    public GoogleCloudPage openPage(){
        driver.get(HOMEPAGE_URL);
        waitElementToBeClickable(search);
        logger.info("Open page " + HOMEPAGE_URL);
        return this;
    }
    public GoogleCloudPage makeSearchRequest(){

        search.click();
        search.sendKeys(SEARCH_REQUEST);
        return this;
    }

    public CalculatorPage openCalculatorPage(){
        //waitForPresenceOfElement(searchResults);
        //driver.findElement(By.linkText(SEARCH_REQUEST)).click();

        waitElementToBeClickable(requiredProductLink).click();
//        new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.elementToBeClickable(requiredProductLink));
//        requiredProductLink.click();

        logger.info("Open page " + SEARCH_REQUEST);
        return new CalculatorPage(driver);
    }
}
