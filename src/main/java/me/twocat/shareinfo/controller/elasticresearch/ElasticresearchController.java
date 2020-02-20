package me.twocat.shareinfo.controller.elasticresearch;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/elatc")
public class ElasticresearchController {

  @RequestMapping("/test")
  public String test(){
    return "null" ;
  }

}
