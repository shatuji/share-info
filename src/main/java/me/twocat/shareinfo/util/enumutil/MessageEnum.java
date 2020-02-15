package me.twocat.shareinfo.util.enumutil;

public enum MessageEnum {
      SUCCESS("this request success"),
      NULLS("request null"),
      FAILURE("this request failure");
      public final String val;
      MessageEnum(String val){
        this.val = val;
      }
}
