package me.twocat.shareinfo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("me.twocat.shareinfo.dao")
public class ShareInfoApplication {
    public static void main(String[] args) {
      SpringApplication.run(ShareInfoApplication.class, args);
    }

}
