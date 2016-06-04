package WebServlets;

import Common.AgentClasses.Car;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by janbaraniewski on 04/06/16.
 */
@WebServlet(name = "CarsServlet")
public class CarsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("[");
        for(Car car : WebGlobals.getInstance().masterAgent.getCars()){
            response.getWriter().write("car"+car.getAid()+": [");
            response.getWriter().write("positionX: '"+car.getPosition().GetX()+"',positionY: '"+car.getPosition().GetY()+"'");
            response.getWriter().write("], ");
        }
        response.getWriter().write("]");
    }
}
