package web.servlet.error;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;

@WebServlet(name = "ErrorNotFoundServlet", value = "/error-404")
public class ErrorNotFoundServlet extends HttpServlet {
    private static final String ERROR_404_PATH = "/pages/error/404.jsp";

    @Override
    @SneakyThrows({IOException.class, ServletException.class})
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher errorDispatcher = req.getRequestDispatcher(ERROR_404_PATH);
        errorDispatcher.forward(req, resp);
    }
}