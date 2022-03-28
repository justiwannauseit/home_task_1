package ui.courses;

import annotations.Driver;
import components.Courses;
import extensions.UIExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

@ExtendWith(UIExtension.class)
public class FavouriteCourseTile_Test {

  @Driver
  public WebDriver driver;

  @Test
  public void click_first_course_by_date() {
    new MainPage(driver).open();

    String actualTitle = new Courses(driver)
            .clickToFirstCourseByDate()
            .getPageTitle();

    Assertions.assertEquals("Нагрузочное тестирование. Инструменты и особенности процесса проведения нагрузочного тестирования", actualTitle);
  }

  @Test
  public void click_last_course_by_date() {
    new MainPage(driver).open();

    String actualTitle = new Courses(driver)
        .clickToLastCourseByDate()
        .getPageTitle();

    Assertions.assertEquals("Специализация Data Engineer | OTUS", actualTitle);
  }

  @Test
  public void click_to_first_alphabetical_course() {
    new MainPage(driver).open();

    String actualTitle = new Courses(driver)
        .clickToFirstAlphabeticalElement()
        .getPageTitle();

    Assertions.assertEquals("Архитектор высоких нагрузок. Лучшие практики по HighLoad и отказоустойчивости веб-приложений | OTUS", actualTitle);
  }

  @Test
  public void click_to_last_alphabetical_course() {
    new MainPage(driver).open();

    String actualTitle = new Courses(driver)
        .clickToLastAlphabeticalElement()
        .getPageTitle();

    Assertions.assertEquals("Специализация Network Engineer", actualTitle);
  }

  @Test
  public void click_to_course_by_name() {
    new MainPage(driver).open();

    String actualTitle = new Courses(driver)
            .clickIfContains("Linux")
            .getPageTitle();

    Assertions.assertTrue(actualTitle.equals("Специализация Administrator Linux") || actualTitle.equals("Специализация Network Engineer"));
  }

  @Test
  public void click_to_course_by_date() {
    new MainPage(driver).open();

    String actualTitle = new Courses(driver)
            .clickIfContains("29 марта")
            .getPageTitle();

    Assertions.assertTrue(actualTitle.equals("Специализация С++") || actualTitle.equals("Специализация Android Developer"));
  }

}
