package me.twocat.shareinfo.util.pojotuils;

import com.google.gson.Gson;

public class JsonUtils {
  /***
   * object convert to json
   * and allow null value
   * @param o
   * @return
   */
  public static String object2Json(Object o)
  {
    return  new Gson().newBuilder().serializeNulls().create().toJson(o);
  }

  /**
   * json convert to T class
   * and allow null value
   * @param json
   * @param t
   * @param <T>
   * @return
   */
  public  static <T> T json2Object(String json , Class<T> t)
  {
    return  new Gson().newBuilder().serializeNulls().create().fromJson(json , t);
  }
}
