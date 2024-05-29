import com.mathsystem.api.graph.model.Graph;

import java.util.*;

import static java.lang.Math.min;

public class PlanarityTester {
    private Map<UUID, Integer> height;
    private Map<UUID, UUID> parent;
    private Map<UUID, Set<UUID>> dirAdjacencyList;
    private Map<Pair<UUID, UUID>, Integer> lowpt;
    private Map<Pair<UUID, UUID>, Integer> lowpt2;
    private Map<Pair<UUID, UUID>, Integer> nestingDepth;
    private Map<Pair<UUID, UUID>, Pair<UUID, UUID>> ref;
    private Map<Pair<UUID, UUID>, Pair<UUID, UUID>> lowptEdge;
    private Map<Pair<UUID, UUID>, ConflictPair> stackBottom;
    private List<ConflictPair> stack;

    private Map<UUID, Set<UUID>> adjacencyList;
    private List<UUID> vertices;
    private Integer numEdges;

    PlanarityTester(List<UUID> vertices) {
        this.numEdges = 0;
        this.adjacencyList = new HashMap<>();
        this.vertices = vertices;

        for (UUID vertex : this.vertices) {
            adjacencyList.put(vertex, new HashSet<>());
        }
    }

    public Integer getNumVertices() {
        return this.adjacencyList.size();
    }

    public Integer getNumEdges() {
        return this.numEdges;
    }

    public void addEdge(UUID from, UUID to) {
        if (adjacencyList.containsKey(from) && adjacencyList.containsKey(to)) {
            adjacencyList.get(from).add(to);
            adjacencyList.get(to).add(from);
            ++numEdges;
        }
    }

    public void removeEdge(UUID from, UUID to) {
        if (adjacencyList.containsKey(from) && adjacencyList.containsKey(to)) {
            adjacencyList.get(from).remove(to);
            adjacencyList.get(to).remove(from);
            --numEdges;
        }
    }

    private void orientEdges(UUID vertex, UUID p, Integer h) {
        this.height.put(vertex, h);
        this.parent.put(vertex, p);

        for (UUID to : this.adjacencyList.get(vertex)) {
            // straight edge
            if (this.dirAdjacencyList.containsKey(to) && this.dirAdjacencyList.get(to).contains(vertex)) {
                continue;
            }
            else {
                this.dirAdjacencyList.get(vertex).add(to);
            }

            Pair<UUID, UUID> v_to = new Pair<>(vertex, to);
            this.lowpt.put(v_to, this.height.get(vertex));
            this.lowpt2.put(v_to, this.height.get(vertex));

            // tree edge or back edge
            if (this.height.get(to) == -1) {
                orientEdges(to, vertex, h + 1);
            }
            else {
                this.lowpt.put(v_to, this.height.get(to));
            }

            this.nestingDepth.put(v_to, 2 * this.lowpt.get(v_to));
            if (this.lowpt2.get(v_to) < this.height.get(vertex)) {
                this.nestingDepth.put(v_to, 1 + this.nestingDepth.get(v_to));
            }

            if (p != null) {
                Pair<UUID, UUID> p_v = new Pair<>(p, vertex);

                if (this.lowpt.get(v_to) < this.lowpt.get(p_v)) {
                    this.lowpt2.put(p_v, min(this.lowpt.get(p_v), this.lowpt2.get(p_v)));
                    this.lowpt.put(p_v, this.lowpt.get(v_to));
                }
                else if (this.lowpt.get(v_to) > this.lowpt.get(p_v)) {
                    this.lowpt2.put(p_v, min(this.lowpt2.get(p_v), this.lowpt.get(v_to)));
                }
                else {
                    this.lowpt2.put(p_v, min(this.lowpt2.get(p_v), this.lowpt2.get(v_to)));
                }
            }
        }
    }

    private boolean addConstraints(Pair<UUID, UUID> v_to, Pair<UUID, UUID> p_v) {
        ConflictPair P = new ConflictPair(new Interval(), new Interval());

        while (true) {
            ConflictPair Q = this.stack.remove(this.stack.size() - 1);

            if (!Q.getLeft().isEmpty()) {
                Q.swapIntervals();
            }
            if (!Q.getLeft().isEmpty()) {
                return false;
            }

            if (this.lowpt.get(Q.getRight().getLow()) > this.lowpt.get(p_v)) {
                if (P.getRight().isEmpty()) {
                    P.setRight(Q.getRight());
                }
                else {
                    this.ref.put(P.getRight().getLow(), Q.getRight().getHigh());
                }
                P.getRight().setLow(Q.getRight().getLow());
            }
            else {
                this.ref.put(Q.getRight().getLow(), this.lowptEdge.get(p_v));
            }

            if (!this.stack.isEmpty() && this.stack.get(this.stack.size() - 1) == this.stackBottom.get(v_to)) {
                break;
            }
        }

        while (conflicting(this.stack.get(this.stack.size() - 1).getLeft(), v_to) ||
               conflicting(this.stack.get(this.stack.size() - 1).getRight(), v_to)) {
            ConflictPair Q = this.stack.remove(this.stack.size() - 1);

            if (conflicting(Q.getRight(), v_to)) {
                Q.swapIntervals();
            }
            if (conflicting(Q.getRight(), v_to)) {
                return false;
            }

            this.ref.put(P.getRight().getLow(), Q.getRight().getHigh());
            if (Q.getRight().getLow() != null) {
                P.getRight().setLow(Q.getRight().getLow());
            }

            if (P.getLeft().isEmpty()) {
                P.setLeft(Q.getLeft());
            }
            else {
                this.ref.put(P.getLeft().getLow(), Q.getLeft().getHigh());
            }
            P.getLeft().setLow(Q.getLeft().getLow());
        }

        if (!P.getLeft().isEmpty() || !P.getRight().isEmpty()) {
            this.stack.add(P);
        }
        return true;
    }

