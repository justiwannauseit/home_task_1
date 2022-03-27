package pages;

import actions.CommonActions;
import annotations.UrlPrefix;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class BasePage<T> extends CommonActions<T> {

    public BasePage(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private String getBaseUrl() {
        return StringUtils.stripEnd(System.getProperty("webdriver.base.url"), "/");
    }

    private String getUrlPrefix() {
        UrlPrefix urlAnnotation = getClass().getAnnotation(UrlPrefix.class);
        if (urlAnnotation != null) {
            return urlAnnotation.value();
        }

        return "";
    }

    public T open() {
        driver.get(getBaseUrl() + getUrlPrefix());
        return (T) this;
    }

    public <Page> Page page(final Class<Page> clazz) {
        try {
            Constructor<Page> constructor = clazz.getConstructor(WebDriver.class);

            return convertInstanceOfObject(constructor.newInstance(driver), clazz);

        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static <T> T convertInstanceOfObject(final Object o, final Class<T> clazz) {
        try {
            return clazz.cast(o);
        } catch (ClassCastException e) {
            return null;
        }
    }

}
