import common.CsvReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: zapolsks
 * Date: 12/4/13
 * Time: 4:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class CsvWriteTest {

    public static final String LAPTOPS_CSV = "src/main/resources/laptops.csv";

    @Test
    public void testWrite() throws Exception {

        FirefoxDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://rozetka.com.ua/notebooks/c80004/filter/view=list/");
        List<WebElement> laptopElements = driver.findElements(By.xpath(".//*[@id='head_banner_container']/div[2]/div/div[1]/div[2]/div/div[4]/table/tbody/tr/td[2]/div/a"));

        ArrayList<String> laptopLinks = new ArrayList<String>();
        ArrayList<String> laptopNames = new ArrayList<String>();
        for (WebElement laptopElement : laptopElements) {
            laptopLinks.add(laptopElement.getAttribute("href"));
            laptopNames.add(laptopElement.getText());
        }

        for (int i = 0; i < laptopLinks.size(); i++) {
            driver.get(laptopLinks.get(i));
            WebElement laptopDetail = driver.findElement(By.xpath(".//div[@class='pp-description']"));
            CsvReader.write(laptopNames.get(i) + "\t" + laptopDetail.getText() + "\n", LAPTOPS_CSV);
        }



        driver.close();
    }
}
