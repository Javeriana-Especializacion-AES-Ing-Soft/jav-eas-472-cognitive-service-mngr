package co.edu.javeriana2.cognitive.utilities;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateUtility {

    private static final String ZONE_ID_AMERICA_BOGOTA = "America/Bogota";

    private DateUtility() {
    }

    public static LocalDateTime getNowInLocalDateTime() {
        return LocalDateTime.now(ZoneId.of(ZONE_ID_AMERICA_BOGOTA));
    }

    public static String getCurrentDateInFormat(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return getNowInLocalDateTime().format(formatter);
    }

}
