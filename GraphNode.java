import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphNode {
    public int val;
    public List<GraphNode> neighbors;
    public GraphNode() {
        val = 0;
        neighbors = new ArrayList<GraphNode>();
    }
    public GraphNode(int _val) {
        val = _val;
        neighbors = new ArrayList<GraphNode>();
    }
    public GraphNode(int _val, ArrayList<GraphNode> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

    /*public GraphNode cloneGraph(GraphNode node) {
        if (node == null) return null;

        if (node.neighbors.size() == 0) {
            return new GraphNode(node.val);
        }

        boolean[] visited = new boolean[101]; // default all false
        Queue<GraphNode> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            GraphNode cur = queue.poll();
            visited[cur.val] = true;
            ArrayList<GraphNode> adj = new ArrayList<>();
            GraphNode newNode = new GraphNode(cur.val, adj);
            for (GraphNode neighbour: cur.neighbors) {
                if (visited[neighbour.val]) continue;
                queue.add(neighbour);
            }
        }
    }*/
