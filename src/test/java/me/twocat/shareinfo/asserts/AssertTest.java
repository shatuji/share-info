package me.twocat.shareinfo.asserts;

import me.twocat.shareinfo.util.pojotuils.DateUtils;
import sun.util.calendar.LocalGregorianCalendar;

import java.util.Date;

public class AssertTest {
  public static void main(String[] args)
  {

    System.out.println("print test class");
    String strs = "nssull";
    String nus = null;
    String dates = "2020-02-25 12:25:10";
    Date date = DateUtils.string2Date(dates);
    String sd = DateUtils.date2String(date);
    System.out.println("test end about this method");
  }
}
