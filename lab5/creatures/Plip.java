package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.*;

/**
 * An implementation of a motile pacifist photosynthesizer.
 *
 * @author Josh Hug
 */
public class Plip extends Creature {

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

    /**
     * Magic Number added
     */
    public static final double MOVE_POINT = 0.15;
    public static final double STAY_POINT = 0.20;
    public static final double MAX_POINT = 2;

    /**
     * creates plip with energy equal to E.
     */
    public Plip(double e) {
        super("plip");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /**
     * creates a plip with energy equal to 1.
     */
    public Plip() {
        this(1);
    }

    /**
     * Should return a color with red = 99, blue = 76, and green that varies
     * linearly based on the energy of the Plip. If the plip has zero energy,
     * it should have a green value of 63. If it has max energy, it should
     * have a green value of 255. The green value should vary with energy
     * linearly in between these two extremes. It's not absolutely vital
     * that you get this exactly correct.
     */
    public Color color() {
        //g = 63;
        r = 99;
        g = (int) (energy * 96) + 63;
        b = 76;
        return color(r, g, b);
    }

    /**
     * Do nothing with C, Plips are pacifists.
     */
    public void attack(Creature c) {
        // do nothing.
    }

    /**
     * Plips should lose 0.15 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    public void move() {
        // TODO
        if(energy >= MOVE_POINT)
            energy -= MOVE_POINT;
    }


    /**
     * Plips gain 0.2 energy when staying due to photosynthesis.
     */
    public void stay() {
        // TODO
        energy += STAY_POINT;
        if(energy > MAX_POINT)
            energy = MAX_POINT;
    }

    /**
     * Plips and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Plip.
     */
    public Plip replicate() {
        energy /= 2;
        return new Plip(energy);
    }

    /**
     * Plips take exactly the following actions based on NEIGHBORS:
     * 1. If no empty adjacent spaces, STAY.
     * 2. Otherwise, if energy >= 1, REPLICATE towards an empty direction
     * chosen at random.
     * 3. Otherwise, if any Cloruses, MOVE with 50% probability,
     * towards an empty direction chosen at random.
     * 4. Otherwise, if nothing else, STAY
     * <p>
     * Returns an object of type Action. See Action.java for the
     * scoop on how Actions work. See SampleCreature.chooseAction()
     * for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        boolean anyClorus = false;
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
            else if(neighbors.get(key).name().equals("clorus"))
            {
                anyClorus = true;
            }
        }

        if (!hasEmpty) { // FIXME
            // TODO
            stay();
            return new Action(Action.ActionType.STAY);
        }

        // Rule 2
        // HINT: randomEntry(emptyNeighbors)
        else if(energy >= 1)
        {
            int index = (int)(Math.random() * emptyNeighbors.size());
            int count = 0;
            Direction finalDirection = emptyNeighbors.getFirst();
            for (Direction dir : emptyNeighbors)
            {
                if(count == index) {
                    finalDirection = dir;
                    break;
                }
                count++;
            }
            replicate();
            return new Action(Action.ActionType.REPLICATE, finalDirection);
        }
        // Rule 3
        else if(anyClorus)
        {
            int index = (int)(Math.random() * emptyNeighbors.size());
            int count = 0;
            Direction finalDirection = emptyNeighbors.getFirst();
            for (Direction dir : emptyNeighbors)
            {
                if(count == index) {
                    finalDirection = dir;
                    break;
                }
                count++;
            }
            int runOrStay = (int)(Math.random() * 2);
            if(runOrStay == 0) {
                move();
                return new Action(Action.ActionType.MOVE, finalDirection);
            }
            else {
                stay();
                return new Action(Action.ActionType.STAY);
            }
        }


        // Rule 4
        stay();
        return new Action(Action.ActionType.STAY);
    }
}
