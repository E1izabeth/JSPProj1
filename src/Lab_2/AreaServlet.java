package Lab_2;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.tempuri.flatzone.*;
import org.tempuri.flatzone.tools.ZoneVisitor;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;

public class AreaServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doWork(req, resp);
        } catch (XMLStreamException ex) {
            throw new ServletException(ex);
        } catch (JAXBException ex) {
            throw new ServletException(ex);
        }
    }

    private void doWork(HttpServletRequest req, HttpServletResponse resp) throws IOException, XMLStreamException, JAXBException {
        double rValue = Double.parseDouble(req.getParameter("R"));
        String rStr = Double.toString(rValue);

        int r = 50; // for shape rendering

        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        resp.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        resp.setDateHeader("Expires", 0); // Proxies.
        resp.setContentType("image/png");

        // String xml = String.join("", Files.readAllLines(Paths.get(request.getServletContext().getRealPath("FlatZone.xml"))));
        File localXmlFile = new File(req.getServletContext().getRealPath("FlatZone.xml"));

        Unmarshaller un = JAXBContext.newInstance(ZoneType.class).createUnmarshaller();
        XMLStreamReader xsr = XMLInputFactory.newFactory().createXMLStreamReader(new FileInputStream(localXmlFile));

        ZoneType zone = (ZoneType) un.unmarshal(xsr, ZoneType.class).getValue();

        BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        final Graphics2D g = image.createGraphics();
        g.setBackground(Color.white);
        g.clearRect(0, 0, 200, 200);

        AffineTransform labelsTransform = AffineTransform.getTranslateInstance(100, 100);

        AffineTransform shapeTransform = AffineTransform.getTranslateInstance(100, 100);
        shapeTransform.concatenate(AffineTransform.getScaleInstance(1, -1));
        g.setTransform(shapeTransform);

        g.setPaint(Color.blue);
        for (ShapeType o : zone.getTriangleOrCircleOrRectangle()) {
            ZoneVisitor.apply(o, new ZoneVisitor() {
                @Override
                public void visitCircle(CircleType cir) {
                    g.fillArc((int)((cir.getOX() - cir.getR())* r ), (int)((cir.getOY() - cir.getR())* r ), (int)(cir.getR() * r * 2), (int)(cir.getR() * r * 2), (int)(cir.getQuarter() * 90), 90);
                }

                @Override
                public void visitRectangle(RectangleType rect) {
                    g.fillRect((int)(rect.getX() * r), (int)(rect.getY() * r), (int)(rect.getW() * r), (int)(rect.getH() * r));
                }

                @Override
                public void visitTriangle(TriangleType tri) {
                    int[] xp = {(int)(tri.getX1() * r), (int)(tri.getX2() * r), (int)(tri.getX3() * r)};
                    int[] yp = {(int)(tri.getY1() * r), (int)(tri.getY2() * r), (int)(tri.getY3() * r)};

                    g.fillPolygon(xp, yp, 3);
                }
            });
        }

        g.setPaint(Color.magenta);

        WebApplicationContext webAppContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        CheckingInfo bean = (CheckingInfo) webAppContext.getBean("CheckingInfoBean");

        LinkedList<AreaCheckServlet.RequestInfo> list = bean.getResults(rValue);
        if (list != null ) {
            for (AreaCheckServlet.RequestInfo info : list) {
                if(info.isInArea)
                    g.setPaint(Color.green);
                else
                    g.setPaint(Color.red);
                g.fillArc((int) (info.x * r/rValue - 3), (int) (info.y * r / rValue - 3), 7, 7, 0, 360);
            }
        }
        g.setTransform(labelsTransform);
        g.setPaint(Color.black);

        Font f = g.getFont();
        Font f2 = f.deriveFont(10);
        g.setFont(f2);
        g.drawString("0", 0, 0);
        g.drawString(rStr, r, 0);
        g.drawString(rStr, 0, -r);
        g.drawString("-" + rStr, -r, 0);
        g.drawString("-" + rStr, 0, r);

        g.drawLine(0, 100, 0, -100);
        g.drawLine(-100, 0, 100, 0);

        ImageIO.write(image, "png", resp.getOutputStream());
    }
}
