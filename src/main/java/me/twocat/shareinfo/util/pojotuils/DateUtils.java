package me.twocat.shareinfo.util.pojotuils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
  /**
   * date convert to string
   * @param date
   * @return
   */
  public static String date2String(@NotNull Date date)
  {
    try {
      return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }catch ( Exception ex)
    {
      return null;
    }
  }

  /***
   * string convert to date
   * and not null
   * @param date
   * @return
   */
  public static Date string2Date(@NotEmpty String date)
  {
    try {
      // date convert to  string java 8
      DateTimeFormatter pattern = DateTimeFormatter
        .ofPattern("yyyy-MM-dd HH:mm:ss");
      return asDate(LocalDateTime.parse(date, pattern));
    }catch (Exception ex){
      return null;
    }
  }

  private static Date asDate(@NotNull  LocalDate localDate) {
    return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
  }

  private static Date asDate(@NotNull  LocalDateTime localDateTime) {
    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
  }

  private static LocalDate asLocalDate(@NotNull  Date date) {
    return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
  }

  private static LocalDateTime asLocalDateTime(@NotNull Date date) {
    return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
  }

}
