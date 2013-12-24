package dataproviders;

import com.google.common.collect.ImmutableList;
import common.CsvReader;
import common.Item;
import org.testng.annotations.DataProvider;

import static common.attributes.SlandoAtt.*;

/**
 * Created with IntelliJ IDEA.
 * User: zapolsks
 * Date: 12/3/13
 * Time: 2:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class ItemsDataProvider {

    public static final String PATH = "src/main/resources/books.csv";

    @DataProvider
    public static Object[][] postNewAdTest() {
        return new Object[][] {
            {ImmutableList.of(
                    new Item(ADD_TITLE_INPUT, "test title"),
                    new Item(ADD_DESCRIPTION_INPUT, "test desc krfjgkfjgkj a lj laj lja g"),
                    new Item(ADD_PHONE_INPUT, "0111111111"),
                    new Item(PRICE_INPUT, "25"))}
        };
    }

    @DataProvider
    public static Object[][] postNewAd() {
        return CsvReader.read(PATH);
    }



}
