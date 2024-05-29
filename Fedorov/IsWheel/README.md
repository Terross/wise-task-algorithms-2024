# Граф - колесо
## Определение
В теории графов колесом называется граф с n вершинами (n ≥ 4), образованный соединением единственной вершины со всеми вершинами (n-1)-цикла
## Алгоритм
Снначла проверяется, что граф имеет хотя бы 4 вершины. Далее метод ищет вершину, соединенную со всеми остальными, если такой вершины нет, то граф не является колесом. Дальше проверяется, что все остальные вершины соединены с центром и ещё с двумя другими вершинами.
```Java
public boolean execute(Graph graph) {
        if (graph.getEdgeCount() < 4) {
            return false;
        }
        Map<UUID, List<UUID>> adjacencyList = getAdjacencyList(graph);
        int n = adjacencyList.size();

        // Находим центр и его соседей
        UUID center = null;
        for (Map.Entry<UUID, List<UUID>> entry : adjacencyList.entrySet()) {
            UUID vertex = entry.getKey();
            List<UUID> neighbors = entry.getValue();
            if (neighbors.size() == n - 1) {
                center = vertex;
                break;
            }
        }
        // Если не найдена вершина, соединенная со всеми остальными, граф не является колесом
        if (center == null)
            return false;

        // Проверяем, что все остальные вершины соединены с центром и ещё с двумя другоими вершинами
        for (Map.Entry<UUID, List<UUID>> entry : adjacencyList.entrySet()) {
            UUID vertex = entry.getKey();
            List<UUID> neighbors = entry.getValue();
            if (!vertex.equals(center)) {
                if (neighbors.size() != 3 || !neighbors.contains(center))
                    return false;
            }
        }

        return true;
    }
```