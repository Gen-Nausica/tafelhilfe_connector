import objects.Postobjekt;
import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;


@WebServlet(name = "Connector")
public class Connector extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println(request);
        StringBuffer jb = new StringBuffer();
        String line = null;
        JSONObject jsonObject = null;
        Postobjekt po = new Postobjekt(request.getParameter("name"),request.getParameter("address"), request.getParameter("plz"), request.getParameter("ort"), request.getParameter("anpsrechpartner"), request.getParameter("tel"), request.getParameter("fax"), request.getParameter("mail"), request.getParameter("url"), request.getParameter("kontaktart"), request.getParameter("option1"), request.getParameter("option2"), request.getParameter("option3"), request.getParameter("option4"), request.getParameter("kommentar"));
       if(po != null){
            if(po.verify()){
                po.postQuarantaeneHelden();
                po.postWirHelfen();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
