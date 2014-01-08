package pageobjects;

import com.google.common.collect.ImmutableList;
import common.attributes.SlandoAtt;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static common.attributes.SlandoAtt.*;

/**
 * Created with IntelliJ IDEA.
 * User: zapolsks
 * Date: 12/3/13
 * Time: 2:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class PostNewAdPage extends AbstractPageObject {

    private final List<SlandoAtt> slandoAttList = ImmutableList.of(ADD_TITLE_INPUT, ADD_PHONE_INPUT, ADD_DESCRIPTION_INPUT, MAP_ADDRESS_INPUT, PRICE_INPUT);



    public PostNewAdPage(WebDriver driver) {
        super(driver);

        driver.get("http://slando.ua/myaccount/");

        getElement(USER_EMAIL_INPUT).sendKeys("");
        getElement(USER_PASS_INPUT).sendKeys("");
        getElement(USER_LOGINE_BUTTON).click();

        getElement(POST_NEW_ADVERT_BUTTON).click();

    }



    public void selectCategory() {
        getElement(CATEGORY_BREADCRUMB_COM_BOX).click();
        getElement(HOBBY_BUTTON).click();
        getElement(BOOKS_MAGAZINES_BUTTON).click();
        getElement(BOOKS_BUTTON).click();
    }




}
