package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.awt.*;

public class Engine
{
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 70;
    public static final int HEIGHT = 50;
    private WorldGenerator worldGenerator;

    public int lastSeed;
    private boolean gameRunning = false;

    public Engine()
    {
        //worldGenerator = new WorldGenerator();
        ter.initialize(WIDTH, HEIGHT);
    }

    public void render()
    {
        if(worldGenerator != null)
            ter.renderFrame(worldGenerator.world);
    }

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard()
    {
    }

    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     * <p>
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     * <p>
     * In other words, both of these calls:
     * - interactWithInputString("n123sss:q")
     * - interactWithInputString("lww")
     * <p>
     * should yield the exact same world state as:
     * - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] interactWithInputString(String input)
    {
        // TODO: Fill out this method so that it run the engine using the input
        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().
        //
        // See proj3.byow.InputDemo for a demo of how you can make a nice clean interface
        // that works for many different input types.

        //只
        for (int i = 0; i < input.length(); i++)
        {
            char c = input.charAt(i);
            switch (c)
            {
                case 'n':
                {
                    if(gameRunning)
                        continue;
                    //读取后续的数字
                    StringBuilder number = new StringBuilder();
                    while (i + 1 < input.length() && input.charAt(i + 1) >= '0' && input.charAt(i + 1) <= '9')
                    {
                        number.append(input.charAt(i + 1));
                        i++;
                    }
                    try
                    {
                        int seed = Integer.parseInt(number.toString());
                        TETile[][] world = new TETile[WIDTH][HEIGHT];
                        for(int x = 0; x < world.length; x++)
                        {
                            for(int y = 0; y < world[0].length; y++)
                            {
                                world[x][y] = new TETile(Tileset.NOTHING, Color.black);
                            }
                        }

                        worldGenerator = new WorldGenerator(Integer.parseInt(number.toString()),
                                WIDTH, HEIGHT, world);
                        gameRunning = true;
                    }
                    catch (Exception e)
                    {
                        System.out.println("input number followed by n is invalid!");
                        return null;
                    }
                }
                case 'l':
                {
                    if(!gameRunning)
                        gameRunning = true;
                }
                case 'w':
                {

                }
                case 'a':
                {

                }
                case 's':
                {

                }
                case 'd':
                {

                }
                case ':':
                case 'q':
                {
                    //退出操作
                    gameRunning = false;
                    break;
                }
                default:
                {
                    //什么都不做
                }
            }
        }
        return worldGenerator == null ? null : worldGenerator.world;
    }

    private void move(int x, int y, int xIncrement, int yIncrement)
    {
        if(worldGenerator != null)
        {
            if(worldGenerator.world[x + xIncrement][y + yIncrement] != Tileset.WALL)
            {
                worldGenerator.world[x][y] = Tileset.FLOOR;
                worldGenerator.world[x + xIncrement][y + yIncrement] = Tileset.AVATAR;
            }
        }
    }
}
