package framework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleCloudPage extends AbstractPage {

    private static final String HOMEPAGE_URL = "https://cloud.google.com";
    private static final String SEARCH_REQUEST = "Google Cloud Platform Pricing Calculator";

    @FindBy(xpath = "//input[@name='q']")
    private WebElement search;

    @FindBy(xpath = "//div[@class='gsc-resultsbox-visible']")
    private WebElement searchResults;

    @FindBy(css = "#suggestion-product-0 .devsite-suggestion-fragment:nth-child(1)")
    private WebElement directLinkSearchResults;

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
        search.sendKeys(SEARCH_REQUEST + Keys.ENTER);
        return this;
    }

    public CalculatorPage selectRequiredPageInSearchResults(){
        waitForPresenceOfElement(searchResults);
        driver.findElement(By.linkText(SEARCH_REQUEST)).click();
        logger.info("Open page " + SEARCH_REQUEST);
        return new CalculatorPage(driver);
    }
}
