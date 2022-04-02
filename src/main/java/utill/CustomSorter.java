package utill;

import org.openqa.selenium.WebElement;

import java.util.Comparator;

import static utill.CustomDataFormatter.stringDateToData;
import static utill.CustomDataFormatter.stringToStringDate;

public class CustomSorter {

    public static final Comparator<WebElement> sortWebElementsByDate = Comparator
            .comparing(el -> stringDateToData(stringToStringDate(el.getText())));

    public static final Comparator<WebElement> sortWebElementsByAlphabetical = Comparator.comparing(WebElement::getText);
}
