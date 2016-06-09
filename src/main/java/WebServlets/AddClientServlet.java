package WebServlets;

import Common.Globals;
import Common.Position;
import Common.Starter;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jedrek on 10.06.16.
 */
public class AddClientServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        float x = Float.parseFloat(request.getParameter("x"));
        float y = Float.parseFloat(request.getParameter("y"));

        Position position = new Position(x,y);
        WebGlobals.getInstance().masterAgent.addClientLocation(position);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
}
