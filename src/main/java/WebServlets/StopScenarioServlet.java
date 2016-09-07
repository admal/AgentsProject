package WebServlets;

import Scenarios.ScenarioException;
import Scenarios.ScenarioOnExectionException;
import com.google.gson.Gson;
import jade.wrapper.StaleProxyException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by adam on 9/7/16.
 */
public class StopScenarioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String in = request.getParameter("index");
        int index = Integer.parseInt(in);

        try {
            WebGlobals.getInstance().scenarios.get(index).Stop();
        } catch (StaleProxyException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println(new Gson().toJson(e.getMessage()));
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
