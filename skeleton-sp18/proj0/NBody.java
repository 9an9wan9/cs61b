/**
 * NBody is a class that will actually run your simulation.
 * This class will have NO constructor.
 * The goal of this class is to simulate a universe specified in one of the data files.
 * @author Feriz
 * @date 2017.03.18
 */
public class NBody {
    /**
     * Method readRadius.
     * Return a double corresponding to the radius of the universe in that file.
     */
    public static double readRadius(String filename) {

        In universe = new In(filename);
        int counts = universe.readInt();
        double radius = universe.readDouble();

        return radius;

    }
    /**
     * Method readPlanets.
     * Given a file name, it should return an array of Planets corresponding to
     * the planets in the file.
     */
    public static Planet[] readPlanets(String filename) {
        In file = new In(filename);
        int counts = file.readInt();
        double radius = file.readDouble();
        Planet[] planets = new Planet[counts];
        /**
         * @note initialize an array's element after define.
         */
        for (int i = 0; i < counts; i++) {
            planets[i] = new Planet(0,0,0,0,0," ");
        }
        for (int i = 0; i < counts; i++) {
            planets[i].xxPos = file.readDouble();
            planets[i].yyPos = file.readDouble();
            planets[i].xxVel = file.readDouble();
            planets[i].yyVel = file.readDouble();
            planets[i].mass = file.readDouble();
            planets[i].imgFileName = file.readString();
        }
        return planets;
    }
    /**
     * Main method.
     * Drawing the Initial Universe State.
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please enter command with arguments!");
        }
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        Planet[] planets = readPlanets(filename);
        double scale = readRadius(filename);
        String imageToDraw = "./images/starfield.jpg";
        StdDraw.setScale(-scale, scale);
        StdDraw.clear();
        StdDraw.picture(0, 0, imageToDraw);
        for (Planet i : planets) {
            i.draw();
        }
        StdDraw.enableDoubleBuffering();
        for (int time = 0; time < T; time += dt) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < planets.length; i++) {
            planets[i].update(dt, xForces[i], yForces[i]);
            StdDraw.picture(0, 0, imageToDraw);
            planets[i].draw();
            StdDraw.show(1);
            }
        }
    }
}
