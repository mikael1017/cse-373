package graphs.shortestpaths;

import graphs.BaseEdge;
import graphs.Graph;
import priorityqueues.DoubleMapMinPQ;
import priorityqueues.ExtrinsicMinPQ;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * Computes shortest paths using Dijkstra's algorithm.
 * @see ShortestPathFinder for more documentation.
 */
public class DijkstraShortestPathFinder<G extends Graph<V, E>, V, E extends BaseEdge<V, E>>
    implements ShortestPathFinder<G, V, E> {

    protected <T> ExtrinsicMinPQ<T> createMinPQ() {
        return new DoubleMapMinPQ<>();
        /*
        If you have confidence in your heap implementation, you can disable the line above
        and enable the one below.
        You'll also need to change the part of the class declaration that says
        `ArrayHeapMinPQ<T extends Comparable<T>>` to `ArrayHeapMinPQ<T>`.
         */
        // return new ArrayHeapMinPQ<>();

        /*
        Otherwise, do not change this method.
        We override this during grading to test your code using our correct implementation so that
        you don't lose extra points if your implementation is buggy.
         */
    }


    /**
     *  Instead of initializing values for all vertices at the beginning of the algorithm, we’ll initialize values for
     * only the starting vertex. This necessitates that we also change our relaxation operation to match,
     * to initialize values for new vertices as they are encountered.
     *  Additionally, since the maze application has a particular destination in mind, our algorithm does not need to
     * compute the full SPT; we can stop as soon as we have found the shortest path from the starting vertex to the destination.
     */
    @Override
    public ShortestPath<V, E> findShortestPath(G graph, V start, V end) {
        if (Objects.equals(start, end)) {
            return new ShortestPath.SingleVertex<>(start);
        }

        ExtrinsicMinPQ<V> minPQ = createMinPQ();
        HashMap<V, Double> distMap = new HashMap<>();
        HashSet<V> visited = new HashSet<>();
        HashMap<V, E> edgeMap = new HashMap<>();

        minPQ.add(start, 0.0);
        distMap.put(start, 0.0);

        while (!minPQ.isEmpty()) {
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
                }
            }
            visited.add(fromVertex);
        }

        return new ShortestPath.Success<>(edgeMap.values());
    }

    //  graph.outgoingEdgesFrom(V vertex) - returns an unmodifiable collection of the outgoing edges from the given vertex


}
