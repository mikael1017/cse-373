package seamcarving;

import edu.princeton.cs.algs4.Picture;
import graphs.BaseEdge;
import graphs.Edge;
import graphs.Graph;
import graphs.shortestpaths.DijkstraShortestPathFinder;
import graphs.shortestpaths.ShortestPathFinder;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DijkstraSeamFinder implements SeamFinder {

    final class Coordinate {
        private int x;
        private int y;

        private Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }
    }

    /*
    private class MyGraph implements Graph<Coordinate, Edge<Coordinate>> {
        @Override
        public Collection<Edge<Coordinate>> outgoingEdgesFrom(Coordinate vertex, boolean vertical) {
            Collection<Edge<Coordinate>> res = new ArrayList<>();
            if (vertical) {
                for (int i = -1; i <= 1; i++) {
                    Coordinate neighbor = new Coordinate(vertex.getX(), vertex.getY() + i);
                    Edge<Coordinate> edge = new Edge(vertex, neighbor, );
                    res.add(neighbor);
                }
            } else {
                for (int i = -1; i <= 1; i++) {
                    Coordinate neighbor = new Coordinate(vertex.getX() + i, vertex.getY());
                    res.add(neighbor);
                }
            }

        }
    }
    */

    private final ShortestPathFinder<Graph<Coordinate, Edge<Coordinate>>, Coordinate, Edge<Coordinate>> pathFinder;

    public DijkstraSeamFinder() {
        this.pathFinder = createPathFinder();
    }

    protected <G extends Graph<V, Edge<V>>, V> ShortestPathFinder<G, V, Edge<V>> createPathFinder() {
        /*
        We override this during grading to test your code using our correct implementation so that
        you don't lose extra points if your implementation is buggy.
        */
        return new DijkstraShortestPathFinder<>();
    }

    /**
     * We need to choose starting vertex and end vertex
     * Edges have weight representing energy
     *
     * Edges exist from a pixel to its 3 downward neighbors
     *


     */

    @Override
    public List<Integer> findHorizontalSeam(double[][] energies) {
        double min = energies[0][0];
        int minIndex = 0;
        List<Integer> res = new ArrayList<>();

        for (int y = 0; y < energies[0].length; y++) {
            if (energies[0][y] < min) {
                min = energies[0][y];
                minIndex = y;
            }
        }
        res.add(minIndex);

        int x = 1;
        int y = 0;
        min = 1000;
        while (x < energies.length) {
            if (minIndex > 0 && minIndex + 1 < energies[x].length) {
                int y1 = minIndex - 1;
                int y2 = minIndex;
                int y3 = minIndex + 1;
                double e1 = energies[x][y1];
                double e2 = energies[x][y2];
                double e3 = energies[x][y3];
                if (e1 <= e2 && e1 <= e3) {
                    minIndex = y1;
                } else if (e2 <= e3 && e2 <= e1) {
                    minIndex = y2;
                } else {
                    minIndex = y3;
                }
            } else if (minIndex - 1 < 0) {
                int y1 = minIndex;
                int y2 = minIndex + 1;
                double e1 = energies[x][y1];
                double e2 = energies[x][y2];
                if (e1 <= e2) {
                    minIndex = y1;
                } else {
                    minIndex = y2;
                }
            } else if (minIndex + 1 == energies[x].length) {
                int y1 = minIndex - 1;
                int y2 = minIndex;
                double e1 = energies[x][y1];
                double e2 = energies[x][y2];
                if (e1 <= e2) {
                    minIndex = y1;
                } else {
                    minIndex = y2;
                }
            }
            min = 1000;
            res.add(minIndex);
            x++;
        }
        return res;
    }

    @Override
    public List<Integer> findVerticalSeam(double[][] energies) {
        double min = energies[0][0];
        int minIndex = 0;
        List<Integer> res = new ArrayList<>();

        for (int x = 0; x < energies.length; x++) {
            if (energies[x][0] < min) {
                min = energies[x][0];
                minIndex = x;
            }
        }
        res.add(minIndex);

        int x = 0;
        int y = 1;
        min = 1000;
        while (y < energies[x].length) {
            if (minIndex > 0 && (minIndex + 1 < energies.length)) {
                int x1 = minIndex - 1;
                int x2 = minIndex;
                int x3 = minIndex + 1;
                double e1 = energies[x1][y];
                double e2 = energies[x2][y];
                double e3 = energies[x3][y];
                if (e1 <= e2 && e1 <= e3) {
                    minIndex = x1;
                } else if (e2 <= e3 && e2 <= e1) {
                    minIndex = x2;
                } else {
                    minIndex = x3;
                }
            } else if (minIndex - 1 < 0) {
                int x1 = minIndex;
                int x2 = minIndex + 1;
                double e1 = energies[x1][y];
                double e2 = energies[x2][y];
                if (e1 <= e2) {
                    minIndex = x1;
                } else {
                    minIndex = x2;
                }
            } else if (minIndex + 1 == energies.length) {
                int x1 = minIndex - 1;
                int x2 = minIndex;
                double e1 = energies[x1][y];
                double e2 = energies[x2][y];
                if (e1 <= e2) {
                    minIndex = x1;
                } else {
                    minIndex = x2;
                }
            }
            min = 1000;
            res.add(minIndex);
            y++;
        }
        return res;
    }



}


