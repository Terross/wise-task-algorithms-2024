import com.mathsystem.api.graph.model.*;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;
import static java.lang.Math.*;


public class IsPlanar implements GraphProperty {
    int edges_count = 0;
    int vertexes_count = 0;
    int[][] adjacency_matrix;
    List<Edge> edges;
    List<UUID> vertexes;
    ArrayList<ArrayList<Integer>> components;
    Set<ArrayList<Integer>> bridges;

    @Override
    public boolean execute(Graph graph) {
        setValues(graph); // Установка значений переменных переданного графа

        boolean[] visited = new boolean[vertexes_count];
        int[] low = new int[vertexes_count];
        int[] time_in = new int[vertexes_count];
        int[] parent = new int[vertexes_count];

        // Поиск мостов
        for (int i = 0; i < vertexes_count; i++) {
            int time = 0;
            dfsBridges(i, visited, time_in, low, parent, time);
        }

        // Удаление мостов
        for (ArrayList<Integer> bridge : bridges) {
            adjacency_matrix[bridge.get(0)][bridge.get(1)] = 0;
            adjacency_matrix[bridge.get(1)][bridge.get(0)] = 0;
        }

        findComponents(); // Поиск компонент связности

        // Для каждой компонеты составляется матрица смежности и проверяется на планарность
        boolean planar = true;
        for (ArrayList<Integer> current_component : components) {
            if (!planar)
                break;

            int size = current_component.size();
            if (size > 4) {  // Пропускаем компоненты с менее 5 вершинами
                int[][] matrix = new int[size][size];
                int k = 0;
                for (int row = 0; row < size; row++){ // Для матрицы смежности каждой компонеты вычеркиваем строки и столбцы из основной матрицы
                    if (current_component.contains(row))
                        for (int col = 0; col < size; col++){
                            if (current_component.contains(col)) {
                                matrix[k/size][k%size] = adjacency_matrix[row][col];
                                k++;
                            }
                        }
                }
                planar = planarLaying(matrix); // Проверка на планарность компоненты
            }
        }
        return planar;
    }

    // Установка значений переменных переданного графа
    private void setValues(Graph graph) {
        vertexes_count = graph.getVertexCount();
        edges_count = graph.getEdgeCount();
        edges = graph.getEdges();
        vertexes = new ArrayList<>();
        adjacency_matrix = new int[vertexes_count][vertexes_count];
        components = new ArrayList<>();
        bridges = new HashSet<>();

        // Заполнение матрицы смежности
        for (int i = 0; i < edges_count; i++) {
            Edge edge = edges.get(i);
            if (!vertexes.contains(edge.getFromV()))
                vertexes.add(edge.getFromV());
            if (!vertexes.contains(edge.getToV()))
                vertexes.add(edge.getToV());
            adjacency_matrix[vertexes.indexOf(graph.getEdges().get(i).getFromV())][vertexes.indexOf(graph.getEdges().get(i).getToV())] = 1;
            adjacency_matrix[vertexes.indexOf(graph.getEdges().get(i).getToV())][vertexes.indexOf(graph.getEdges().get(i).getFromV())] = 1;
        }
    }

    // Поиск компонет связности
    private void findComponents() {
        ArrayList<Integer> current_component = new ArrayList<>();
        boolean[] visited = new boolean[vertexes_count];
        for (int i = 0; i < vertexes_count; ++i)
            if (!visited[i]) {
                current_component.clear();
                dfsComponents(i, current_component, visited);
                components.add((ArrayList<Integer>) current_component.clone());
            }
    }

    // Обход в глубину для поиска компонент связности
    private void dfsComponents(int vertex, ArrayList<Integer> current_component, boolean[] visited) {
        visited[vertex] = true;
        current_component.add(vertex);

        for (int i = 0; i < adjacency_matrix[vertex].length; i++) {
            if (adjacency_matrix[vertex][i] == 1 && (!visited[i])) {
                dfsComponents(i, current_component, visited);
            }
        }
    }

