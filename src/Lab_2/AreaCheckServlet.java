package Lab_2;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@WebServlet(name = "AreaCheckServlet", urlPatterns = "/checking")
public class AreaCheckServlet extends HttpServlet {

    private ServletConfig config;
    private List<RequestInfo> list = null;

    @Override
    public void init (ServletConfig config) throws ServletException
    {
        this.config = config;
    }
    @Override
    public void destroy() {}
    @Override
    public ServletConfig getServletConfig()
    {
        return config;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("control");
    }

    public static <T> Stream<T> enumerationAsStream(Enumeration<T> e) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
            new Iterator<T>() {
                public T next() {
                    return e.nextElement();
                }
                public boolean hasNext() {
                    return e.hasMoreElements();
                }
            },
            Spliterator.ORDERED
        ), false);
    }

    String getParameter(HttpServletRequest request, String namePrefix) {
        Optional<String> needle = enumerationAsStream(request.getParameterNames()).filter(x -> x.startsWith(namePrefix)).findAny();
        if (!needle.isPresent())
            throw new RuntimeException("Missing query parameter!");

        return request.getParameter(needle.get());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(list==null){
            list=new ArrayList<RequestInfo>();
            config.getServletContext().setAttribute("list",list);
        }
        try{


            RequestInfo p = new RequestInfo(Double.parseDouble(this.getParameter(request, "X")),
                    Double.parseDouble(this.getParameter(request, "Y")), Double.parseDouble(this.getParameter(request, "R")));
            p.isInArea = checkArea(p.x, p.y, p.R);
            list.add(p);
        } catch (Exception e){
            e.printStackTrace();
            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }

        String pageTitle="Servlet example";
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML> <html> <head> <meta charset='UTF-8'> <title>Points</title>" +
                "            <link rel='shortcut icon' href='img/favicon.ico'>" +
                "            <link rel='stylesheet' type='text/css' href='css/main.css'>" +
                "            </head> <body> <center>");
        out.println("<div class='container' style='padding:20px 0px;'> " +
                "<form action=\"index.jsp\" method=\"GET\">" +
                "<button class='submit' style=\"color: #ffffff\"> Return to HOME </button>  <br>" +
                "</form>");
        out.println("<br> <table class='points'> <tr><td>X coordinate</td><td>Y coordinate</td><td>Radius</td><td>Entrance</td></tr>");

        for(int i=0;i<list.size();i++) {
            out.println("<tr>");
            out.println("<td>");
            out.println(list.get(i).x);
            out.println("</td>");
            out.println("<td>");
            out.println(list.get(i).y);
            out.println("</td>");
            out.println("<td>");
            out.println(list.get(i).R);
            out.println("</td>");
            out.println("<td>");

            if(checkArea(list.get(i).x, list.get(i).y, list.get(i).R)){
                out.println("Yes");
                list.get(list.size()-1).isInArea=true;
            }
            else{
                out.println("No");
                list.get(list.size()-1).isInArea=false;
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

        RequestInfo(double x, double y, double r){
            this.x = x;
            this.y = y;
            this.R = r;
        }
    }

    public static boolean checkArea(double x, double y, double R){
        if(x<=0 && y<=0 && x*x+y*y<=R*R){
            return true;
        }
        if(x<=0 && y>=0 && y<=2*x+R){
            return true;
        }
        if(x>=0 && y<=0 && x<=R && y>=-R){
            return true;
        }
        return false;
    }

}
