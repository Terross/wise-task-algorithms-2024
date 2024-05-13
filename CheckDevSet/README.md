# Разделяющие множества

## Определение
Пусть _X, Y ⊂ V (G), R ⊂ V (G) ∪ E(G)_.
1) Назовем множество R разделяющим, если граф G − R несвязен.
2) Пусть _X ⊄ R, Y ⊄ R_. Будем говорить, что R разделяет множества X и Y (или, что то же самое, отделяет множества X и Y друг от
друга), если никакие две вершины _v<sub>x</sub> ∈ X_ и _v<sub>y</sub> ∈ Y_ не лежат в одной
компоненте связности графа G − R

## Пример
![img1.png](pictures%2Fimg1.png) <br>
*Красные вершины являются разделяющим множеством данного графа. После их удаления граф распадается на 6 компонент связности, хотя изначально компонента была одна*

## Алгоритм
*Выделенное множество - вершины графа красного цвета.* <br>
1. Для решения данной задачи воспользуемся модифицированным поиском в глубину.
1. Для начала раскрасим вершины одной компонент связности в один цвет, а сами компоненты в разные цвета, чтобы отличать компонент связности друг от друга. Для упрощения работы был создан специальный класс *ColorNode* который фактически является расширением уже созданного класса Vertex. Поле *color* отвечается за номер цвета, который относится к некоторой компонент связности.
```Java 
  public class ColorNode
    {
        public int color = 0;
        public Vertex node;
        public ColorNode(Vertex node) {
            this.node = node;
        }
    }
```
3. Основная покраска вершин происход в методе *solve*. 
```Java
Vector<ColorNode> set_color_nodes = new Vector<>();
    public boolean solve(Graph graph, boolean with_red_node)
    {
        int number_color = 0;
        if (with_red_node) {
            for (Vertex ver : graph.getVertices().values()) {
                ColorNode temp = new ColorNode(ver);
                set_color_nodes.add(temp);
            }
        }
        for (ColorNode node : set_color_nodes) {
            if (with_red_node) {
                if (node.color == 0 && node.node.getColor() != Color.red) {
                    number_color = number_color + 1;
                    dfs(node, graph, number_color, set_color_nodes, with_red_node);
                }
            }
            else {
                if (!visited.contains(node)) {
                    dfs(node, graph, number_color, set_color_nodes, with_red_node);
                    if (unic_set.size() > 2){
                        return true;
                    }
                    unic_set.clear();
                }
            }
        }
        return false;
    }

```
В первую очередь в данном методе строится множество *ColorNode* посредством прохода по имеющимся вершинам. После этого запускаем цикл, в котором совершаем проход по всем *ColorNode's* (т.е. по всем вершинам графа). В данном методе присутствует флаг *with_red_node*, который игнорирует вершины красного цвета. Таким образом первым проходом с флагом *with_red_node* мы красим все вершины в номера их компонент связности, пропуская красные вершины.
```Java
public void dfs(ColorNode color_node, Graph graph, int number_color, Vector<ColorNode> set_color_node)
    {
        if (color_node.color == 0) {
            color_node.color = number_color;
            for (Edge edge : graph.getEdges()) {
                if (edge.getFromV().equals(color_node.node.getId())) {
                    ColorNode temp_color = getColorNode(edge.getToV(), set_color_node);
                    dfs(temp_color, graph, number_color, set_color_node);
                }
                else if (edge.getToV().equals(color_node.node.getId())) {
                    ColorNode temp_color = getColorNode(edge.getFromV(), set_color_node);
                    dfs(temp_color, graph, number_color, set_color_node);
                }
            }
        }
    }
```
```Java
    HashSet<Integer> unic_set = new HashSet<>();
    HashSet<ColorNode> visited = new HashSet<>();
    public void dfs(ColorNode color_node, Graph graph, int number_color, Vector<ColorNode> set_color_node, boolean with_red_node)
    {
        if (with_red_node) {
            if (color_node.color == 0 && color_node.node.getColor() != Color.red) {
                color_node.color = number_color;
                workNode(graph, color_node, set_color_node, with_red_node, number_color);
            }
        }
        else {
            if (!visited.contains(color_node)) {
                visited.add(color_node);
                unic_set.add(color_node.color);
                workNode(graph, color_node, set_color_node, with_red_node, number_color);
            }
        }
    }
```
4. После этого выполняем второй запуск метода *solve* уже без флага *with_red_node*. Таким образом мы совершаем обход по всему графу. Мы запускаем от каждой вершины dfs и на каждом шаге записываем в *unic_set* цвет встретившейся вершины.
```Java
          else {
                if (!visited.contains(node)) {
                    dfs(node, graph, number_color, set_color_nodes, with_red_node);
                    if (unic_set.size() > 2){
                        return true;
                    }
                    unic_set.clear();
                }
            }
```
  6. После выполнения dfs в *unic_set* будут лежать все встретившиеся в компонент связности цвета и если их оказывается больше 2 (т.е. 0 у красных вершин и какой-то цвет компоненты), то компонента распалась и возвращаем true.
  7. Таким образом, если во время прохода алгоритм не вернул true, то возвращается false;
## *Примечания:*
1. *Если подать граф без помеченных вершин - false, если нет множества, то оно не является разделяющим. Таким же образом граф без любых вершин.*
2. *Если подать ориентированный граф - false.*
3. *Если весь граф это выделенное множество - false, так как компонент связности не остается.*

