package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LanguageSettings {

    WebDriver webDriver;

    public LanguageSettings(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @FindBy(how = How.XPATH, using = "//span[@class='a-button-text a-declarative']")
    WebElement dropdownList;

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "EUR")
    WebElement currencyEUR;

    @FindBy(how = How.XPATH, using = "//input[@type='submit']")
    WebElement saveButton;


    public void clickOnDropdownList() {
        dropdownList.click();
    }

    public void chooseEUR() {
        currencyEUR.click();
    }

    public void saveSettings() {
        saveButton.click();
    }
}
