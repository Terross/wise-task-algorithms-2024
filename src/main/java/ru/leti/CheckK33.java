package ru.leti;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CheckK33 implements GraphProperty {
    @Override
    public boolean execute(Graph graph) {
        // Лямбда проверки выделено ли ребро
        Predicate<Edge> cf = (Edge e) -> e.getColor().equals(Color.red);

        // Стянутые ребра
        var removedEdges = graph.getEdges().stream().filter(cf)
                .collect(Collectors.toCollection(LinkedList::new));
        // Нетронутые ребра
        var restedEdges = graph.getEdges().stream().filter((e) -> !cf.test(e))
                .collect(Collectors.toCollection(LinkedList::new));

        // Группы стянутых вершин
        var checked = new HashSet<UUID>();
        var groups = new HashSet<HashSet<UUID>>();
        for (var e : removedEdges) {
            HashSet<UUID> group = null;
            for (var g : groups) {
                if (g.contains(e.getFromV()) || g.contains(e.getToV())) {
                    group = g;
                    break;
                }
            }
            if (group == null) {
                group = new HashSet<>();
                groups.add(group);
            }
            group.add(e.getFromV());
            checked.add(e.getFromV());

            group.add(e.getToV());
            checked.add(e.getToV());
        }
        graph.getVertices().values().stream()
                .filter(v -> !checked.contains(v.getId()))
                .forEach(v -> groups.add(new HashSet<>(Collections.singleton(v.getId()))));

        // Новые идентификаторы слитых вершин
        var newVertices = new HashMap<UUID, Vertex>();
        for (var g : groups) {
            var v = Vertex.builder().id(UUID.randomUUID()).build();
            for (var el : g) {
                newVertices.put(el, v);
            }
        }

        // Ребра стянутого графа
        var newEdges = restedEdges;
        for (var e : newEdges) {
            if (newVertices.containsKey(e.getFromV()))
                e.setFromV(newVertices.get(e.getFromV()).getId());
            if (newVertices.containsKey(e.getToV()))
                e.setToV(newVertices.get(e.getToV()).getId());
        }

        // Проверка на изоморфность K33
        if (groups.size() != 6) return false;
        if (newEdges.size() != 9) return false;

        HashSet<UUID> left = new HashSet<>(Collections.singleton(newEdges.getFirst().getFromV()));
        HashSet<UUID> right = new HashSet<>();
        for (var e: newEdges) {
            if (left.contains(e.getFromV())) right.add(e.getToV());
            else if (left.contains(e.getToV())) right.add(e.getFromV());
        }
        for (var e: newEdges) {
            if (left.contains(e.getFromV())) right.add(e.getToV());
            else if (left.contains(e.getToV())) right.add(e.getFromV());
            else if (right.contains(e.getFromV())) left.add(e.getToV());
            else if (right.contains(e.getToV())) left.add(e.getFromV());
        }

        return left.size() == right.size() && left.size() == 3;
    }
}
