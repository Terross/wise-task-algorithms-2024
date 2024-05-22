## Сумма степеней двух любых вершин графа больше или равна числу вершин графа, уменьшенному на единицу
# Определения 
1.Число вершин графа G называют его порядком и обозначают |G|.
2.Степенью вершины u (deg(u)) графа G называют количество ребер графа G инцидентных вершине u.
# Алгоритм
Проверяем, есть ли хотя бы две вершины в графе
``` Java
        if (n < 2)
            return false;
```

Далее проходим по всем парам вершин и находим сумму их степеней. Если сумма степеней пары вершин < n - 1, возвращаем false, где n -- количество вершин в графе. Если все пары удовлетворяют условию, возвращаем false.
```Java 
        for (Map.Entry<UUID, List<UUID>> entry1 : adjacencyList.entrySet()) {
            List<UUID> neighbors1 = entry1.getValue();
            for (Map.Entry<UUID, List<UUID>> entry2 : adjacencyList.entrySet()) {
                List<UUID> neighbors2 = entry2.getValue();
                int degreeSum = neighbors1.size() + neighbors2.size(); // Сумма степеней двух вершин
                if (degreeSum < n - 1)
                    return false;
            }
        }
        return true;
