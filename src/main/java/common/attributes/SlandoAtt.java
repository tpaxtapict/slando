package common.attributes;

/**
 * Created with IntelliJ IDEA.
 * User: zapolsks
 * Date: 12/3/13
 * Time: 2:04 PM
 * To change this template use File | Settings | File Templates.
 */
public enum SlandoAtt {
    USER_EMAIL_INPUT(".//input[@id='userEmail']"),
    USER_PASS_INPUT(".//input[@id='userPass']"),
    REMEMBER_ME_CH_BOX(".//label[@class='icon f_checkbox inlblk vtop']"),
    USER_LOGINE_BUTTON(".//*[@id='se_userLogin']"),

    //PostNewAdPage
    ADD_TITLE_INPUT("title", ".//*[@id='add-title']"),
    ADD_PHONE_INPUT("phone", ".//*[@id='add-phone']"),
    ADD_DESCRIPTION_INPUT("description", ".//*[@id='add-description']"),
    MAP_ADDRESS_INPUT("address", ".//*[@id='mapAddress']"),
    SAVE_BUTTON(".//*[@id='save']"),

    POST_NEW_ADVERT_BUTTON(".//*[@href='http://slando.ua/post-new-ad/']"),

    CATEGORY_BREADCRUMB_COM_BOX(".//*[@id='category-breadcrumb-container']//dt[contains(./a/text(),'Выбрать')]"),
    HOBBY_BUTTON(".//*[@id='cat-903']//strong"),
    BOOKS_MAGAZINES_BUTTON(".//*[@id='category-903']//span[contains(text(),'Книги')]"),
    BOOKS_BUTTON(".//*[@id='category-49']//span[contains(text(),'Книги')]"),
    PRICE_R_BUTTON(".//label[contains(text(),'Цена') and not(*)]/preceding-sibling::label"),
    PRICE_INPUT(".//label[contains(text(),'Цена') and not(*)]/following-sibling::input");


    private String xpath;

    public String getName() {
        return name;
    }

    private String name;

    private SlandoAtt(String xpath) {
        this("", xpath);
    }

    private SlandoAtt(String name, String xpath) {
        this.name = name;
        this.xpath = xpath;
    }

    public String getXpath() {
        return xpath;
    }

    public static SlandoAtt getByName(String name) {
        for (SlandoAtt a: SlandoAtt.class.getEnumConstants()) {
            if (a.name.equals(name)) return a;
        }
        throw new Error("there is no enum " + name);
    }
}
