package web.servlet.error;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;

@WebServlet(name = "ErrorForbiddenServlet", value = "/error-403")
public class ErrorForbiddenServlet extends HttpServlet {
    private static final String ERROR_403_PATH = "/pages/error/403.jsp";

    @Override
    @SneakyThrows({IOException.class, ServletException.class})
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher errorDispatcher = req.getRequestDispatcher(ERROR_403_PATH);
        errorDispatcher.forward(req, resp);
    }
}