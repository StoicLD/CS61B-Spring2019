package byow.Core;

import org.junit.Assert;

public class Point
{
    private int x;
    private int y;

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Point(Point other)
    {
        x = other.getX();
        y = other.getY();
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public static Point add(Point p1, Point p2)
    {
        return new Point(p1.x + p2.x, p1.y + p2.y);
    }

    public static Point subtract(Point p1, Point p2)
    {
        return new Point(p1.x - p2.x, p1.y - p2.y);
    }

    public static Point multiply(Point p, int m)
    {
        return new Point(p.x * m, p.y * m);
    }

    @Override
    public boolean equals(Object o)
    {
        Assert.assertSame(o.getClass(), this.getClass());
        Point p = (Point)o;
        return p.getX() == x && p.getY() == y;
    }


}
