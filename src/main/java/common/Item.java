package common;

import common.attributes.SlandoAtt;

/**
 * Created with IntelliJ IDEA.
 * User: zapolsks
 * Date: 12/3/13
 * Time: 2:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class Item {
    private SlandoAtt slandoAtt;
    private String value;

    public Item(SlandoAtt slandoAtt, String value) {
        this.slandoAtt = slandoAtt;
        this.value = value;
    }

    public SlandoAtt getSlandoAtt() {
        return slandoAtt;
    }

    public void setSlandoAtt(SlandoAtt slandoAtt) {
        this.slandoAtt = slandoAtt;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
