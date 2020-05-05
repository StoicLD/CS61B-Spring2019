package byow.Core;

public class Room
{
    //需要注意的是，Room是有一圈围墙的
    private int width;
    private int height;
    //左下角坐标
    private int x;
    private int y;

    public Room(int x, int y, int width, int height)
    {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }
}
