package Lab_2;

import java.util.Date;

public class CheckInfo {
    private double X, Y, R;
    private boolean isInArea;
    private Date ShotTime;

    public CheckInfo(double X, double Y, double R, boolean isInArea) {
        this.X = X;
        this.Y = Y;
        this.R = R;
        this.isInArea = isInArea;
    }

    public Date getShotTime() {
        return ShotTime;
    }

    public void setShotTime(Date shotTime) {
        ShotTime = shotTime;
    }

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public double getY() {
        return Y;
    }

    public void setY(double y) {
        Y = y;
    }

    public double getR() {
        return R;
    }

    public void setR(double r) {
        R = r;
    }

    public boolean isInArea() {
        return isInArea;
    }

    public void setInArea(boolean inArea) {
        isInArea = inArea;
    }
}