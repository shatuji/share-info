package me.twocat.shareinfo.service.sendemailsms;

import com.aliyuncs.exceptions.ServerException;
import me.twocat.shareinfo.service.redis.RedisServiceApiImpl;
import me.twocat.shareinfo.util.pojotuils.GeneratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;

import com.aliyuncs.http.MethodType;



/***
 * send message to phone
 */
@Service
public class SendSMSMsgServiceImpl {
  public static final Logger LOGGER = LoggerFactory.getLogger(SendEmailServerImpl.class);
  private static  final String accessKeyId = "LTAI4Ffr4rtpscv9jG8NdBmP";
  private static final String accessSecrectVal = "V40LdbOD2zX37UXSCLgPVvjKqCimlc";
  private static final String SignName = "两只猫咪";
  private static final String TemplateCode = "SMS_183262730";
  @Autowired
  RedisServiceApiImpl redisServiceApi;

  public String sendMsg(String account , String phoneNubmer)
  {
    DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessSecrectVal);
    IAcsClient client = new DefaultAcsClient(profile);

    CommonRequest request = new CommonRequest();

    request.setMethod(MethodType.POST);
    request.setDomain("dysmsapi.aliyuncs.com");
    request.setVersion("2017-05-25");
    request.setAction("SendSms");
    request.putQueryParameter("RegionId", "cn-hangzhou");
    request.putQueryParameter("PhoneNumbers", phoneNubmer);
    request.putQueryParameter("SignName", SignName);
    request.putQueryParameter("TemplateCode", TemplateCode);
    String generatorVal = GeneratorUtils.generatorRandom6Digit(6);
    //save redis
    redisServiceApi.redisSaveObjectByKey(account , generatorVal,60 * 5);
    request.putQueryParameter("TemplateParam", "{\"code\":"+ generatorVal +"}");
    try {
      CommonResponse response = client.getCommonResponse(request);
      return response.getData();
    } catch (ServerException e) {
      LOGGER.info("ServerException message ===>{}" , e.getMessage());
      return  null;
    } catch (ClientException e) {
      LOGGER.info("clientException ===>getErrorMessage->{} , getMessage-->"
        , e.getErrMsg() , e.getMessage());
      return null;
    }
  }

}
