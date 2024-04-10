package Utils;

public class Vector2 {
    private double angle;
    private double x;
    private double y;
    private double magnitude;

    private double xProjection;
    private double yProjection;

    //#region initialisers
    public Vector2() {initialiseWithCoords(0, 0, 0);}
    public Vector2(float angle) {initialiseWithAngle(angle, 0);}
    public Vector2(float x, float y) {initialiseWithCoords(x, y, 0);}
    public Vector2(float x, float y, float magnitude) {initialiseWithCoords(x, y, magnitude);}

    private void initialiseWithCoords(float x, float y, float magnitude) {
        this.setAxes(x, y);
        this.setMagnitude(magnitude);
    }

    private void initialiseWithAngle(float angle, float magnitude) {
        this.setAngle(angle);
        this.setMagnitude(magnitude);
    }
    //#endregion initialisers

    //#region setters and getters
    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
        this.setProjections();
    }

    public void setX(double x) {
        this.x = x;
        this.angle = this.getXAsRadians();
        this.setProjections();
    }

    public void setY(double y) {
        this.y = y;
        this.angle = this.getYAsRadians();
        this.setProjections();
    }

    public void setAxes(double x, double y) {
        this.x = x;
        this.y = y;
        this.angle = this.getAxesAsRadians();
        this.setProjections();
    }

    public void setAxes(double[] axes){
        this.setAxes(axes[0], axes[1]);
    }

    public void setAngle(double angleDegrees) {
        this.angle = Math.toRadians(angleDegrees);
        this.setAxes(this.getAngleAsAxes());
        // setAxes sets projections
    }

    public double getAngle() {
        return Math.toDegrees(this.angle);
    }

    public double[] getAngleAsAxes() {
        return new double[]{Math.cos(this.angle), Math.sin(this.angle)};
    }

    public double getXAsRadians() {
        return Math.acos(this.x);
    }

    public double getXAsDegrees() {
        return Math.toDegrees(this.getXAsRadians());
    }

    public double getYAsRadians() {
        return Math.asin(this.y);
    }

    public double getYAsDegrees() {
        return Math.toDegrees(this.getXAsRadians());
    }

    public double getAxesAsRadians() {
        return this.getXAsRadians();
    }

    public double getAxesAsDegrees() {
        return this.getXAsDegrees();
    }

    private void setProjections() {
        this.xProjection = this.x * this.magnitude;
        this.yProjection = this.y * this.magnitude;
    }
    //#endregion setters and getters

    public void invertX() {
        this.setX(this.x * -1);
    }

    public void invertY() {
        this.setY(this.y * -1);
    }

    public void applyProjectionsToPosition(Position position) {
        position.x += this.xProjection;
        position.y += this.yProjection;
    }
}
