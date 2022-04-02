package components;

import annotations.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.Lessons;
import utill.CustomSorter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component("//*[contains(@class, 'container-lessons')]/div[text()='Популярные курсы']//following-sibling::div")
public class Courses extends AnyComponentAbs<Courses> {

    @FindBy(xpath = ".//div[contains(@class, 'lessons__new-item-start')]")
    private List<WebElement> popularLessons;

    @FindBy(xpath = ".//div[contains(@class, 'lessons')]/div[contains(@class, 'lessons__new-item-time')]")
    private List<WebElement> specLessons;

    @FindBy(xpath = ".//div[contains(@class, 'lessons__new-item-title')]")
    private List<WebElement> lessonNames;

    private final static String LESSON_CONTAINER = ".//div[text()[contains(.,'%s')]]/ancestor::div[contains(@class, 'lessons__new-item-container')]";

    public Courses(WebDriver driver) {
        super(driver);
    }

    public Lessons clickToFirstCourseByDate() {
        clickIfContains(sortElementsViaComparator(getAllCourses(), CustomSorter.sortWebElementsByDate).get(0).getText());
        return new Lessons(driver);
    }

    public Lessons clickToLastCourseByDate() {
        List<WebElement> elements = getAllCourses();
        clickIfContains(sortElementsViaComparator(elements, CustomSorter.sortWebElementsByDate).get(elements.size() - 1).getText());
        return new Lessons(driver);
    }

    public Lessons clickToFirstAlphabeticalElement() {
        clickIfContains(sortElementsViaComparator(lessonNames, CustomSorter.sortWebElementsByAlphabetical).get(0).getText());
        return new Lessons(driver);
    }

    public Lessons clickToLastAlphabeticalElement() {
        clickIfContains(sortElementsViaComparator(lessonNames, CustomSorter.sortWebElementsByAlphabetical).get(lessonNames.size() - 1).getText());
        return new Lessons(driver);
    }

    public Lessons clickIfContains(String value) {
        clickElementByPredicate.accept(By.xpath(String.format(LESSON_CONTAINER, value)), Objects::nonNull);
        return new Lessons(driver);
    }

    private List<WebElement> getAllCourses() {
        List<WebElement> courses = new ArrayList<>(popularLessons);
        courses.addAll(specLessons);
        return courses;
    }

}
