package popups;

import actions.CommonActions;
import annotations.Popup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class AnyPopupAbs<T> extends CommonActions<T> {

  {
    this.standartWaiter.waitForCondition(ExpectedConditions.visibilityOfElementLocated(getPopupLocator()));
  }

  protected String baseLocator;

  public AnyPopupAbs(final WebDriver driver) {
    super(driver);
  }

  private By getPopupLocator() {
    Popup component = getClass().getAnnotation(Popup.class);

    if (component != null) {
      String value = component.value();

      baseLocator = value;

      if (value.startsWith("/")) {
        return By.xpath(value);
      }
      return By.cssSelector(value);
    }

    return null;
  }

}
