package creatures;

import huglife.*;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestClorus
{
    @Test
    public void testBasics()
    {
        Clorus p = new Clorus(2);
        assertEquals(2, p.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), p.color());
        p.move();
        assertEquals(1.85, p.energy(), 0.01);
        p.move();
        assertEquals(1.70, p.energy(), 0.01);
        p.stay();
        assertEquals(1.90, p.energy(), 0.01);
        p.stay();
        assertEquals(2.00, p.energy(), 0.01);
    }

    @Test
    public void testChoose()
    {
        // No empty adjacent spaces; stay.
        Clorus p = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded1 = new HashMap<Direction, Occupant>();
        surrounded1.put(Direction.TOP, new Impassible());
        surrounded1.put(Direction.BOTTOM, new Impassible());
        surrounded1.put(Direction.LEFT, new Impassible());
        surrounded1.put(Direction.RIGHT, new Impassible());

        Action actual = p.chooseAction(surrounded1);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        // Even plip is stayed around, Clorus will stay
        p = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded2 = new HashMap<Direction, Occupant>();
        surrounded2.put(Direction.TOP, new Plip());
        surrounded2.put(Direction.BOTTOM, new Impassible());
        surrounded2.put(Direction.LEFT, new Plip());
        surrounded2.put(Direction.RIGHT, new Impassible());

        actual = p.chooseAction(surrounded2);
        expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        //Attck randomly
        int count = 1000;
        while((count--) >= 0) {
            p = new Clorus(1.3);
            HashMap<Direction, Occupant> surrounded3 = new HashMap<Direction, Occupant>();
            surrounded3.put(Direction.TOP, new Plip(1));
            surrounded3.put(Direction.BOTTOM, new Empty());
            surrounded3.put(Direction.LEFT, new Plip(1.2));
            surrounded3.put(Direction.RIGHT, new Empty());

            actual = p.chooseAction(surrounded3);

            assertEquals(Action.ActionType.ATTACK, actual.type);
            if(actual.dir.equals(Direction.TOP)) {
                assertEquals(2.3, p.energy(), 0.01);
            }
            else if(actual.dir.equals(Direction.LEFT)){
                assertEquals(2.5, p.energy(), 0.01);
            }
        }

        // Replicate
        p = new Clorus(1.2);
        HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Impassible());
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());

        actual = p.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);
        assertEquals(expected, actual);

        // Energy less than 1.0, Clorus will stay
        p = new Clorus(0.2);
        actual = p.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.MOVE, Direction.TOP);
        assertEquals(expected, actual);


        HashMap<Direction, Occupant> allEmpty = new HashMap<Direction, Occupant>();
        allEmpty.put(Direction.TOP, new Empty());
        allEmpty.put(Direction.BOTTOM, new Empty());
        allEmpty.put(Direction.LEFT, new Empty());
        allEmpty.put(Direction.RIGHT, new Empty());
        p = new Clorus(0.99);
        actual = p.chooseAction(topEmpty);
        assertEquals(Action.ActionType.MOVE, actual.type);



        System.out.println("Passed the chooseAction Test!");
    }
}
