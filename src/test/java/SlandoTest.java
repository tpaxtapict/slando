import common.Item;
import dataproviders.ItemsDataProvider;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import pageobjects.PostNewAdPage;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zapolsks
 * Date: 12/3/13
 * Time: 2:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class SlandoTest {


    @Test(dataProviderClass = ItemsDataProvider.class, dataProvider = "postNewAdTest")
    public void testPostNewAd(List<Item> itemList) throws Exception {
        PostNewAdPage page = new PostNewAdPage(new FirefoxDriver());
        page.selectCategory();
        page.fillPage(itemList);
        String s;
    }

    @Test(dataProviderClass = ItemsDataProvider.class, dataProvider = "postNewAd")
    public void testTest(List<Item> itemList) throws Exception {
        System.out.println("---------------------");
        System.out.println("");
        for (Item item : itemList) {

            System.out.println(item.getSlandoAtt().getName() + " = " + item.getValue());
        }

    }


}
