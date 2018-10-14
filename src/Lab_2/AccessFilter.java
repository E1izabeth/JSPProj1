package Lab_2;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccessFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws java.io.IOException, ServletException{
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if (req.getQueryString() != ""){
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else
        {
            resp.setStatus(400);
        }
        try {
            Integer x = Integer.parseInt(req.getParameter("X"));
            Integer y = Integer.parseInt(req.getParameter("Y"));
            Integer r = Integer.parseInt(req.getParameter("R"));

            if (x <= 4 && x >= -4 && y > -5 && y < 3 && r >= 1 && r <= 3)
            {
                resp.sendRedirect("index.jsp");
            }
            else
            {
                resp.setStatus(400);
            }
        }
        catch(Exception ex)
        {
            resp.setStatus(400);
        }

        resp.sendRedirect("index.jsp");
    }
}