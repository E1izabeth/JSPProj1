package org.tempuri.flatzone.tools;

import org.tempuri.flatzone.CircleType;
import org.tempuri.flatzone.RectangleType;
import org.tempuri.flatzone.ShapeType;
import org.tempuri.flatzone.TriangleType;

import java.util.logging.Logger;

public interface ZoneVisitor {
    void visitCircle(CircleType circle);
    void visitRectangle(RectangleType rect);
    void visitTriangle(TriangleType triangle);

    public static void apply(ShapeType o, ZoneVisitor visitor) {
        if (o instanceof CircleType) {
            visitor.visitCircle((CircleType)o);
        } else if (o instanceof RectangleType) {
            visitor.visitRectangle((RectangleType)o);
        } else if (o instanceof TriangleType) {
            visitor.visitTriangle((TriangleType)o);
        } else {
            Logger.getLogger(ZoneVisitor.class.getName()).severe("Unknown zone shape kind");
        }
    }
}
