package me.twocat.shareinfo.util;


import io.jsonwebtoken.*;
import me.twocat.shareinfo.entity.userprofile.User;
import me.twocat.shareinfo.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;


import java.util.*;
import java.util.stream.Collectors;

/***
 @author echo
 @create 2019年09月20日 10:28

 */
@Component
public class JwtTokenProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Map<String , Object> claims = new HashMap<>();
        claims.put("userId" , userPrincipal.getId());
        claims.put("username" , userPrincipal.getUsername());
        claims.put("phoneOrEmail" , userPrincipal.getPhoneOrEmail());

        return Jwts.builder()
                .setSubject("login info")
                .setIssuedAt(new Date())
                .setClaims(claims)
                .setExpiration(new Date(new Date().getTime() + jwtExpirationInMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

  /**
   * extract user from jwt
   * @param token
   * @return
   */
    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong( claims.get("userId") + "");
    }

    public User getUserFromToken(String token)
    {
      Claims claims = Jwts.parser()
                        .setSigningKey(jwtSecret)
                        .parseClaimsJws(token)
                        .getBody();
      User user = new User();
      Long ids = Long.parseLong(claims.get("userId") + "");
      user.setId(ids);
      user.setUserName((String)claims.get("username"));
      user.setAccount((String)claims.get("phoneOrEmail"));
      return user;
    }

    public UserPrincipal getUserPrincipalFromToken()
    {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new UserPrincipal(12l , "ROLE" ,"DD" , null, authorities);
    }


    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }catch (Exception ex){
            logger.error("JWT has a error");
        }
        return false;
    }
}
