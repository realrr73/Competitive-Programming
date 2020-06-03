import java.util.*;

public class WeightedGraph {
	static class Edge {
		int source;
		int destination;
		int weight;

		public Edge(int u, int v, int w) {
			this.u = source;
			this.v = destination;
			this.w = weight;
		}
	}

	static class Graph {
		int V;
		LinkedList<Edge> adj[];

		Graph(int vertices) {
			this.V = vertices;
			adj = new LinkedList[V];
			for (int i = 0; i < vertices; i++) {
				adj[i] = new LinkedList<>();
			}
		}

		public void addEdge(int u, int v, int w, boolean bidirectional) {
			Edge edge = new Edge(u, v, w);
			adj[u].addFirst(edge);
			if (bidirectional) {
				edge = new Edge(v, u, w);
				adj[v].addFirst(edge);
			}
		}
	}
}
