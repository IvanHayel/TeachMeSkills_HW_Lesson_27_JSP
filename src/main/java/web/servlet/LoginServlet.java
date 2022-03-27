package web.servlet;

import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import service.UserService;

import java.io.IOException;

@Log
@WebServlet(name = "LoginServlet", value = "/sign-in")
public class LoginServlet extends HttpServlet {
    private static final String LOGIN_PAGE_PATH = "/pages/login.jsp";
    private static final String HOME_PATH = "/";
    private static final String LOGIN_PARAMETER = "login";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String USER_ATTRIBUTE = "user";
    private static final UserService userService = UserService.getInstance();

    private transient HttpSession session;

    @Override
    @SneakyThrows({IOException.class, ServletException.class})
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        session = req.getSession();
        User currentUser = (User) session.getAttribute(USER_ATTRIBUTE);
        if (currentUser != null) resp.sendRedirect(HOME_PATH);
        else req.getRequestDispatcher(LOGIN_PAGE_PATH).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        session = req.getSession();
        @NonNull String login = req.getParameter(LOGIN_PARAMETER);
        @NonNull String password = req.getParameter(PASSWORD_PARAMETER);
        if (userService.isLoginExist(login)) {
            User user = userService.getByLogin(login);
            if (user.getPassword().equals(password)) {
                session.setAttribute(USER_ATTRIBUTE, user);
                log.info(String.format("%s logged in.", user));
            }
        }
        doGet(req, resp);
    }
}