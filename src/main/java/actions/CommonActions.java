package actions;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import waiters.StandartWaiter;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class CommonActions<T> {

    protected WebDriver driver;
    protected StandartWaiter standartWaiter;
    protected Actions actions;

    public CommonActions(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        standartWaiter = new StandartWaiter(driver);
        actions = new Actions(driver);
    }

    protected BiConsumer<By, Predicate<? super WebElement>> clickElementByPredicate = (By locator, Predicate<? super WebElement> predicate) -> {
        List<WebElement> elements = driver.findElements(locator).stream().filter(predicate).collect(Collectors.toList());
        if (!elements.isEmpty()) {
            WebElement element;
            element = elements.stream().reduce((first, second) -> Faker.instance().random().nextInt(0, 1) == 0 ? first : second).get();
            actions.moveToElement(element).sendKeys(Keys.DOWN).pause(800).build().perform();
            element.click();
        }
    };

    protected List<WebElement> sortElementsViaComparator(List<WebElement> elements, Comparator<WebElement> comparator) {
        return elements.stream().sorted(comparator).collect(Collectors.toList());
    }

}