    private boolean conflicting(Interval interval, Pair<UUID, UUID> edge) {
        return !interval.isEmpty() && this.lowpt.get(interval.getHigh()) > this.lowpt.get(edge);
    }

    private Integer lowest(ConflictPair conflictPair) {
        if (conflictPair.getLeft().isEmpty()) {
            return this.lowpt.get(conflictPair.getRight().getLow());
        }
        if (conflictPair.getRight().isEmpty()) {
            return this.lowpt.get(conflictPair.getLeft().getLow());
        }

        return min(this.lowpt.get(conflictPair.getLeft().getLow()),
                   this.lowpt.get(conflictPair.getRight().getLow()));
    }

    private boolean checkPlanarity(UUID vertex) {
        Pair<UUID, UUID> p_v = new Pair<>(this.parent.get(vertex), vertex);

        List<UUID> sortedAdjList = new ArrayList<>(this.dirAdjacencyList.get(vertex));
        sortedAdjList.sort(Comparator.comparingInt(neighbour -> nestingDepth.get(new Pair<>(vertex, neighbour))));

        for (UUID to : sortedAdjList) {
            Pair<UUID, UUID> v_to = new Pair<>(vertex, to);

            this.stackBottom.put(v_to, stack.isEmpty() ? null : stack.get(stack.size() - 1));

            if (vertex == this.parent.get(to)) {
                if (!checkPlanarity(to)) {
                    return false;
                }
            }
            else {
                this.lowptEdge.put(v_to, v_to);
                this.stack.add(new ConflictPair(new Interval(), new Interval(v_to, v_to)));
            }

            if (this.lowpt.get(v_to) < this.height.get(vertex)) {
                if (to == sortedAdjList.get(0)) {
                    this.lowptEdge.put(p_v, this.lowptEdge.get(v_to));
                }
                else if (!addConstraints(v_to, p_v)) {
                    return false;
                }
            }
        }

        if (this.parent.get(vertex) != null) {
            UUID p = this.parent.get(vertex);

            while (!this.stack.isEmpty() && Objects.equals(lowest(this.stack.get(this.stack.size() - 1)), this.height.get(p))) {
                ConflictPair P = this.stack.remove(this.stack.size() - 1);
            }

            if (!this.stack.isEmpty()) {
                ConflictPair P = this.stack.remove(this.stack.size() - 1);

                while (P.getLeft().getHigh() != null && P.getLeft().getHigh().second() == p) {
                    P.getLeft().setHigh(this.ref.get(P.getLeft().getHigh()));
                }
                if (P.getLeft().getHigh() == null && P.getLeft().getLow() != null) {
                    this.ref.put(P.getLeft().getLow(), P.getRight().getLow());
                    P.getLeft().setLow(null);
                }

                while (P.getRight().getHigh() != null && P.getRight().getHigh().second() == p) {
                    P.getRight().setHigh(this.ref.get(P.getRight().getHigh()));
                }
                if (P.getRight().getHigh() == null && P.getRight().getLow() != null) {
                    this.ref.put(P.getRight().getLow(), P.getLeft().getLow());
                    P.getRight().setLow(null);
                }

                this.stack.add(P);
            }

            if (this.lowpt.get(p_v) < this.height.get(p)) {
                Pair<UUID, UUID> H_l = this.stack.get(this.stack.size() - 1).getLeft().getHigh();
                Pair<UUID, UUID> H_r = this.stack.get(this.stack.size() - 1).getRight().getHigh();

                if (H_l != null && (H_r == null || this.lowpt.get(H_l) > this.lowpt.get(H_r))) {
                    this.ref.put(p_v, H_l);
                }
                else {
                    this.ref.put(p_v, H_r);
                }
            }
        }

        return true;
    }

    public boolean isPlanar() {
        if (getNumVertices() < 5) {
            return true;
        }
        if (3 * getNumVertices() - 6 < getNumEdges())
        {
            return false;
        }

        this.height = new HashMap<>();
        this.parent = new HashMap<>();

        this.dirAdjacencyList = new HashMap<>();
        for (UUID vertex : this.vertices) {
            this.height.put(vertex, -1);
            this.parent.put(vertex, null);
            this.dirAdjacencyList.put(vertex, new HashSet<>());
        }

        this.lowpt = new HashMap<>();
        this.lowpt2 = new HashMap<>();
        this.nestingDepth = new HashMap<>();

        for (UUID vertex : this.vertices) {
            if (this.height.get(vertex) == -1) {
                orientEdges(vertex, null, 0);
            }
        }

        this.stack = new ArrayList<>();
        this.stackBottom = new HashMap<>();
        this.lowptEdge = new HashMap<>();
        this.ref = new HashMap<>();

        for (UUID vertex : this.vertices) {
            if (this.height.get(vertex) == 0 && !checkPlanarity(vertex)) {
                return false;
            }
        }

        return true;
    }
}