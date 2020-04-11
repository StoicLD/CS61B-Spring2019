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

    private static int WIDTH = 60;
    private static int HEIGHT = 40;
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
        addHexagon(world, new Position(10, 10), 4, Tileset.FLOWER);
        ter.renderFrame(world);
    }
}
