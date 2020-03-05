package blog.controllers;

import blog.services.interfaces.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private NotificationService notificationService;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws IOException {
        displayAppropriateLoginErrorMessage(httpServletRequest, httpServletResponse);

        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/users/login");
    }

    private void displayAppropriateLoginErrorMessage(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        if (httpServletRequest.getParameter("username").isEmpty() ||
                httpServletRequest.getParameter("password").isEmpty()) {
            notificationService.addErrorMessage("Please fill the form correctly!");
        } else {
            notificationService.addErrorMessage("Login not successful");
        }
    }
}
