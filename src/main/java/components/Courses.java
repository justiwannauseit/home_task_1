package components;

import annotations.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.Lessons;
import utill.CustomDataFormatter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component("//*[contains(@class, 'container-lessons')]/div[text()='Популярные курсы']//following-sibling::div")
public class Courses extends AnyComponentAbs<Courses> {

    @FindBy(xpath = ".//div[contains(@class, 'lessons__new-item-start')]")
    private List<WebElement> popularLessons;

    @FindBy(xpath = ".//div[contains(@class, 'lessons__new-item-time')]")
    private List<WebElement> specLessons;

    @FindBy(xpath = ".//div[contains(@class, 'lessons__new-item-title')]")
    private List<WebElement> lessonNames;

    @FindBy(xpath = ".//button[contains(@class, 'cookie')]")
    private WebElement cookie;

    public Courses(WebDriver driver) {
        super(driver);
    }

    public Lessons clickToFirstCourseByDate() {
        List<WebElement> courses = new ArrayList<>(popularLessons);
        courses.addAll(specLessons);
        List<String> stringDates = getSortedDatesByWebElements(courses);
        return clickIfContains(stringDates.get(0));
    }

    public Lessons clickToLastCourseByDate() {
        List<WebElement> courses = new ArrayList<>(popularLessons);
        courses.addAll(specLessons);
        List<String> stringDates = getSortedDatesByWebElements(courses);
        return clickIfContains(stringDates.get(stringDates.size() - 1));
    }

    public Lessons clickToFirstAlphabeticalElement() {
        String first = lessonNames.stream().map(WebElement::getText).sorted().findFirst().orElseThrow(NullPointerException::new);
        clickIfContains(first);
        return new Lessons(driver);
    }

    public Lessons clickToLastAlphabeticalElement() {
        String last = lessonNames.stream().map(WebElement::getText).max(Comparator.naturalOrder()).orElseThrow(NullPointerException::new);
        clickIfContains(last);
        return new Lessons(driver);
    }

    public Lessons clickIfContains(String value) {
        WebElement element = driver.findElement(By.xpath(String.format(".//div[text()[contains(.,'%s')]]/ancestor::div[contains(@class, 'lessons__new-item-container')]", value)));
        clickToCourse(element);
        return new Lessons(driver);
    }

    private Lessons clickToCourse(WebElement element) {
        if (cookie.isEnabled()) cookie.click();
        actions.moveToElement(element).sendKeys(Keys.DOWN).pause(800).build().perform();
        element.click();
        return new Lessons(driver);
    }

    private List<String> getSortedDatesByWebElements(List<WebElement> courses) {
        return courses.stream().map(WebElement::getText)
                .map(CustomDataFormatter::stringToStringDate)
                .map(CustomDataFormatter::StringDateToData)
                .distinct()
                .filter(Objects::nonNull)
                .sorted()
                .map(CustomDataFormatter::DateToString)
                .collect(Collectors.toList());
    }

}
