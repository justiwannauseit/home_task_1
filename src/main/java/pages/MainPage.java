package pages;

import annotations.UrlPrefix;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@UrlPrefix("/")
public class MainPage extends BasePage<MainPage> {

  @FindBy(xpath = ".//a[contains(@class,'lessons')]")
  private List<WebElement> lessons;

  public MainPage(final WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }



}
