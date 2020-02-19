package me.twocat.shareinfo.controller.elasticresearch;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/elatc")
public class ElasticresearchController {

  @RequestMapping("/test")
  public ResponseEntity test(){
    return null ;
  }

}
