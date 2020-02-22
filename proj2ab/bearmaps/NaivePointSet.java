package bearmaps;
import java.util.ArrayList;
import java.util.List;

public class NaivePointSet implements PointSet
{
    List<Point> points;
    public NaivePointSet(List<Point> points)
    {
        this.points = new ArrayList<>(points);
    }

    @Override
    public Point nearest(double x, double y)
    {
        Point target = new Point(x, y);
        Point minPoint = points.get(0);
        double minDis = Point.distance(target, minPoint);
        for(Point p : points)
        {
            double currDis = Point.distance(p, target);
            if(currDis < minDis)
            {
                minDis = currDis;
                minPoint = p;
            }
        }
        return minPoint;
    }
}
