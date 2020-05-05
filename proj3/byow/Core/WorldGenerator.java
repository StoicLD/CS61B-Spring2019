package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import org.junit.Assert;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldGenerator
{
    private final int SEED;
    private Random random;
    public final int WIDTH;
    public final int HEIGHT;

    public final int MAX_ROOM_SIZE = 9;
    public final int MIN_ROOM_SIZE = 3;
    public final int EXTRA_ROOM_SIZE = 1;
    public final int numRoomEntries;
    public TETile[][] world;

    private List<Room> rooms;
    public WorldGenerator(int SEED, int _w, int _h, TETile[][] _world)
    {
        Assert.assertTrue(SEED >= 0);
        Assert.assertTrue(_w >= 50);
        Assert.assertTrue(_h >= 50);

        this.SEED = SEED;
        random = new Random(SEED);
        WIDTH = _w;
        HEIGHT = _h;
        world = _world;
        numRoomEntries = RandomUtils.uniform(random, 150,500);
        rooms = new ArrayList<>();
        generateRoom();
    }

    /**
     * 生成随机房间
     */
    public void generateRoom()
    {
        for(int i = 0; i < numRoomEntries; i++)
        {
            //一个数学小技巧，是的长方形不至于太长
            int size = RandomUtils.uniform(random, 1, 3 + EXTRA_ROOM_SIZE) * 2 + 1;
            int rectangularity = RandomUtils.uniform(random,0, 1 + size / 2) * 2;
            int w = size;
            int h = size;
            if (0 == RandomUtils.uniform(random,0, 100) % 2)
            {
                w += rectangularity;
            }
            else
            {
                h += rectangularity;
            }

            int x = RandomUtils.uniform(random, 0, (WIDTH - w) / 2) * 2 + 1;
            int y = RandomUtils.uniform(random, 0, (HEIGHT - h) / 2) * 2 + 1;
            //  根据以上算法生成一个新房间
            Room room = new Room(x, y, w, h);
            //  判断新房间是否与已存在的房间重叠
            boolean overlaps = false;
            for (var other : rooms)
            {
                //需要双重判断，泥中有我和我中有你
                if (isOverlap(room, other) || isOverlap(other, room))
                {
                    overlaps = true;
                    break;
                }
            }
            if (overlaps)
            {
                continue;
            }
            //  将新房间加入房间列表
            rooms.add(room);
            //  将新房间写入地图
            //StartRegion();
            for (int k = x; k < x + w; ++k)
            {
                for (int j = y; j < y + h; ++j)
                {
                    if(k == x || k == x + w - 1 || j == y || j == y + h - 1)
                        carve(k, j, Tileset.WALL);
                    else
                        carve(k, j, Tileset.FLOOR);
                }
            }
        }
    }

    /**
     * 检查是否重叠（扩大重叠范围）
     * @param room 房间对象
     * @return 重叠与否
     */
    private boolean isOverlap(Room room, Room other)
    {
        int xMin = room.getX();
        int xMax = room.getX() + room.getWidth() - 1;
        int yMin = room.getY();
        int yMax= room.getY() + room.getHeight() - 1;
/*        if((checkIndex(xMin, yMin) && (world[xMin][yMin].equals(Tileset.FLOOR) || world[xMin][yMin].equals(Tileset.WALL)))
                || (checkIndex(xMax, yMin) && (world[xMax][yMin].equals(Tileset.FLOOR) || world[xMax][yMin].equals(Tileset.WALL)))
                || (checkIndex(xMin, yMax) && (world[xMin][yMax].equals(Tileset.FLOOR) || world[xMin][yMax].equals(Tileset.WALL)))
                || (checkIndex(xMax, yMax) && (world[xMax][yMax].equals(Tileset.FLOOR) || world[xMax][yMax].equals(Tileset.WALL))))
            return true;*/
        if(isOverlapHelper(xMin, xMax, yMin, yMax, other.getX(), other.getY()))
            return true;
        if(isOverlapHelper(xMin, xMax, yMin, yMax, other.getX() + other.getWidth() - 1, other.getY()))
            return true;
        if(isOverlapHelper(xMin, xMax, yMin, yMax, other.getX(), other.getY() + other.getHeight() - 1))
            return true;
        if(isOverlapHelper(xMin, xMax, yMin, yMax, other.getX() + other.getWidth() - 1, other.getY() + other.getHeight() - 1))
            return true;
        return false;
    }

    private boolean checkIndex(int x, int y)
    {
        return x >= 0 && x < WIDTH && y >= 0 && y <= HEIGHT;
    }

    private boolean isOverlapHelper(int xMin, int xMax, int yMin, int yMax, int x, int y)
    {
        return x >= xMin && x <= xMax && y >= yMin && y <= yMax;
    }

    private void carve(int i, int j, TETile tileset)
    {
        if (tileset.equals(Tileset.FLOOR))
            world[i][j] = new TETile(tileset, Color.yellow);
        else if(tileset.equals(Tileset.WALL))
            world[i][j] = new TETile(tileset, Color.pink);
        else
            world[i][j] = new TETile(tileset, Color.black);
    }
    public void generateMaze()
    {

    }

    public void connect()
    {

    }
}
