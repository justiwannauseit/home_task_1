package driver;

import driver.custom.ChromeCustomDriver;
import driver.custom.FirefoxCustomDriver;
import driver.custom.OperaCustomDriver;
import exceptions.DriverTypeNotSupported;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Locale;

@Slf4j
public class DriverFactory implements IDriverFactory {

  private String browserType = System.getProperty("browser").toLowerCase(Locale.ROOT);

  @Override
  public EventFiringWebDriver getDriver() {
    switch (this.browserType) {
      case "chrome": {
        return new EventFiringWebDriver(new ChromeCustomDriver().newDriver());
      }
      case "opera": {
        return new EventFiringWebDriver(new OperaCustomDriver().newDriver());
      }
      case "firefox": {
        return new EventFiringWebDriver(new FirefoxCustomDriver().newDriver());
      }
      default:
        try {
          throw new DriverTypeNotSupported(this.browserType);
        } catch (DriverTypeNotSupported ex) {
          ex.printStackTrace();
          return null;
        }
    }
  }
}
