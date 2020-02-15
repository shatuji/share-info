package me.twocat.shareinfo.security;

import me.twocat.shareinfo.payload.ApiResponse;
import me.twocat.shareinfo.util.pojotuils.JsonUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 @author echo
 @create 2019年09月20日 9:43

 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /**
     * this interface just only one method to implements
     * The first spring security related class that we’ll define is JwtAuthenticationEntryPoint.
     * It implements AuthenticationEntryPoint interface and provides the implementation for its commence() method.
     * This method is called whenever an exception is thrown due to an unauthenticated user trying to access a resource that requires authentication.
     * In this case, we’ll simply respond with a 401 error containing the exception message
     * @param httpServletRequest
     * @param httpServletResponse
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("utf-8");//set character encoding and supporting chinese
        httpServletResponse.getWriter().write(JsonUtils.object2Json(ApiResponse.responseFailure().setMessage("你还没有登录")));
    }
}
