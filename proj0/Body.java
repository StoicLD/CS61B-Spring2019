public class Body
{
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;

    private static final double gravity = 6.67 * 1e-11;

    public Body()
    {

    }

    public Body(Body b)
    {
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public Body(double xP, double yP, double xV,
                double yV, double m, String img)
    {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public double calcDistance(Body b)
    {
        double dx = xxPos - b.xxPos;
        double dy = yyPos - b.yyPos;
        return Math.sqrt(dx*dx + dy*dy);
    }

    //对b施加的力
    public double calcForceExertedBy(Body b)
    {
        double dis = calcDistance(b);
        return (mass * b.mass * gravity) / (dis * dis);
    }

    public double calcForceExertedByX(Body b)
    {
        double dis = calcDistance(b);

        //这里是向量啊啊啊啊，不应该绝对值的
        double dx = xxPos - b.xxPos;
        if(dx == 0)
            return 0;

        return dx * (mass * b.mass * gravity) / (dis * dis * dis);
    }

    public double calcForceExertedByY(Body b)
    {
        double dis = calcDistance(b);
        double dy = yyPos - b.yyPos;
        if(dy == 0)
            return 0;

        return dy * (mass * b.mass * gravity) / (dis * dis * dis);
    }

    public double calcNetForceExertedByX(Body[] allBodys)
    {
        double sumX = 0;
        //注意是传入引用的
        for(int i=0; i < allBodys.length; i++)
        {
            if(!allBodys[i].equals(this))
            {
                sumX += allBodys[i].calcForceExertedByX(this);
            }
        }
        return sumX;
    }

    public double calcNetForceExertedByY(Body[] allBodys)
    {
        double sumY = 0;
        //注意是传入引用的
        for(int i=0; i < allBodys.length; i++)
        {
            if(!allBodys[i].equals(this))
            {
                sumY += allBodys[i].calcForceExertedByY(this);
            }
        }
        return sumY;
    }

    public void update(double dt, double fX, double fY)
    {
        double dVx = dt * fX / mass;
        double dVy = dt * fY / mass;
        xxVel += dVx;
        yyVel += dVy;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    public void draw()
    {
        StdDraw.picture(xxPos, yyPos, "./images/"+imgFileName);
    }
}