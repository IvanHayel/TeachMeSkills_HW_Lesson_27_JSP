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
@WebServlet(name = "RegistrationServlet", value = "/sign-up")
public class RegistrationServlet extends HttpServlet {
    private static final String REGISTRATION_PAGE_PATH = "/pages/registration.jsp";
    private static final String LOGIN_PATH = "/sign-in";
    private static final String HOME_PATH = "/";
    private static final String LOGIN_PARAMETER = "login";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String NAME_PARAMETER = "name";
    private static final String SURNAME_PARAMETER = "surname";
    private static final String USER_ATTRIBUTE = "user";
    private static final UserService userService = UserService.getInstance();

    private transient HttpSession session;

    @Override
    @SneakyThrows({IOException.class, ServletException.class})
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        session = req.getSession();
        User currentUser = (User) session.getAttribute(USER_ATTRIBUTE);
        if (currentUser != null) resp.sendRedirect(HOME_PATH);
        else req.getRequestDispatcher(REGISTRATION_PAGE_PATH).forward(req, resp);
    }

    @Override
    @SneakyThrows(IOException.class)
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        session = req.getSession();
        @NonNull String login = req.getParameter(LOGIN_PARAMETER);
        @NonNull String password = req.getParameter(PASSWORD_PARAMETER);
        @NonNull String name = req.getParameter(NAME_PARAMETER);
        @NonNull String surname = req.getParameter(SURNAME_PARAMETER);
        if (!userService.isLoginExist(login)) {
            @NonNull Integer id = UserService.getNextId();
            User userToRegister = new User(id, login, password, name, surname);
            if (userService.save(userToRegister)) {
                log.info(String.format("%s registered.", userToRegister));
                resp.sendRedirect(LOGIN_PATH);
            } else throw new IllegalStateException();
        } else doGet(req, resp);
    }
}