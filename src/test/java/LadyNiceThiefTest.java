import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import thiefs.LadyNiceThief;

/**
 * Created with IntelliJ IDEA.
 * User: szapolskyi
 * Date: 04.12.13
 * Time: 22:29
 * To change this template use File | Settings | File Templates.
 */
public class LadyNiceThiefTest {
    @Test
    public void testCatchAuthors() throws Exception {
        new LadyNiceThief(new FirefoxDriver()).catchBooksBySeries();
    }
}
