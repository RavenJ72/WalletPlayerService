package servlets;

import applicationServices.exceptions.BaseException;
import applicationServices.services.PlayerService;
import jbdcRepositories.connection.DatabaseManager;
import model.Player;
import serviceSingleton.PlayerServiceSingleton;
import services.PlayerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/myservlet")
public class MyServlet extends HttpServlet {



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получаем объект PrintWriter для записи данных в тело ответа
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        // Получаем объект PrintWriter для записи данных в тело ответа
        try {
            String message = "Привет, это мой первый сервлет!";
            System.out.println(message);
            Player player = new Player();
            System.out.println(player);

            try {
                DriverManager.getConnection("jdbc:postgresql://localhost:5432/wallet","postgres","admin");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


            response.getWriter().write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
