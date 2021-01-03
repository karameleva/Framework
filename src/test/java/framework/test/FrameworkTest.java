package framework.test;

import framework.model.ComputeEngine;
import framework.page.CalculatorPage;
import framework.page.GoogleCloudPage;
import framework.page.TenMinuteMailPage;
import framework.service.ComputeEngineCreator;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class FrameworkTest extends CommonConditions{

    @Test
    public void testArePricesOnWebsiteAndInEmailEqual(){

        ComputeEngine testComputeEngine = ComputeEngineCreator.withOptionsFromProperty();
        CalculatorPage calculatorPage = new GoogleCloudPage(driver)
                .openPage()
                .makeSearchRequest()
                .selectRequiredPageInSearchResults()
                .activateComputeEngineSection()
                .fillInTheForm(testComputeEngine);

        String priceFromCalculatorPage = calculatorPage.getPrice();

        ((JavascriptExecutor) driver).executeScript("window.open()");
        List<String> handles = new ArrayList<String>(driver.getWindowHandles());

        driver.switchTo().window(handles.get(1));
        TenMinuteMailPage tenMinuteMailPage = new TenMinuteMailPage(driver);
        String tenMinMail = tenMinuteMailPage.getTenMinMail();

        driver.switchTo().window(handles.get(0));
        calculatorPage.sendEmail(tenMinMail);

        driver.switchTo().window(handles.get(1));

        Assert.assertTrue(priceFromCalculatorPage.contains(tenMinuteMailPage.getPrice()), "Prices at the website and in the email are not equal");
    }

}