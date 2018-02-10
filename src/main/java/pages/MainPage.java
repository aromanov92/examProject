package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static libs.ActionsWithOurElements.*;

public class MainPage extends ParentPage{
    @FindBy(id = "search")
    private WebElement searchField;

    @FindBy(id = "dictionary")
    private WebElement menuDictionary;

    @FindBy(id = "spares")
    private WebElement subMenuSpares;

    public MainPage(WebDriver webDriver) {
        super(webDriver,"/#/inbox");
    }

    public boolean isSearchFieldPresent(){
        return isElementPresent(searchField);
    }

    public void clickOnMenuDictionary(){
        clickOnElement(menuDictionary);
    }

    public void clickOnSubMenuSpare(){
        clickOnElement(subMenuSpares);
    }
}