    // Обход в глубину для поиска мостов
    void dfsBridges(int start, boolean[] visited, int[] time_in, int[] low, int[] parent, int time) {
        visited[start] = true;
        time_in[start] = low[start] = ++time;

        for(int v = 0; v < vertexes_count; v++) {
            if(adjacency_matrix[start][v] == 1) {
                if(!visited[v]) {
                    parent[v] = start;
                    dfsBridges(v, visited, time_in, low, parent, time);
                    low[start] = min(low[start], low[v]);

                    if(low[v] > time_in[start]) {
                        ArrayList<Integer> pair = new ArrayList<>();
                        pair.add(start);
                        pair.add(v);
                        bridges.add(pair);
                    }

                } else if(v != parent[start])
                    low[start] = min(low[start], time_in[v]);
            }
        }
    }

    // Добавление ребра в матрицу смежности
    private void addEdge(int k, int m, int[][] matrix) {
        matrix[k][m] = matrix[m][k] = 1;
    }

    // Проверка на содержание ребра в матрице смежности
    private boolean containsEdge(int k, int m, int[][] matrix) {
        return (matrix[k][m] == 1);
    }

    // Обход в глубину для поиска простого цикла
    private boolean dfsCycle(ArrayList<Integer> result, int[] visited, int parent, int v, int[][] matrix) {
        visited[v] = 1;
        for (int i = 0; i < matrix.length; i++) {
            if (i == parent)
                continue;
            if (matrix[v][i] == 0)
                continue;
            if (visited[i] == 0) {
                result.add(v);
                if (dfsCycle(result, visited, v, i, matrix))
                    return true; // Цикл найден
                else
                    result.remove(result.size() - 1);
            }
            if (visited[i] == 1) {
                // Цикл найден
                result.add(v);
                ArrayList<Integer> cycle = new ArrayList<>();
                // "Выдергиваем" вершины цикла из порядка обхода
                for (int j = 0; j < result.size(); j++) {
                    if (result.get(j) == i) {
                        cycle.addAll(result.subList(j, result.size()));
                        result.clear();
                        result.addAll(cycle);
                        return true;
                    }
                }
                return true;
            }
        }
        visited[v] = 2;
        return false;
    }

