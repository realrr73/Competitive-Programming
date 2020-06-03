import java.util.*;

public class Flows {
	
	static class FlowEdge {
		final int v, w;
		final double capacity;
		double flow;

		FlowEdge(int v, int w, double capacity) {
			this.v = v;
			this.w = w;
			this.capacity = capacity;
		}

		int from() {
			return v;
		}

		int to() {
			return w;
		}

		int other(int v) {
			return (v == this.v) ? w : this.v;
		}

		double capacity() {
			return capacity;
		}

		double flow() {
			return flow;
		}

		double residualCapacityTo(int v) {
			if (v == this.w)
				return capacity - flow;
			else
				return flow;
		}

		void addResidualFlowTo(int v, double delta) {
			if (v == this.w)
				flow += delta;
			else
				flow -= delta;
		}

		@Override
		public String toString() {
			return "[" + v + ", " + w + " (" + capacity + ")]";
		}
	}

	static class FlowNetwork {
		final int V;
		int E;
		ArrayList<ArrayList<FlowEdge>> graph;

		FlowNetwork(int V) {
			this.V = V;
			graph = new ArrayList<>(V);
			for (int i = 0; i < V; ++i)
				graph.add(new ArrayList<>());
		}

		void addEdge(FlowEdge e) {
			int v = e.from();
			int w = e.to();
			graph.get(v).add(e);
			graph.get(w).add(e);
			E++;
		}

		Iterable<FlowEdge> adj(int v) {
			return graph.get(v);
		}

		Iterable<FlowEdge> edges() {
			ArrayList<FlowEdge> al = new ArrayList<>(V);
			for (int i = 0; i < V; ++i) {
				for (FlowEdge fe : graph.get(i))
					al.add(fe);
			}
			return al;
		}

		int V() {
			return V;
		};

		int E() {
			return E;
		};
	}

	static class FordFulkerson {
		
		private boolean[] marked;
		private FlowEdge[] adj;
		private double value;

		public FordFulkerson(FlowNetwork G, int s, int t) {
			value = 0;
			while (hasAugmentingPath(G, s, t)) {
				double bottle = Double.POSITIVE_INFINITY;

				for (int v = t; v != s; v = adj[v].other(v)) {
					bottle = Math.min(bottle, adj[v].residualCapacityTo(v));
				}
				for (int v = t; v != s; v = adj[v].other(v))
					adj[v].addResidualFlowTo(v, bottle);

				value += bottle;
			}
		}

		public final boolean hasAugmentingPath(FlowNetwork G, int s, int t) {
			adj = new FlowEdge[G.V()];
			marked = new boolean[G.V()];

			Queue<Integer> q = new LinkedList<>();
			q.add(s);
			marked[s] = true;
			while (!q.isEmpty()) {

				int v = q.poll();
				for (FlowEdge e : G.adj(v)) {
					int w = e.other(v);
					if (e.residualCapacityTo(w) > 0 && !marked[w]) {
						adj[w] = e;

						marked[w] = true;
						q.add(w);
					}
				}
			}
			return marked[t];
		}

		public double value() {
			return value;
		}

		public boolean inCut(int v) {
			return marked[v];
		}

	}
}
