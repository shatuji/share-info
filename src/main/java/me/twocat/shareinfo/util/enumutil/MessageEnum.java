package me.twocat.shareinfo.util.enumutil;

public enum MessageEnum {
      SUCCESS("this request success"),
      failure("this request failure");
      public final String val;
      private MessageEnum(String val){
        this.val = val;
      }
}
