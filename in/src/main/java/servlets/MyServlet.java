package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/myservlet")
public class MyServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получаем объект PrintWriter для записи данных в тело ответа
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        // Получаем объект PrintWriter для записи данных в тело ответа
        try {
            String message = "Привет, это мой первый сервлет!";
            System.out.println(message); // Выводим сообщение в консоль сервера
            response.getWriter().write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
