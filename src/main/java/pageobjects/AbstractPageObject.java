package pageobjects;

import common.attributes.SlandoAtt;
import common.Item;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: zapolsks
 * Date: 12/4/13
 * Time: 5:02 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractPageObject {

    protected WebDriver driver;

    public AbstractPageObject(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    protected WebElement getElement(SlandoAtt slandoAtt) {
        return driver.findElement(By.xpath(slandoAtt.getXpath()));
    }


    public void fillPage(List<Item> itemList) {
        for (Item item: itemList) {
            WebElement webElement = getElement(item.getSlandoAtt());
            webElement.clear();
            webElement.sendKeys(item.getValue());
        }
    }

    protected void waitUntilExist(final SlandoAtt slandoAtt, int seconds) {
        WebDriverWait waitWithTimeout = new WebDriverWait(driver, seconds);
        waitWithTimeout.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver) {
                return getElement(slandoAtt);
            }
        });
    }
}
