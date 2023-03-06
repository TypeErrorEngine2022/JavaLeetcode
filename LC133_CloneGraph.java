import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC133_CloneGraph {
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        boolean[] visited = new boolean[101];
        Graph graph = new Graph();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (visited[cur.val]) continue;
            visited[cur.val] = true;
            graph.addVertex(cur.val);
            for (Node adj: cur.neighbors) {
                graph.addEdge(cur.val, adj.val);
                queue.add(adj);
            }
        }
        graph.connect();
        return graph.getFirstNode();
    }


    protected static class Graph {
        private final Node[] nodeTable;
        private final boolean[][] edge;

        public Graph(){
            nodeTable = new Node[101];
            edge = new boolean[101][101];
        }

        public Node getFirstNode() {
            return nodeTable[1];
        }

        public void addVertex(int val) {
            nodeTable[val] = new Node(val);
        }

        public void addEdge(int a, int b) {
            if (a < 0 || a > 100 || b < 0 || b > 100)
                return;

            edge[a][b] = edge[b][a] = true;
        }

        public void connect() {
            for (int i = 0; i < 101; i++) {
                for (int j = 0; j < 101; j++) {
                    if (edge[i][j]) {
                        nodeTable[i].neighbors.add(nodeTable[j]);
                        nodeTable[j].neighbors.add(nodeTable[i]);
                        edge[j][i] = false;
                    }
                }
            }
        }
    }

    public Node cloneGraphDFS(Node node) {
        if (node == null) return null;
        Node[] visited = new Node[101];
        Node copy = new Node(node.val);
        return dfs(node, copy, visited);
    }

    private Node dfs(Node srcNode, Node copy, Node[] visited) {
        visited[copy.val] = copy;
        for (Node neighbours: srcNode.neighbors) {
            if (visited[neighbours.val] != null) {
                copy.neighbors.add(visited[neighbours.val]);
                continue;
            }
            Node newNode = new Node(neighbours.val);
            copy.neighbors.add(newNode);
            dfs(neighbours, newNode, visited);
        }
        return copy;
    }
}
