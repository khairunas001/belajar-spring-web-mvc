package bang_anas.belajar_spring_web_mvc.interceptor;

import bang_anas.belajar_spring_web_mvc.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
