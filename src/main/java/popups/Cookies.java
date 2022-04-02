package popups;

import annotations.Popup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Popup(".//div[@class='cookie']")
public class Cookies extends AnyPopupAbs<Cookies> {

    @FindBy(xpath = ".//button[contains(@class, 'cookie')]")
    private WebElement cookie;

    public Cookies(WebDriver driver) {
        super(driver);
    }

    public void clickToOkButton() {
        if (cookie.isEnabled()) {
            actions.moveToElement(cookie).pause(400).build().perform();
            cookie.click();
        }
    }

}
