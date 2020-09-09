package graphs.shortestpaths;

import graphs.BaseEdge;
import graphs.Graph;
import priorityqueues.DoubleMapMinPQ;
import priorityqueues.ExtrinsicMinPQ;

import java.util.*;

/**
 * Computes shortest paths using Dijkstra's algorithm.
 * @see ShortestPathFinder for more documentation.
 */
public class DijkstraShortestPathFinder<G extends Graph<V, E>, V, E extends BaseEdge<V, E>>
    implements ShortestPathFinder<G, V, E> {

    protected <T> ExtrinsicMinPQ<T> createMinPQ() {
        return new DoubleMapMinPQ<>();

    }

    @Override
    public ShortestPath<V, E> findShortestPath(G graph, V start, V end) {

        if (graph.outgoingEdgesFrom(start).isEmpty()) {
            return new ShortestPath.Failure<>();
        }
        if (Objects.equals(start, end)) {
            return new ShortestPath.SingleVertex<>(start);
        }

        ExtrinsicMinPQ<V> minPQ = createMinPQ();
        HashMap<V, Double> distMap = new HashMap<>();
        HashSet<V> visited = new HashSet<>();
        HashMap<V, E> edgeMap = new HashMap<>();

        minPQ.add(start, 0.0);
        distMap.put(start, 0.0);

        while (!minPQ.isEmpty() && !edgeMap.containsKey(end)) {
            V fromVertex = minPQ.removeMin();
            for (E edge : graph.outgoingEdgesFrom(fromVertex)) {
                V toVertex = edge.to();
                if (!visited.contains(toVertex)) {
                    double newDistance = edge.weight() + distMap.get(fromVertex);
                    if (distMap.getOrDefault(toVertex, Double.POSITIVE_INFINITY) > newDistance) {
                        distMap.put(toVertex, newDistance);
                        edgeMap.put(toVertex, edge);
                        if (minPQ.contains(toVertex)) {
                            minPQ.changePriority(toVertex, newDistance);
                        } else {
                            minPQ.add(toVertex, newDistance);
                        }
                    }
                    visited.add(fromVertex);
                }
                if (toVertex.equals(end)) {
                    visited.add(toVertex);
                    break;
                }

            }
        }
        Iterator<V> iterator = edgeMap.keySet().iterator();
        while (iterator.hasNext()) {
            V vertex = iterator.next();
            if (!visited.contains(vertex)) {
                iterator.remove();
            }
        }
        List<E> edgeList = new ArrayList<E>(edgeMap.values());

        return new ShortestPath.Success<>(edgeList);
    }
    //  graph.outgoingEdgesFrom(V vertex) - returns an unmodifiable collection of the outgoing edges from the given vertex

}
