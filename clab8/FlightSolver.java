import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
//找到同一时间在空中的最多人数
public class FlightSolver {
    private ArrayList<Flight> fights;
    PriorityQueue<Flight> startTimePQ;      //开始时间从小到大
    PriorityQueue<Flight> endTimePQ;        //结束时间从小到大

    private Comparator<Flight> byEndTime = Comparator.comparing((Flight f) -> (f.endTime));
    private Comparator<Flight> byStartTime = Comparator.comparing((Flight f) -> (f.startTime));


    public FlightSolver(ArrayList<Flight> flights) {
        /* FIX ME */
        this.fights = flights;
        startTimePQ = new PriorityQueue<>(flights.size(), byStartTime);
        endTimePQ = new PriorityQueue<>(flights.size(), byEndTime);

        //一开始只是初始化按照开始时间的最小堆
        for(int i = 0; i < flights.size(); i++)
        {
            startTimePQ.add(flights.get(i));
        }
    }

    public int solve() {
        /* FIX ME */
        int maxNumbers = 0;
        int currNumbers = 0;
        Flight currFlight;
        while(!startTimePQ.isEmpty())
        {
            currFlight = startTimePQ.poll();
            endTimePQ.add(currFlight);
            currNumbers += currFlight.passengers;

            if(!endTimePQ.isEmpty())
            {
                while (!endTimePQ.isEmpty() && (endTimePQ.peek().endTime < currFlight.startTime))
                {
                    currNumbers -= endTimePQ.poll().passengers;
                }
            }
            maxNumbers = Math.max(maxNumbers, currNumbers);
        }
        return maxNumbers;
    }


}
