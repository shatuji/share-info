package me.twocat.shareinfo.controller.elasticresearch;



import lombok.extern.slf4j.Slf4j;
import me.twocat.shareinfo.service.elasticsearch.ProfileService;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/elatc")
public class ElasticresearchController {

  @Autowired
  ProfileService profileService;
  @RequestMapping("/test")
  public String test(){
    try {
      log.info("print this all info");
      return "index value is -->" + profileService.createProfileDocument(null);
    }catch (Exception ex)
    {
      log.info("test value is error --->{}" , ex);
    }
    return "null" ;
  }

  @RequestMapping("/findall")
  public SearchResponse findAll(String field)
  {
    try {
      System.out.println("print this value field--->" + field);
      return profileService.findAll(field);
    }catch (Exception ex){
      log.info("print find all error--->{}" , ex);
    }
    return null;
  }

  @RequestMapping("/insertdata")
  public String insertDataByIndex(){
    try {
      return profileService.insertIndexDocument(null);
    }catch (Exception ex){
      log.info("pritn ex value--->{}" , ex);
    }
    return "";
  }
}
