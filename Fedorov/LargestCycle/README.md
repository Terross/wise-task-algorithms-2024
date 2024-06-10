# Окружение графа (натуральное число с нулем)

## Определения и понятия

Окружение графа — это длина его наибольшего простого цикла. 

Цикл — это последовательность вершин a<sub>1</sub>a<sub>2</sub> . . . a<sub>n</sub>
и различных рёбер e<sub>1</sub>, . . . , e<sub>n</sub> графа G, где e<sub>i</sub> = a<sub>i</sub>a<sub>i+1</sub> для всех i ∈ [1..n]
(мы считаем, что an+1 = a1).

Цикл называется простым, если все вершины a<sub>1</sub>, . . . , a<sub>n</sub> различны.

## Алгоритм

Код реализует алгоритм для нахождения наибольшего простого цикла в ориентированном или неориентированном графе. Вот краткое описание его работы:

Импорт зависимостей. Код начинается с импорта необходимых классов из библиотек.
```java
import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
```


Определение класса LargestCycle: Этот класс реализует интерфейс GraphCharacteristic, который определяет характеристику графа.
```java
public class LargestCycle implements GraphCharacteristic
```


Поля класса:
```java
Integer max_length = 0;
Set<UUID> used_vert = new HashSet<>();
Set<Edge> used_edges = new HashSet<>();
```

max_length - инициализируется нулем, предназначено для отслеживания максимальной длины найденного цикла.
used_vert - хранит множество уже посещенных вершин.
used_edges - хранит множество уже посещенных рёбер.


Метод execute - этот метод вызывается для выполнения алгоритма. Он проходит по всем вершинам графа и вызывает либо метод bnb_dir, либо bnb_undir в зависимости от типа графа (ориентированного или неориентированного).

```java
@Override
    public Integer execute(Graph graph) {
        if (graph.getDirectType() == GraphType.DIRECTED) {
            for (UUID vertexId : graph.getVertices().keySet()) {
                used_vert.clear();
                bnb_dir(graph, vertexId, vertexId);
            }
        }else{
            for (UUID vertexId : graph.getVertices().keySet()) {
                used_vert.clear();
                used_edges.clear();
                bnb_undir(graph, vertexId, vertexId);
            }
        }

        return max_length;
    }
```
Методы bnb_undir и bnb_dir: Это рекурсивные методы, которые используются для поиска циклов в неориентированных и ориентированных графах соответственно. Они осуществляют обход графа методом ветвей и границ и отслеживают посещенные вершины и рёбра. Когда обход достигает стартовой вершины, он проверяет, является ли обнаруженный цикл наибольшим.
```java
    public void bnb_undir(Graph graph, UUID startVertexId, UUID currentVertexId) {
        if (used_vert.size() > 1 && startVertexId.equals(currentVertexId)) {
            max_length = Math.max(used_vert.size(), max_length);
            return;
        }
        
        if (!used_vert.contains(currentVertexId)) {
            used_vert.add(currentVertexId);
            for (Edge edge : graph.getEdges()) {
                if (!used_edges.contains(edge)) {
                    if (edge.getFromV().equals(currentVertexId)) {
                        used_edges.add(edge);
                        UUID nextVertexId = edge.getToV();
                        used_vert.add(currentVertexId);
                        bnb_undir(graph, startVertexId, nextVertexId);
                        used_edges.remove(edge);
                    }
                    if (edge.getToV().equals(currentVertexId)) {
                        used_edges.add(edge);
                        UUID nextVertexId = edge.getFromV();
                        bnb_undir(graph, startVertexId, nextVertexId);
                        used_edges.remove(edge);
                    }
                }
            }
            used_vert.remove(currentVertexId);
        }
    }
    
    public void bnb_dir(Graph graph, UUID startVertexId, UUID currentVertexId) {
        if (used_vert.size() > 1 && startVertexId.equals(currentVertexId)) {
            max_length = Math.max(used_vert.size(), max_length);
            return;
        }
        
        if (!used_vert.contains(currentVertexId)) {
            used_vert.add(currentVertexId);
            for (Edge edge : graph.getEdges()) {
                if (edge.getFromV().equals(currentVertexId)) {
                    UUID nextVertexId = edge.getToV();
                    bnb_dir(graph, startVertexId, nextVertexId);
                }
            }
            used_vert.remove(currentVertexId);
        }
    }
```

## Примечания:
Если граф состоит из одной вершины, то цикл длины 0. 
Если граф пустой, то цикл длины 0.
Случай с петлями не рассматривается, поскольку в программе нет возможности создать петли.