package thiefs;

import com.google.common.collect.ImmutableList;
import common.CsvReader;
import common.Item;
import common.attributes.LadyNiceAtt;
import common.attributes.SlandoAtt;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageobjects.AbstractPageObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static common.attributes.LadyNiceAtt.*;
import static common.attributes.SlandoAtt.*;
import static common.attributes.SlandoAtt.PRICE_INPUT;

/**
 * Created with IntelliJ IDEA.
 * User: zapolsks
 * Date: 12/4/13
 * Time: 5:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class LadyNiceThief extends AbstractPageObject {

    public static final String HOME = "http://lady.webnice.ru/literature/";
    public static final String ABC_XPATH = "html/body/table/tbody/tr/td/a[string-length()=1]";
    public static final String AUTHORT_XPATH = "html/body/table[2]/tbody/tr/td/a";
    public static final String AUTHORS_CSV = "src/main/resources/authors.csv";
    public static final String ШАРМ = "http://lady.webnice.ru/literature/?act=pubseries&v=16&from=";
    public static final String ШАРМ_КОЛЛЕКЦИЯ = "http://lady.webnice.ru/literature/?act=pubseries&v=58&from=";
    public static final String ОЧАРОВАНИЕ = "http://lady.webnice.ru/literature/?act=pubseries&v=15&from=";


    private List<LadyNiceAtt> list = ImmutableList.of(BOOK, AUTHOR, ANNOTATION);

    public LadyNiceThief(WebDriver driver) {
        super(driver);
    }

    private void book(String link) {
        driver.get(link);

    }

    public void catchAuthors() {
        driver.get(HOME + "?genre=0");

        List<String> abcLinks = new ArrayList<String>();
        List<WebElement> abc = getElements(ABC_XPATH);
        for (WebElement webElement : abc) {
            abcLinks.add(webElement.getAttribute("href"));
        }

        for (String link : abcLinks) {
            driver.get(link);
            List<WebElement> elements = getElements(AUTHORT_XPATH);

            List<String> authorNames = new ArrayList<String>();
            List<String> authorId = new ArrayList<String>();

            for (WebElement author : elements) {
                String href = getHref(author);

                String name = author.getText();

                CsvReader.write(name + "\t" + href + "\n", AUTHORS_CSV);
            }
        }

    }

    private String getHref(WebElement author) {
        String href = author.getAttribute("href");
        href = href.substring(href.indexOf("&v=")+3);
        return href;
    }

    public void catchBooks() {

        driver.get("http://lady.webnice.ru/literature/allbooks.php?v=9176");
        getElement(".//*[@id='inp' and @name='username']").sendKeys("tpaxtapict");
        getElement(".//*[@id='inp' and @name='password']").sendKeys("zSi1989j");
        getElement(".//*[@id='fix_w2']").click();


        try {

            Scanner scanner = new Scanner(new File(AUTHORS_CSV));

            int count = 0;

            while (scanner.hasNextLine()) {
                String[] values = nextRow(scanner);
                String authorName = values[0];
                String authorId = values[1];
                driver.get("http://lady.webnice.ru/literature/allbooks.php?v="+ authorId);

                List<String> bookNames = new ArrayList<String>();
                List<String> bookId = new ArrayList<String>();

                List<WebElement> elements = getElements("html/body/table/tbody/tr/td[2]/a");

                for (int i = 1; i < elements.size(); i++) {
                    bookNames.add(elements.get(i).getText());
                    bookId.add(getHref(elements.get(i)));
                }

                StringBuffer buffer = new StringBuffer();
                for (int i = 0; i < bookId.size(); i++) {
                    buffer.append(authorName);
                    buffer.append("\t");
                    buffer.append(authorId);
                    buffer.append("\t");
                    buffer.append(bookNames.get(i));
                    buffer.append("\t");
                    buffer.append(bookId.get(i));
                    buffer.append("\t");
                    buffer.append("\t");
                    buffer.append("\n");
                }

                CsvReader.write(buffer.toString(), "src/main/resources/books_dic.csv");

                System.out.println("author "+ ++count);


            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void catchBooksBySeries() {


        for (int i = 0; i <= 1700; i+=20) {
            driver.get(ОЧАРОВАНИЕ +i);

            List<String> bookNames = new ArrayList<String>();
            List<String> bookId = new ArrayList<String>();

            for (WebElement webElement : getElements("html/body/table/tbody/tr/td/a[1]/text()[string-length() > 1]/..")) {
                bookId.add(getHref(webElement));
                bookNames.add(webElement.getText());
            }


            List<String> authorNames = new ArrayList<String>();
            List<String> bookDescription = new ArrayList<String>();

            for (WebElement webElement : getElements("html/body/table/tbody/tr/td/i[1]")) {
                authorNames.add(webElement.getText());
            }

            for (WebElement webElement : getElements("html/body/table/tbody/tr/td/i[2]")) {
                bookDescription.add(webElement.getText());
            }

            StringBuffer buffer = new StringBuffer();
            for (int j = 0; j < bookId.size(); j++) {
                buffer.append(bookId.get(j));
                buffer.append("\t");
                buffer.append(bookNames.get(j));
                buffer.append("\t");
                buffer.append(authorNames.get(j));
                buffer.append("\t");
                buffer.append(bookDescription.get(j));
                buffer.append("\t");
                buffer.append("\t");
                buffer.append("\n");
            }

            CsvReader.write(buffer.toString(), "src/main/resources/books_очарование.csv");

        }



    }

    private WebElement getElement(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    private static String[] nextRow(Scanner scanner) {
        return scanner.nextLine().split("\t");
    }

    private List<WebElement> getElements(String xpath) {
        return driver.findElements(By.xpath(xpath));
    }
}
