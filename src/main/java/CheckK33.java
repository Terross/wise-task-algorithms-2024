import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CheckK33 implements GraphProperty {
    @Override
    public boolean execute(Graph graph) {
        // Лямбда проверки выделено ли ребро
        Predicate<Edge> cf = (Edge e) -> e.getLabel().equals("+");

        // Стянутые ребра
        var removedEdges = graph.getEdges().stream().filter(cf)
                .collect(Collectors.toCollection(LinkedList::new));
        // Нетронутые ребра
        var restedEdges = graph.getEdges().stream().filter((e) -> !cf.test(e))
                .collect(Collectors.toCollection(LinkedList::new));

        // Группы стянутых вершин
        var groups = new HashSet<HashSet<UUID>>();
        for (var e : removedEdges) {
            HashSet<UUID> group = null;
            for (var g : groups) {
                if (g.contains(e.getFromV()) || g.contains(e.getToV())) {
                    group = g;
                    break;
                }
            }
            if (group == null) group = new HashSet<>();
            group.add(e.getFromV());
            group.add(e.getToV());
            groups.add(group);
        }

        // Новые идентификаторы слитых вершин
        var updatedVertices = new HashMap<UUID, Vertex>();
        for (var g : groups) {
            var uid = Vertex.builder().id(UUID.randomUUID()).build();
            for (var el : g) {
                updatedVertices.put(el, uid);
            }
        }

        // Ребра стянутого графа
        var newEdges = new LinkedList<Edge>();
        for (var e : restedEdges) {
            var edge = new Edge(
                    updatedVertices.get(e.getFromV()).getId(),
                    updatedVertices.get(e.getToV()).getId(),
                    e.getColor(),
                    e.getWeight(),
                    e.getLabel()
            );
            newEdges.add(edge);
        }

        // Проверка на изоморфность K33
        if ((new HashSet<>(updatedVertices.values())).size() != 6) return false;
        if (newEdges.size() != 9) return false;

        HashSet<UUID> left = new HashSet<>();
        HashSet<UUID> right = new HashSet<>();
        for (var e : newEdges) {
            if (left.contains(e.getFromV())) {
                right.add(e.getToV());
            } else if (right.contains(e.getFromV())) {
                left.add(e.getToV());
            } else {
                left.add(e.getFromV());
                right.add(e.getToV());
            }
        }

        return left.size() == right.size() && left.size() == 3;
    }
}
