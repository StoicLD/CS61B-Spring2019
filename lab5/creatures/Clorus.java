package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Random;

public class Clorus extends Creature
{
    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    public static final double MOVE_POINT = 0.03;
    public static final double STAY_POINT = 0.01;

    public Clorus()
    {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
    }

    public Clorus(double e)
    {
        this();
        energy = e;
    }

    @Override
    public void move()
    {
        energy -= MOVE_POINT;
        if(energy < 0)
            energy = 0;
    }

    @Override
    public void attack(Creature c)
    {
        energy += c.energy();
    }

    @Override
    public Creature replicate()
    {
        energy /= 2;
        return new Clorus(energy);
    }

    @Override
    public void stay()
    {
        energy -= STAY_POINT;
        if(energy < 0)
            energy = 0;
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors)
    {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        boolean anyPlips = false;
        boolean hasEmpty = false;

        // TODO
        // (Google: Enhanced for-loop over keys of NEIGHBORS?)
        for (Direction key : neighbors.keySet())
        {
            if(neighbors.get(key).name().equals("empty"))
            {
                emptyNeighbors.add(key);
                hasEmpty = true;
            }
            else if(neighbors.get(key).name().equals("plip"))
            {
                plipNeighbors.add(key);
                anyPlips = true;
            }
        }

        if (!hasEmpty) { // FIXME
            // TODO
            stay();
            return new Action(Action.ActionType.STAY);
        }

        // Rule 2
        else if(anyPlips)
        {
            Direction finalDirection = pickRandom(plipNeighbors);
            attack((Creature) neighbors.get(finalDirection));
            return new Action(Action.ActionType.ATTACK, finalDirection);
        }

        // Rule 3
        else if(energy >= 1)
        {
            return new Action(Action.ActionType.REPLICATE, pickRandom(emptyNeighbors));
        }

        // Rule 4
        Direction finalDirection = pickRandom(emptyNeighbors);
        move();
        return new Action(Action.ActionType.MOVE, finalDirection);
    }

    public static Direction pickRandom (Deque<Direction> deque)
    {
        int index = (int)(Math.random() * deque.size());
        int count = 0;
        Direction finalDirection = deque.getFirst();
        for (Direction dir : deque)
        {
            if(count == index) {
                finalDirection = dir;
                break;
            }
            count++;
        }
        return finalDirection;
    }


    @Override
    public Color color()
    {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }
}
