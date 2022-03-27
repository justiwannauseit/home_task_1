package utill;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
@Slf4j
public class CustomDataFormatter {

    private static final Pattern PATTERN = Pattern.compile("((\\d+|В)\\s\\W+|О дате старта будет объявлено позже)");
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("d MMMM", new Locale("ru"));

    public static String stringToStringDate(String string) {
        Matcher matcher = PATTERN.matcher(string);
        if (matcher.find()) string = matcher.group();
        string = string.replaceAll("В ", "1 ")
                .replaceAll("ае", "ая")
                .replaceAll("О дате старта будет объявлено позже", "31 декабря");
        return string;
    }

    public static Date StringDateToData(String value) {
        try {
            return DATE_FORMAT.parse(value);
        } catch (ParseException ignore) {
            log.info("Value: " + value + " don't parsed");
            return null;
        }
    }

    public static String DateToString(Date date) {
        return DATE_FORMAT.format(date).replaceAll("31 декабря", "О дате старта будет объявлено позже");
    }

}
