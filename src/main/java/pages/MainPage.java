package pages;

import annotations.UrlPrefix;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@UrlPrefix("/")
public class MainPage extends BasePage<MainPage> {

  public MainPage(final WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

}
