package me.twocat.shareinfo.controller;

import me.twocat.shareinfo.entity.User;
import me.twocat.shareinfo.payload.ApiResponse;
import me.twocat.shareinfo.security.JwtAuthenticationFilter;
import me.twocat.shareinfo.util.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class SuperController {
  private static final Logger LOGGER = LoggerFactory.getLogger(SuperController.class);
  @Autowired
  private JwtTokenProvider jwtTokenProvider;
  @Autowired
  private HttpServletRequest request;


  /***
   * return use information
   * @return
   */
   public Optional<User> getUserInfo()
   {
     try {
       return Optional.ofNullable(jwtTokenProvider.getUserFromToken(JwtAuthenticationFilter.getJwtFromRequest(request)));
     }catch (Exception ex)
     {
       LOGGER.info("console data from supercontroller --->{}" , ex.getMessage());
       return null;
     }
   }
}
