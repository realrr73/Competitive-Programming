import java.util.*;

public class Graph {
	int V;
	LinkedList<Integer> adj[];
	boolean[] visited;

	public Graph(int vertices) {
		V = vertices;
		this.V = vertices;
		visited = new boolean[V];
		adj = new LinkedList[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new LinkedList<>();
		}
	}

	void addEdge(int u, int v) {
		u--; v--;
		adj[u].add(v);
		adj[v].add(u);		
	}

	void dfs(int u) {
		visited[u] = true;

		Iterator<Integer> i = adj[u].listIterator();

		while (i.hasNext()) {
			int v = i.next();
			if (!visited[v]) {
				dfs(v);
			}
		}
	}

	void dfs2(int u) {
		Stack<Integer> stack = new Stack<>();

		stack.push(u);

		while (stack.empty() == false) {
			u = stack.peek();
			stack.pop();

			if (visited[u] == false) {
				visited[u] = true;
			}

			Iterator<Integer> itr = adj[u].iterator();

			while (itr.hasNext()) {
				int v = itr.next();
				if (!visited[v]) {
					stack.push(v);
				}
			}

		}
	}

	void bfs(int u) {
		LinkedList<Integer> queue = new LinkedList<Integer>();

		visited[u] = true;

		queue.add(u);

		while (queue.size() != 0) {
			u = queue.poll();
			Iterator<Integer> i = adj[u].listIterator();
			while (i.hasNext()) {
				int v = i.next();
				if (!visited[v]) {
					visited[v] = true;
					queue.add(v);
				}
			}
		}
	}

}
