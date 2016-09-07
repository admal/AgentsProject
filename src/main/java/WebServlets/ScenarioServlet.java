package WebServlets;


import Common.Position;
import Scenarios.ScenarioException;
import Scenarios.ScenarioOnExectionException;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "ScenarioServlet")
public class ScenarioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

//        int index =Integer.parseInt(request.getParameter("index"));
        String in = request.getParameter("index");
        int index = Integer.parseInt(in);

        try {
            WebGlobals.getInstance().scenarios.get(index).Start(false);
        } catch (ScenarioException | ScenarioOnExectionException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println(new Gson().toJson(e.getMessage()));
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");


        String retJson = new Gson().toJson(WebGlobals.getInstance().scenarios);
        response.getWriter().write(retJson);
    }
}