    // Поиск связных компонент графа G - G', дополненного ребрами из G, один из концов которых принадлежит связной компоненте, а другой G'
    private void dfsSegments(int[] used, boolean[] laid_vertexes, int[][] result, int v, int[][] matrix) {
        used[v] = 1;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[v][i] == 1) {
                addEdge(v, i, result);
                if (used[i] == 0 && !laid_vertexes[i])
                    dfsSegments(used, laid_vertexes, result, i, matrix);
            }
        }
    }

    // Поиск сегментов
    private ArrayList<int[][]> getSegments(boolean[] laid_vertexes, boolean[][] edges, int[][] matrix) {
        ArrayList<int[][]> segments = new ArrayList<>();
        // Поиск однореберных сегментов
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                if (matrix[i][j] == 1 && !edges[i][j] && laid_vertexes[i] && laid_vertexes[j]) {
                    int[][] temporary = new int[matrix.length][matrix.length];
                    addEdge(i, j, temporary);
                    segments.add(temporary);
                }
            }
        }
        // Поиск связных компонент графа G - G', дополненного ребрами из G, один из концов которых принадлежит связной компоненте, а другой G'
        int[] visited = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            if (visited[i] == 0 && !laid_vertexes[i]) {
                int[][] result = new int[matrix.length][matrix.length];
                dfsSegments(visited, laid_vertexes, result, i, matrix);
                segments.add(result);
            }
        }
        return segments;
    }

    // Обход в глубину для поиска цепи в выбранном сегменте
    private void dfsChain(int[] visited, boolean[] laid_vertexes, ArrayList<Integer> chain, int v, int[][] matrix) {
        visited[v] = 1;
        chain.add(v);
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[v][i] == 1 && visited[i] == 0) {
                if (!laid_vertexes[i]) {
                    dfsChain(visited, laid_vertexes, chain, i, matrix);
                } else {
                    chain.add(i);
                }
                return;
            }
        }
    }

    // Поиск цепи
    private ArrayList<Integer> getChain(boolean[] laid_vertexes, int[][] matrix) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            if (laid_vertexes[i]) {
                boolean in_graph = false;
                for (int j = 0; j < matrix.length; j++) {
                    if (containsEdge(i, j, matrix)) {
                        in_graph = true;
                        break;
                    }
                }
                if (in_graph) {
                    dfsChain(new int[matrix.length], laid_vertexes, result, i, matrix);
                    break;
                }
            }
        }
        return result;
    }

    // Укладка цепи
    private void layChain(boolean[][] result, ArrayList<Integer> chain, boolean cyclic) {
        for (int i = 0; i < chain.size() - 1; i++) {
            result[chain.get(i)][chain.get(i + 1)] = true;
            result[chain.get(i + 1)][chain.get(i)] = true;
        }
        if (cyclic) {
            result[chain.get(0)][chain.get(chain.size() - 1)] = true;
            result[chain.get(chain.size() - 1)][chain.get(0)] = true;
        }
    }

    // Проверка на то, что данный сегмент содержится в данной грани
    private boolean isFaceContainsSegment(ArrayList<Integer> face, int[][] segment, boolean[] laid_vertexes, int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (containsEdge(i, j, segment)) {
                    if ((laid_vertexes[i] && !face.contains(i)) || (laid_vertexes[j] && !face.contains(j))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    // Подсчет числа граней, вмещающих данные сегмент
    private int[] NumberOfFacesContainedSegments(ArrayList<ArrayList<Integer>> internal_faces, ArrayList<Integer> external_face, ArrayList<int[][]> segments, boolean[] laid_vertexes, ArrayList<Integer>[] dest_faces, int[][] matrix) {
        int[] count = new int[segments.size()];
        for (int i = 0; i < segments.size(); i++) {
            for (ArrayList<Integer> face : internal_faces) {
                if (isFaceContainsSegment(face, segments.get(i), laid_vertexes, matrix)) {
                    dest_faces[i] = face;
                    count[i]++;
                }
            }
            if (isFaceContainsSegment(external_face, segments.get(i), laid_vertexes, matrix)) {
                dest_faces[i] = external_face;
                count[i]++;
            }
        }
        return count;
    }

    // Проверка на возможность плоской укладки графа
    public boolean planarLaying(int[][] matrix) {
        // Ищем цикл, если его нет, то граф не соответствует условиям алгоритма (Нет циклов => дерево => планарный)
        ArrayList<Integer> cycle = new ArrayList<>();
        boolean hasCycle = dfsCycle(cycle, new int[matrix.length], -1, 0, matrix); // Поиск цикла
        if (!hasCycle)
            return true;

        if(cycle.isEmpty())
            return true;

        // Списки граней
        ArrayList<ArrayList<Integer>> internal_faces = new ArrayList<>();
        ArrayList<Integer> external_face = (ArrayList<Integer>) cycle.clone();
        internal_faces.add(cycle);
        internal_faces.add(external_face);

        // Массивы уже уложенных вершин и ребер соответственно
        boolean[] laid_vertexes = new boolean[matrix.length];
        boolean[][] laid_edges = new boolean[matrix.length][matrix.length];
        for (int i : cycle)
            laid_vertexes[i] = true;
        
        // Укладка найденного цикла
        layChain(laid_edges, cycle, true);
        
        // Выделение множества сегментов, подсчет числа вмещающих граней, выделение цепей из сегментов, укладка цепей, добавление новых граней
        while (true) {
            ArrayList<int[][]> segments = getSegments(laid_vertexes, laid_edges, matrix);
            //Если нет сегментов, то граф - простой цикл => планарный
            if (segments.size() == 0)
                break;
            
            // Массив граней, в которые будут уложены соответствующие сегменты с минимальным числом NumberOfFacesContainedSegments()
            ArrayList<Integer>[] dest_faces = new ArrayList[segments.size()];
            int[] count = NumberOfFacesContainedSegments(internal_faces, external_face, segments,laid_vertexes, dest_faces, matrix);

            // Ищем минимальное число NumberOfFacesContainedSegments()
            int minimal = 0;
            for (int i = 0; i < segments.size(); i++) {
                if (count[i] < count[minimal])
                    minimal = i;
            }
            // Если хотя бы одно ноль, то граф не планарный
            if (count[minimal] == 0)
                return false;

            //Укладка выбранного сегмента
            else {
                // Выделяем цепь между двумя контактными вершинами
                ArrayList<Integer> chain = getChain(laid_vertexes, segments.get(minimal));
                // Помечаем вершины цепи как уложенные
                for (int i : chain)
                    laid_vertexes[i] = true;

                // Укладываем соответствующие ребра цепи
                layChain(laid_edges, chain, false);

                // Целевая грань, куда будет уложен выбранный сегмент
                ArrayList<Integer> face = dest_faces[minimal];

                // Новые грани, порожденные разбиением грани face выбранным сегментом
                ArrayList<Integer> face1 = new ArrayList<>();
                ArrayList<Integer> face2 = new ArrayList<>();

                // Ищем номера контактных вершин цепи
                int contact1 = 0, contact2 = 0;
                for (int i = 0; i < face.size(); i++) {
                    if (face.get(i).equals(chain.get(0)))
                        contact1 = i;
                    if (face.get(i).equals(chain.get(chain.size() - 1)))
                        contact2 = i;
                }

                // Находим обратную цепь (цепь, пробегаемая в обратном направлении)
                ArrayList<Integer> reverse_chain = (ArrayList<Integer>) chain.clone();
                Collections.reverse(reverse_chain);
                int face_size = face.size();
                if (face != external_face) { // Если целевая грань не внешняя, то укладываем прямую цепь в одну из порожденных граней, а обратную в другую в зависимости от номеров контактных вершин
                    if (contact1 < contact2) {
                        face1.addAll(chain);
                        for (int i = (contact2 + 1) % face_size; i != contact1; i = (i + 1) % face_size)
                            face1.add(face.get(i));
                        face2.addAll(reverse_chain);
                        for (int i = (contact1 + 1) % face_size; i != contact2; i = (i + 1) % face_size)
                            face2.add(face.get(i));
                    }
                    else {
                        face1.addAll(reverse_chain);
                        for (int i = (contact1 + 1) % face_size; i != contact2; i = (i + 1) % face_size)
                            face1.add(face.get(i));
                        face2.addAll(chain);
                        for (int i = (contact2 + 1) % face_size; i != contact1; i = (i + 1) % face_size)
                            face2.add(face.get(i));
                    }
                    // Удаляем целевую грань (она разбилась на две новые) и добавляем порожденные грани в множество внутренних граней
                    internal_faces.remove(face);
                    internal_faces.add(face1);
                    internal_faces.add(face2);
                }
                else { // Если целевая грань совпала с внешней, то делаем все то же самое, только одна из порожденных граней - новая внешняя грань
                    ArrayList<Integer> new_external_face = new ArrayList<>();
                    if (contact1 < contact2) {
                        new_external_face.addAll(chain);
                        for (int i = (contact2 + 1) % face_size; i != contact1; i = (i + 1) % face_size)
                            new_external_face.add(face.get(i));
                        face2.addAll(chain);
                        for (int i = (contact2 - 1 + face_size) % face_size; i != contact1; i = (i - 1 + face_size) % face_size)
                            face2.add(face.get(i));
                    }
                    else {
                        new_external_face.addAll(reverse_chain);
                        for (int i = (contact1 + 1) % face_size; i != contact2; i = (i + 1) % face_size)
                            new_external_face.add(face.get(i));
                        face2.addAll(reverse_chain);
                        for (int i = (contact1 - 1 + face_size) % face_size; i != contact2; i = (i - 1 + face_size) % face_size)
                            face2.add(face.get(i));
                    }
                    // Удаляем старые грани, добавляем новые
                    internal_faces.remove(external_face);
                    internal_faces.add(new_external_face);
                    internal_faces.add(face2);
                    external_face = new_external_face;
                }
            }
        }
        return true;
    }
}
