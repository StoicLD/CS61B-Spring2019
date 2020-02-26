import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.Queue;

public class SeparableEnemySolver {

    Graph g;

    //0表示未标记，1表示第一组，2表示第二组
    private HashMap<String, Integer> marked;
    /**
     * Creates a SeparableEnemySolver for a file with name filename. Enemy
     * relationships are biderectional (if A is an enemy of B, B is an enemy of A).
     */
    SeparableEnemySolver(String filename) throws java.io.FileNotFoundException {
        this.g = graphFromFile(filename);
        marked = new HashMap<>();
    }

    /** Alterntive constructor that requires a Graph object. */
    SeparableEnemySolver(Graph g) {
        this.g = g;
        marked = new HashMap<>();
    }

    /**
     * Returns true if input is separable, false otherwise.
     */
    public boolean isSeparable() {
        // TODO: Fix me
        Set<String> set = g.labels();
        Queue<String> bfsQueue = new Queue<>();

        //每次有效循环是遍历一个连通图
        for(String label : set)
        {
            if(marked.get(label) != null)
                continue;

            //root 节点入队，设置为group1
            bfsQueue.enqueue(label);
            marked.put(label, 1);
            String currLabel;
            while(!bfsQueue.isEmpty())
            {
                currLabel = bfsQueue.dequeue();
                int currGroupNum = marked.get(currLabel);
                for (String neighbor : g.neighbors(currLabel))
                {
                    Integer markValue = marked.get(neighbor);
                    if (markValue == null)
                    {
                        marked.put(neighbor, -currGroupNum);
                        bfsQueue.enqueue(neighbor);
                    }
                    else if (markValue == currGroupNum)
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /* HELPERS FOR READING IN CSV FILES. */

    /**
     * Creates graph from filename. File should be comma-separated. The first line
     * contains comma-separated names of all people. Subsequent lines each have two
     * comma-separated names of enemy pairs.
     */
    private Graph graphFromFile(String filename) throws FileNotFoundException {
        List<List<String>> lines = readCSV(filename);
        Graph input = new Graph();
        for (int i = 0; i < lines.size(); i++) {
            //第一行是所有人的名字，所以先全部加进去
            if (i == 0) {
                for (String name : lines.get(i)) {
                    input.addNode(name);
                }
                continue;
            }
            //保证除了第一行以外每行的格式是  xx, xx 这样（两个人的姓名）
            assert(lines.get(i).size() == 2);
            input.connect(lines.get(i).get(0), lines.get(i).get(1));
        }
        return input;
    }

    /**
     * Reads an entire CSV and returns a List of Lists. Each inner
     * List represents a line of the CSV with each comma-seperated
     * value as an entry. Assumes CSV file does not contain commas
     * except as separators.
     * Returns null if invalid filename.
     *
     * @source https://www.baeldung.com/java-csv-file-array
     */
    private List<List<String>> readCSV(String filename) throws java.io.FileNotFoundException {
        List<List<String>> records = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filename));
        while (scanner.hasNextLine()) {
            records.add(getRecordFromLine(scanner.nextLine()));
        }
        return records;
    }

    /**
     * Reads one line of a CSV.
     *
     * @source https://www.baeldung.com/java-csv-file-array
     */
    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        Scanner rowScanner = new Scanner(line);
        rowScanner.useDelimiter(",");
        while (rowScanner.hasNext()) {
            values.add(rowScanner.next().trim());
        }
        return values;
    }

    /* END HELPERS  FOR READING IN CSV FILES. */

}
