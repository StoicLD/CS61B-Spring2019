package bearmaps.hw4;

import bearmaps.proj2ab.DoubleMapPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.awt.font.TextMeasurer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex>
{
    private SolverOutcome solverOutcome;
    private List<Vertex> solution;
    private double solutionWeight;
    private int numStatesExplored;
    private double explorationTime;

    /**
     * Constructor which finds the solution,
     * computing everything necessary for all other methods to return their results in constant time.
     * Note that timeout passed in is in seconds.
     *
     * @param input   输入的图
     * @param start   开始的vertex
     * @param end     结束的vertex
     * @param timeout 以秒为单位的超时时间
     */
    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout)
    {
        //直接在构造函数解决全部的问题
        Stopwatch stopwatch = new Stopwatch();
        double startTime = stopwatch.elapsedTime();
        DoubleMapPQ<Vertex> fringe = new DoubleMapPQ<>();
        //记录当前的距离的
        HashMap<Vertex, Double> distTo = new HashMap<>();
        distTo.put(start, 0.0);
        //记录访问顺序
        HashMap<Vertex, Vertex> sourceFrom = new HashMap<>();

        fringe.add(start, 0);
        double endTime = stopwatch.elapsedTime();
        solverOutcome = SolverOutcome.UNSOLVABLE;
        numStatesExplored = 0;
        solution = new LinkedList<>();
        while (fringe.size() != 0)
        {
            //判断是否超时
            if ((endTime - startTime) > timeout)
            {
                solution = null;
                solutionWeight = 0;
                solverOutcome = SolverOutcome.TIMEOUT;
                break;
            }
            Vertex currVertex = fringe.removeSmallest();
            numStatesExplored++;

            //如果找到终点了
            if (currVertex.equals(end))
            {
                solutionWeight = distTo.get(currVertex);
                solverOutcome = SolverOutcome.SOLVED;
                Vertex pathVertex = currVertex;
                Stack<Vertex> tempStack = new Stack<>();
                while (sourceFrom.get(pathVertex) != null)
                {
                    tempStack.push(pathVertex);
                    pathVertex = sourceFrom.get(pathVertex);
                }
                tempStack.push(pathVertex);
                while(!tempStack.empty())
                {
                    solution.add(tempStack.pop());
                }
                break;
            }

            //正常寻找过程
            List<WeightedEdge<Vertex>> neighbors = input.neighbors(currVertex);
            for (WeightedEdge<Vertex> weightedEdge : neighbors)
            {
                Vertex to = weightedEdge.to();
                double currDis = distTo.get(currVertex) + weightedEdge.weight();

                //因为采用的是map，所以如果没访问过
                if(distTo.get(to) == null)
                {
                    fringe.add(to, currDis + input.estimatedDistanceToGoal(to, end));
                    distTo.put(to, currDis);
                    sourceFrom.put(to, currVertex);
                }
                else if (distTo.get(to) > currDis)
                {
                    //已经访问过了，但是要更新
                    if (fringe.contains(to))
                    {
                        //改队列中的优先级
                        fringe.changePriority(to, distTo.get(to) + input.estimatedDistanceToGoal(to, end));
                    }
                    else
                    {
                        //已经访问过且拿出队列过，但是现在重新访问的时候发现距离更短了，需要重新加进去
                        fringe.add(to, currDis + input.estimatedDistanceToGoal(to, end));
                    }
                    //修改距离
                    distTo.put(to, currDis);
                    sourceFrom.put(to, currVertex);
                }
            }
            endTime = stopwatch.elapsedTime();
        }

        explorationTime = stopwatch.elapsedTime() - startTime;
    }

    /**
     * result: Returns one of SolverOutcome.SOLVED, SolverOutcome.TIMEOUT, or SolverOutcome.UNSOLVABLE.
     * Should be SOLVED if the AStarSolver was able to complete all work in the time given.
     * UNSOLVABLE if the priority queue became empty. TIMEOUT if the solver ran out of time.
     * You should check to see if you have run out of time every time you dequeue.
     *
     * @return SolverOutcome
     */
    public SolverOutcome outcome()
    {
        return solverOutcome;
    }

    /**
     * solution: A list of vertices corresponding to a solution.
     * Should be empty if result was TIMEOUT or UNSOLVABLE
     *
     * @return list of vertexes
     */
    public List<Vertex> solution()
    {
        return solution;
    }

    /**
     * solutionWeight: The total weight of the given solution,
     * taking into account edge weights. Should be 0 if result was TIMEOUT or UNSOLVABLE.
     *
     * @return total weight
     */
    public double solutionWeight()
    {
        return solutionWeight;
    }

    /**
     * numStatesExplored: The total number of priority queue dequeue operations.
     *
     * @return dequeue numbers
     */
    public int numStatesExplored()
    {
        return numStatesExplored;
    }

    /**
     * explorationTime: The total time spent in seconds by the constructor.
     *
     * @return total time
     */
    public double explorationTime()
    {
        return explorationTime;
    }
}