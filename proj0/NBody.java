public class NBody
{
    public static double readRadius(String name)
    {
        In in = new In(name);
        if(in.exists())
        {
            if(!in.isEmpty())
            {
                double first = in.readDouble();
                double r = in.readDouble();
                return r;
            }
        }
        return 0;
    }

    public static String cutDown(String s)
    {
        for(int i = 0; i< s.length(); i++)
        {
            if(s.indexOf(i) == '.')
            {
                return s.substring(0, i);
            }
        }
        return s;
    }

    public static Body[] readBodies(String filename)
    {
        In in = new In(filename);
        if(in.exists())
        {
            if(!in.isEmpty())
            {
                int num = in.readInt();
                double r = in.readDouble();
                Body[] planets = new Body[num];

                for(int i = 0; i < num; i++)
                {
                    planets[i] = new Body(in.readDouble(),
                                        in.readDouble(),
                                        in.readDouble(),
                                        in.readDouble(),
                                        in.readDouble(),
                                        in.readString());
                }
                return planets;
            }
        }
        return null;
    }




    public static void main(String[] args)
    {
        if(args.length != 3)
        {
            System.out.println("argument not equal to 3 !!!");
            return;
        }
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);

        String filename = args[2];
        double r =readRadius(filename);
        Body[] allPlanets = readBodies(filename);
        if(allPlanets == null)
            return;

        StdDraw.enableDoubleBuffering();
        /** Sets up the universe so it goes from
         * -100, -100 up to 100, 100 */
        StdDraw.setScale(-r, r);

        double timeSum = 0;
        double[] xForces = new double[allPlanets.length];
        double[] yForces = new double[allPlanets.length];
        while(timeSum < T)
        {
            timeSum += dt;
            /* Clears the drawing window. */
            StdDraw.clear();

            for(int i =0; i < allPlanets.length; i++)
            {
                Body b = allPlanets[i];
                xForces[i] = b.calcNetForceExertedByX(allPlanets);
                yForces[i] = b.calcNetForceExertedByY(allPlanets);
            }

            StdDraw.picture(0, 0, "./images/starfield.jpg");
            for (int i =0; i < allPlanets.length; i++)
            {
                Body b = allPlanets[i];
                b.update(dt, xForces[i], yForces[i]);
                b.draw();
            }
            /* Shows the drawing to the screen, and waits 2000 milliseconds. */
            StdDraw.show();
            StdDraw.pause(10);
        }
    }
}
