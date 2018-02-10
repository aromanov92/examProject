package loginTest;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import parentTest.ParentTest;


public class LoginTest extends ParentTest {

    @Test
    public void validLogin(){
        loginPage.loginUser("uatest", "111");
        checkAC("Search field is not present", mainPage.isSearchFieldPresent(), true);

    }

    @Test

    public void unValidLogin(){
        loginPage.loginUser("uatest","222");
        Assert.assertTrue("Login Input does not dispalay", loginPage.isLoginInputDisplay());
    }
}
