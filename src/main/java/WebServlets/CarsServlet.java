package WebServlets;

import Common.AgentClasses.Car;
import Common.AgentType;
import Common.WebModels.CarAgentWebModel;
import MasterAgent.IMasterAgent;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        IMasterAgent master = WebGlobals.getInstance().masterAgent;
        if (master == null) {
            response.getWriter().write("null");
            return;
        }

        List<Car> cars = master.getCars();
        List<CarAgentWebModel> viewModels = new ArrayList<CarAgentWebModel>();
        for (Car car :
                cars) {
            viewModels.add(new CarAgentWebModel(AgentType.Car, car.getPosition(), car.getPosition(), 100, car.getAid().getLocalName()));
        }
        String retJson = new Gson().toJson(viewModels);
        response.getWriter().write(retJson);
    }
}
