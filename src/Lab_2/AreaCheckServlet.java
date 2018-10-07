package Lab_2;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

@WebServlet(name = "AreaCheckServlet", urlPatterns = "/checking")
public class AreaCheckServlet extends HttpServlet {

    private ServletConfig config;
    private List<RequestInfo> list = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    public void destroy() {
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("control");
    }

    String getParameter(HttpServletRequest request, String namePrefix) {
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();

            if (name.startsWith(namePrefix))
                return request.getParameter(name);
        }

        throw new RuntimeException("Missing query parameter!");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //CheckingInfo bean = (CheckingInfo) request.getSession().getAttribute("CheckingInfoBean");

        WebApplicationContext webAppContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        CheckingInfo bean = (CheckingInfo) webAppContext.getBean("CheckingInfoBean");
        // CheckingInfo bean = (CheckingInfo) request.getSession().getAttribute("scopedTarget.CheckingInfoBean");

        try {
            RequestInfo p = new RequestInfo(Double.parseDouble(this.getParameter(request, "X")),
                    Double.parseDouble(this.getParameter(request, "Y")), Double.parseDouble(this.getParameter(request, "R")));
            p.isInArea = checkArea(p.x, p.y, p.R);
            bean.registerResult((p));
        } catch (Exception e) {
            e.printStackTrace();
            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }

        String pageTitle = "Servlet example";
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML> <html> <head> <meta charset='UTF-8'> <title>Points</title>" +
                "            <link rel='stylesheet' type='text/css' href='css/main.css' />" +
                "            </head> <body> ");
        out.println("<form action=\"index.jsp\" method=\"GET\" style=\"padding:20px 0px;\">" +
                "<button class='submit' style=\"color: #ffffff\"> Return to HOME </button>  <br />" +
                "</form>");
        out.println("<br /> <table class='points'> <tr><td>X coordinate</td><td>Y coordinate</td><td>Radius</td><td>Entrance</td></tr>");

        for (RequestInfo r : bean.getAllResults()) {
            out.println("<tr>");
            out.println("<td>");
            out.println(r.x);
            out.println("</td>");
            out.println("<td>");
            out.println(r.y);
            out.println("</td>");
            out.println("<td>");
            out.println(r.R);
            out.println("</td>");
            out.println("<td>");

            if (r.isInArea) {
                out.println("Yes");
            } else {
                out.println("No");
            }

            out.println("</td>");
            out.println("</tr>");
        }

        out.println("</table> </body> </html>");

    }

    public class RequestInfo {
        public double x;
        public double y;
        public double R;
        public boolean isInArea;

        RequestInfo(double x, double y, double r) {
            this.x = x;
            this.y = y;
            this.R = r;
        }
    }

    public static boolean checkArea(double x, double y, double R) {
        if (x >= 0 && y <= 0 && x * x + y * y <= R * R * 0.25) {
            return true;
        }
        if (x <= 0 && y <= 0 && y >= -2 * x - R) {
            return true;
        }
        if (x >= 0 && y >= 0 && x <= R && y <= R) {
            return true;
        }
        return false;
    }

}
