package me.twocat.shareinfo.controller.sendcode;


import me.twocat.shareinfo.service.sendemailsms.SendEmailServerImpl;
import me.twocat.shareinfo.service.sendemailsms.SendSMSMsgServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/2catme/code")
public class SendCodeController {
  public static final Logger LOGGER = LoggerFactory.getLogger(SendCodeController.class);
  @Autowired
  SendEmailServerImpl sendEmailServer;
  @Autowired
  SendSMSMsgServiceImpl sendSMSMsgService;
  @RequestMapping("/emailcode")
  public void sendEmailCode()
  {
    try {
      sendEmailServer.sendEmail();
    }catch (Exception ex){
      LOGGER.info("sendcode controller ex error--->" , ex.getMessage());
    }
  }

  @RequestMapping("/smscode")
  public String sendSMSCode(String p){
    return  sendSMSMsgService.sendMsg("utt" ,p);
  }
}
