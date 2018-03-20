/**
 * Class Planet.
 * @author Feriz
 * @date 2017.03.17
 */
public class Planet {
    public double xxPos, yyPos, xxVel, yyVel, mass;
    public String imgFileName;
    public static final double gravity = 6.67e-11;
    /**
     * Constructor for defining an instance for class Planet.
     */
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    /**
     * Constructor for defining an instance from an existing instance.
     */
    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }
    /**
     * Method calcDistance.
     */
    public double calcDistance(Planet p) {
        double distance;
        distance = Math.sqrt(Math.pow((xxPos - p.xxPos), 2) +
                             Math.pow((yyPos - p.yyPos), 2));
        return distance;
    }
    /**
     * Method calcForceExertedBy.
     */
    public double calcForceExertedBy(Planet p) {
        double forceExerted;
        forceExerted = gravity * mass * p.mass /
            (calcDistance(p) * calcDistance(p));
        return forceExerted;
    }
    /**
     * Method calcForceExertedByX.
     */
    public double calcForceExertedByX(Planet p) {
        double forceExertedX;
        forceExertedX = calcForceExertedBy(p) * (p.xxPos - xxPos)
            / calcDistance(p);
        return forceExertedX;
    }
    /**
     * Method calcForceExertedByY.
     */
    public double calcForceExertedByY(Planet p) {
        double forceExertedY;
        forceExertedY = calcForceExertedBy(p) * (p.yyPos - yyPos)
            / calcDistance(p);
        return forceExertedY;
    }
    /**
     * Method calcNetForceExertedByX.
     */
    public double calcNetForceExertedByX(Planet[] q) {
        double NetForceExertedByX = 0;
        for (Planet i : q) {
            if ((xxPos == i.xxPos) && (yyPos == i.yyPos) && (xxVel == i.xxVel) &&
                (yyVel == i.yyVel) && (mass == i.mass) && (imgFileName == i.imgFileName))
                continue;
            NetForceExertedByX += calcForceExertedByX(i);
        }
        return NetForceExertedByX;
    }
    /**
     * Method calcNetForceExertedByY.
     */
    public double calcNetForceExertedByY(Planet[] q) {
        double NetForceExertedByY = 0;
        for (Planet i : q) {
            if ((xxPos == i.xxPos) && (yyPos == i.yyPos) && (xxVel == i.xxVel) &&
                (yyVel == i.yyVel) && (mass == i.mass) && (imgFileName == i.imgFileName))
                continue;
            NetForceExertedByY += calcForceExertedByY(i);
        }
        return NetForceExertedByY;
    }
    /**
     * Method update
     */
    public void update(double dt, double fx, double fy) {
        double ax, ay;
        ax = fx / mass;
        ay = fy / mass;
        xxVel = xxVel + ax * dt;
        yyVel = yyVel + ay * dt;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }
    /**
     * Method Draw.
     */
    public void draw() {
        String imageToDraw = "./images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, imageToDraw);
    }
}
