package blog.services.interfaces;

import javax.servlet.http.HttpServletRequest;

public interface ErrorService {
    String redirectToRightErrorPage(HttpServletRequest request);
}
