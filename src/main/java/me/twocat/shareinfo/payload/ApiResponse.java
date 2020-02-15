package me.twocat.shareinfo.payload;

import me.twocat.shareinfo.util.enumutil.MessageEnum;

import java.util.Map;

/***
 @author echo
 @create 2019年09月20日 10:57

 */
public  class ApiResponse {
    private String message;
    private Integer status;
    private Map<String , Object> data;


    public  ApiResponse()
    {

    }
    private static ApiResponse getInstance()
    {
      return new ApiResponse();
    }

  /**
   * response default success message
   * @return
   */
  public static ApiResponse responseSuccess()
    {
      return getInstance().setStatus(200).setMessage(MessageEnum.SUCCESS.val);
    }

  /***
   * return default failure message
   * @return
   */
  public static ApiResponse responseFailure()
    {
      return getInstance().setStatus(500).setMessage(MessageEnum.failure.val);
    }
  public ApiResponse(Boolean success, String message) {

    this.message = message;
  }

  public Integer getStatus() {
    return status;
  }

  public  ApiResponse setStatus(Integer status) {
    this.status = status;
    return this;
  }

  public Map<String, Object> getData() {
    return data;
  }

  public ApiResponse setData(Map<String, Object> data) {
    this.data = data;
    return this;
  }





    public String getMessage() {
        return message;
    }

    public ApiResponse setMessage(String message) {
        this.message = message;
        return this;
    }
}
