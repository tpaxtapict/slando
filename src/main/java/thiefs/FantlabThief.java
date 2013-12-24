package thiefs;

import common.CsvReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: szapolskyi
 * Date: 10.12.13
 * Time: 21:28
 * To change this template use File | Settings | File Templates.
 */
public class FantlabThief {

    public static final String XPATH = ".//*[@id='maindiv']/div/table/tbody/tr/td[3]/table[2]/tbody/tr/td[1]/table[2]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[1]/td[2]";

    public void catchBooks() {

        int j = 0;
        ArrayList<String> authors = new ArrayList<>();
        ArrayList<String> bookNames = new ArrayList<>();
        ArrayList<String> years = new ArrayList<>();

        FirefoxDriver driver = new FirefoxDriver();

        driver.get("http://fantlab.ru/bookcase23611");
        run(driver);

        driver.get("http://fantlab.ru/bookcase23611page2");
        run(driver);



    }

    private void run(FirefoxDriver driver) {
        StringBuffer buffer = new StringBuffer();
        for (WebElement element : driver.findElements(By.xpath(XPATH))) {

            String author;
            try {
                 author = element.findElement(By.xpath("./span[1]/a")).getText();
            } catch (Throwable t) {
                author = element.findElement(By.xpath("./span[1]")).getText();
            }

            String bookName = element.findElement(By.xpath("./p/a/b")).getText();
            String year = element.findElement(By.xpath("./p[2]")).getText();

            buffer.append(author);
            buffer.append("\t");
            buffer.append(bookName);
            buffer.append("\t");
            buffer.append(year);
            buffer.append("\n");

            CsvReader.write(buffer.toString(), "src/main/resources/fantlab.csv");

            buffer = new StringBuffer();

        }
    }

    public static void main(String[] args) {
        new FantlabThief().catchBooks();
    }
}
