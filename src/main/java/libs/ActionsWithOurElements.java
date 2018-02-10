package libs;

import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionsWithOurElements {
    WebDriver webDriver;
    static Logger logger;
    private static WebDriverWait webDriverWait15;
    static private WebDriverWait webDriverWait20;
    ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);

    public ActionsWithOurElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        logger = Logger.getLogger("ActionsWithOurElements");
        webDriverWait15 = new WebDriverWait(webDriver, configProperties.TIME_FOR_EXPLICIT_WAIT_LOW());
        webDriverWait20 = new WebDriverWait(webDriver, configProperties.TIME_FOR_EXPLICIT_WAIT_HIGHT());
    }

    public static void enterTextInToInput(WebElement input, String text){
        try{
            input.clear();
            input.sendKeys(text);
            logger.info(text + " was inputed in to input " + input);
        }catch (Exception e){
            logErrorAndStopTest();
        }
    }

    public static void clickOnElement(WebElement element){
        try{
            webDriverWait20.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            logger.info("Element was clicked " + element);
        }catch (Exception e){
            logErrorAndStopTest();
        }
    }

    public static boolean isElementPresent(WebElement element){
        try {
            boolean tempState
                    = element.isDisplayed()&&element.isEnabled();
            logger.info("Is Element  Present ? - " + tempState);
            return tempState;
        }catch (Exception e){
            logger.info("Is Element  Present ? - false");
            return false;
        }
    }

    /**
     * Method select needed line in DropDown by WebElement
     * @param select
     * @param option
     */
    public void selectOptionsInDropDown(WebElement select, WebElement option){
        clickOnElement(select);
        clickOnElement(option);
    }

    /**
     * Method select line in DropDown by xPathLocator
     * @param select
     * @param xPathLocator
     */
    public void selectOptionsInDropDown(WebElement select, String xPathLocator){
        try {
            selectOptionsInDropDown(select, webDriver.findElement(By.xpath(xPathLocator)));
        }catch (Exception e){
            logErrorAndStopTest();
        }
    }

    /**
     * Method set needed state in CheckBox
     * @param element
     * @param neededState (Can be only 'Checked' or 'Unchecked')
     */
    public void setStateToCheckBox(WebElement element, String neededState){
        final String CHECK_STATUS = "Checked";
        final String UNCHECK_STATUS = "Unchecked";
        if (!neededState.equals(CHECK_STATUS) && !neededState.equals(UNCHECK_STATUS)){
            logger.error(neededState + " - Value of neededState is not expected ");
            Assert.fail(neededState + " - Value of neededState is not expected ");
        }else {
            try {
                if (neededState.equals(CHECK_STATUS) && !element.isSelected() ||
                        neededState.equals(UNCHECK_STATUS) && element.isSelected()){
                    clickOnElement(element);
                } else {
                    logger.info("CheckBox has " + neededState + " state already ");
                }
            }catch (Exception e){
                logErrorAndStopTest();
            }
        }
    }

    /**
     * Method selected TEXT in dropDown
     * @param dropDownElement
     * @param textForSelection
     */
    public void selectTextInDropDown(WebElement dropDownElement, String textForSelection) {
        try {
            Select optionsFromDD = new Select(dropDownElement);
            optionsFromDD.selectByVisibleText(textForSelection);
            logger.info(textForSelection + " text was selected in DropDown");
        } catch (Exception e) {
            logErrorAndStopTest();
        }
    }

    /**
     * Method checked is element present on page
     *
     * @param xpathLocator
     * @return
     */
    public boolean isElementPresent(String xpathLocator) {
        try {
            WebElement webElement = webDriver.findElement(By.xpath(xpathLocator));
            return webElement.isDisplayed() && webElement.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickOnElement(String xpathLocator) {
        try {
            clickOnElement(webDriver.findElement(By.xpath(xpathLocator)));
        } catch (Exception e) {
            logErrorAndStopTest();
        }
    }

    private static void logErrorAndStopTest(){
        logger.error("Can not work with element " );
        Assert.fail("Can not work with element " );
    }
}
