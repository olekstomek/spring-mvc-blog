package blog.controllers;

import blog.services.interfaces.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {

    @Autowired
    ErrorService errorService;

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        return errorService.redirectToRightErrorPage(request);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}