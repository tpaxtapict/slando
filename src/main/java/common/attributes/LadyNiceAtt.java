package common.attributes;

/**
 * Created with IntelliJ IDEA.
 * User: zapolsks
 * Date: 12/4/13
 * Time: 5:07 PM
 * To change this template use File | Settings | File Templates.
 */
public enum LadyNiceAtt {

    BOOK("book", ""),
    AUTHOR("author", ""),
    SERIES_OF_BOOKS("series_of_books", ""),
    ANNOTATION("annotation", "")

    ;
    private String xpath;

    public String getName() {
        return name;
    }

    private String name;

    private LadyNiceAtt(String xpath) {
        this("", xpath);
    }

    private LadyNiceAtt(String name, String xpath) {
        this.name = name;
        this.xpath = xpath;
    }

    public String getXpath() {
        return xpath;
    }

    public static LadyNiceAtt getByName(String name) {
        for (LadyNiceAtt a: LadyNiceAtt.class.getEnumConstants()) {
            if (a.name.equals(name)) return a;
        }
        throw new Error("there is no enum " + name);
    }
}
