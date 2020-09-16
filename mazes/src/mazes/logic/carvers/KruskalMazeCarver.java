package mazes.logic.carvers;

import graphs.EdgeWithData;
import graphs.minspantrees.MinimumSpanningTreeFinder;
import mazes.entities.Room;
import mazes.entities.Wall;
import mazes.logic.MazeGraph;


import java.util.Collection;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

/**
 * Carves out a maze based on Kruskal's algorithm.
 */
public class KruskalMazeCarver extends MazeCarver {
    MinimumSpanningTreeFinder<MazeGraph, Room, EdgeWithData<Room, Wall>> minimumSpanningTreeFinder;
    private final Random rand;

    public KruskalMazeCarver(MinimumSpanningTreeFinder
                                 <MazeGraph, Room, EdgeWithData<Room, Wall>> minimumSpanningTreeFinder) {
        this.minimumSpanningTreeFinder = minimumSpanningTreeFinder;
        this.rand = new Random();
    }

    public KruskalMazeCarver(MinimumSpanningTreeFinder
                                 <MazeGraph, Room, EdgeWithData<Room, Wall>> minimumSpanningTreeFinder,
                             long seed) {
        this.minimumSpanningTreeFinder = minimumSpanningTreeFinder;
        this.rand = new Random(seed);
    }

    @Override
    protected Set<Wall> chooseWallsToRemove(Set<Wall> walls) {
        // Hint: you'll probably need to include something like the following:
        // this.minimumSpanningTreeFinder.findMinimumSpanningTree(new MazeGraph(edges));
        ArrayList<EdgeWithData<Room, Wall>> edges = new ArrayList<>();
        for (Wall w : walls) {
            edges.add(new EdgeWithData<Room, Wall>(w.getRoom1(), w.getRoom2(), rand.nextDouble(), w));
        }
        MazeGraph graph = new MazeGraph(edges);
        Collection<EdgeWithData<Room, Wall>> result = this.minimumSpanningTreeFinder.
            findMinimumSpanningTree(new MazeGraph(edges)).edges();
        Set<Wall> beRemoved = new HashSet<>();
        for (EdgeWithData<Room, Wall> edge : result) {
            beRemoved.add(edge.data());
        }
        return beRemoved;
    }
}
