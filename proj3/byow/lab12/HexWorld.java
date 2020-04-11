package byow.lab12;

import org.junit.Test;

import static org.junit.Assert.*;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld
{
    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    private static TETile randomTile()
    {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.GRASS;
            case 3: return Tileset.AVATAR;
            case 4: return Tileset.TREE;
            default: return Tileset.SAND;
        }
    }


    /**
     * 添加一个六边形瓦片
     * @param world 世界数组
     * @param p     六边形的左下角
     * @param s     六边形的size
     * @param t     瓦片类型
     */
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t)
    {
        //首先要保证输入合法
        int upperLeftX = p.x - s + 1;
        int upperLeftY = p.y + 2 * s - 1;
        int lowerRightX = p.x + 2 * s - 2;
        int lowerRightY = p.y;
        if (upperLeftX < 0 || lowerRightX >= world.length || upperLeftY >= world[0].length || lowerRightY < 0)
        {
            System.out.println("Error!!");
            return;
        }
        //下半部分
        for (int i = s - 1; i >= 0; i--)
        {
            for (int j = 0; j < s + 2 * i; j++)
            {
                world[upperLeftX + j + s - i - 1][lowerRightY + i] = t;
            }
        }

        //上半部分
        for (int i = 0; i < s; i++)
        {
            for (int j = 0; j < 3 * s - 2 - 2 * i; j++)
            {
                world[upperLeftX + i + j][upperLeftY - s + i + 1] = t;
            }
        }
    }

    /**
     * 创建一个随机Title的pattern
     * @param world 世界数组
     * @param p 中间列的最下面一个hex的左下角
     * @param s 大小
     */
    public static void makeHexagonPattern(TETile[][] world, Position p, int s)
    {
        makeColumnHexagonRandom(world, new Position(p.x, p.y), s, 2 * s - 1);
        //先往左走
        for(int i = 1; i < s; i++)
        {
            //makeColumnHexagon(world, new Position(p.x - i * (2 * s - 1), p.y + i * s), s, 2 * s - 1 - i, randomTile());
            makeColumnHexagonRandom(world, new Position(p.x - i * (2 * s - 1), p.y + i * s), s, 2 * s - 1 - i);
        }

        //再往右边走
        for(int i = 1; i < s; i++)
        {
            makeColumnHexagonRandom(world, new Position(p.x + i * (2 * s - 1), p.y + i * s), s, 2 * s - 1 - i);
        }
    }

    public static void makeColumnHexagon(TETile[][] world, Position p, int s, int colSize, TETile t)
    {
        for(int i = 0; i < colSize; i++)
        {
            p.y += 2 * s;
            addHexagon(world, p, s, t);

        }
    }

    // p 是最下面一个左下角的坐标
    public static void makeColumnHexagonRandom(TETile[][] world, Position p, int s, int colSize)
    {
        for(int i = 0; i < colSize; i++)
        {
            p.y += 2 * s;
            addHexagon(world, p, s, randomTile());
        }
    }


    private static int WIDTH = 80;
    private static int HEIGHT = 60;
    public static void main(String[] args)
    {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1)
        {
            for (int y = 0; y < HEIGHT; y += 1)
            {
                world[x][y] = Tileset.NOTHING;
            }
        }
        //addHexagon(world, new Position(10, 10), 4, Tileset.FLOWER);
        makeHexagonPattern(world, new Position( WIDTH / 2, 10), 3);
        ter.renderFrame(world);
    }
}
