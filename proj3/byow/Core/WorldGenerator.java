package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import org.junit.Assert;

import java.awt.*;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldGenerator
{
    private final int SEED;
    private Random random;
    private final Point[] POSSIBLE_DIR;
    private List<Room> rooms;
    private boolean[][] isMaze;
    private enum Status
    {
        nothing,
        maze,
        room
    }

    public final int WIDTH;
    public final int HEIGHT;

    public final int EXTRA_ROOM_SIZE = 1;
    public final int numRoomEntries;
    public TETile[][] world;
    public final int WINDING_PERCENT = 60;

    public WorldGenerator(int SEED, int _w, int _h, TETile[][] _world)
    {
        Assert.assertTrue(SEED >= 0);
        Assert.assertTrue(_w >= 50);
        Assert.assertTrue(_h >= 50);
        POSSIBLE_DIR = new Point[]{new Point(1, 0), new Point(-1, 0), new Point(0, 1), new Point(0, -1)};
        this.SEED = SEED;
        random = new Random(SEED);
        WIDTH = _w;
        HEIGHT = _h;
        world = _world;
        numRoomEntries = RandomUtils.uniform(random, 50,100);
        rooms = new ArrayList<>();
        isMaze = new boolean[WIDTH][HEIGHT];
        generateRoom();

        //  空余区域用迷宫填充
        /*
        for (int x = 1; x < WIDTH; x += 2)
        {
            for (int y = 1; y < HEIGHT; y += 2)
            {
                if (canMakeMaze(x, y))
                {
                    generateMaze(x, y);
                }
            }
        }*/

    }

    /**
     * 生成随机房间
     */
    private void generateRoom()
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

    private void carve(Point p, TETile tile)
    {
        carve(p.getX(), p.getY(), tile);
    }

/*    private void carveMaze(Point p, Point dir)
    {
        carve(p, Tileset.FLOOR);
        //左右
        if(dir.equals(POSSIBLE_DIR[0]) || dir.equals(POSSIBLE_DIR[1]))
        {
            carve(Point.add(p, POSSIBLE_DIR[2]), Tileset.WALL);
            carve(Point.add(p, POSSIBLE_DIR[3]), Tileset.WALL);
        }
        //上下
        else if(dir.equals(POSSIBLE_DIR[2]) || dir.equals(POSSIBLE_DIR[3]))
        {
            carve(Point.add(p, POSSIBLE_DIR[0]), Tileset.WALL);
            carve(Point.add(p, POSSIBLE_DIR[1]), Tileset.WALL);
        }
    }

    private void generateMaze(int x, int y)
    {
        List<Point> cells = new ArrayList<>();
        Point lastDir = new Point(POSSIBLE_DIR[RandomUtils.uniform(random, 0, POSSIBLE_DIR.length)]);
        Point start = new Point(x, y);
        //carve(start);
        carveMaze(start, lastDir);
        cells.add(start);
        while(cells.size() != 0)
        {
            Point cell = cells.get(cells.size() - 1);

            //  检查可生长的方向
            List<Point> unmadeCells = new ArrayList<>();
            for(Point dir : POSSIBLE_DIR)
            {
                if (canMakeMaze(Point.add(cell, dir)))
                {
                    unmadeCells.add(dir);
                }
            }

            if(unmadeCells.size() != 0)
            {
                Point dir;
                if((unmadeCells.contains(lastDir)) && (RandomUtils.uniform(random,0, 100) > WINDING_PERCENT))
                {
                    dir = lastDir;
                }
                else
                {
                    //  换个方向生长
                    dir = unmadeCells.get(RandomUtils.uniform(random, 0, unmadeCells.size()));
                }

                //  TODO: 记录迷宫
                //Carve(cell + dir);
                //Carve(cell + dir * 2);
                Point nextCell = Point.add(cell, dir);
                carveMaze(nextCell, dir);

                cells.add(nextCell);
                lastDir = dir;
            }
            else
            {
                //  没有任何一个可生长方向，此路径结束
                //cells.RemoveAt(cells.Count - 1);
                lastDir = new Point(0, 0);
            }
        }
    }

    private boolean canMakeMaze(int x, int y)
    {
        return canMakeMaze(new Point(x, y));
    }

    private boolean canMakeMaze(Point p)
    {
        int x = p.getX();
        int y = p.getY();
        //以自身为中心，2为半径（不包括自身）的范围内，必须全部是nothing
        //也就是一个5 * 5的格子
        for(int i = -2; i <= 2; i++)
        {
            for(int j = -2; j <= 2; j++)
            {
                if(!checkIndex(x + i, y + j))
                    return false;
                if(!world[x + i][y + j].equals(Tileset.NOTHING))
                    return false;
            }
        }
        return true;
    }*/

    //生成通道，连接房间
    //采用一种比较简单的方法
    // (1) 任意选择一个没有选中的房间
    // (2) 如果该房间没有联通 --> (3)。否则回到(1)
    // (3) 任意选择房间外延的一个未被选中的位置，如果该位置是最后一个位置了，选择(4)
    //     否则像外侧发出射线，如果碰撞到了WALL，则联通。否则回到(3)
    // (4) 从该位置出发，衍生通道
    private void connect(List<Room> rooms)
    {
        boolean[] connectedRooms = new boolean[rooms.size()];

    }

    /**
     * 返回在rooms中的下标，如果没找到返回-1
     * @param rooms 房间列表
     * @param p 点
     * @return
     */
    private int belongToWhichRoom(List<Room> rooms, Point p)
    {
        int x = p.getX();
        int y = p.getY();
        int i = 0;
        for(var room : rooms)
        {
            int xMin = room.getX();
            int xMax = room.getX() + room.getWidth() - 1;
            int yMin = room.getY();
            int yMax= room.getY() + room.getHeight() - 1;
            if(x >= xMin && x <= xMax && y >= yMin && y <= yMax)
                return i;
            i++;
        }
        return -1;
    }
}
