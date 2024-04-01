# wise-task-algorithms-2024

## 1. Создание проекта
Maven проект версися java не выше 17
![img1.png](docs%2Fimg1.png)

## 2. pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>ru.leti</groupId>
    <artifactId>NodesEqEdges</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <junit.version>5.7.0</junit.version>
        <assertj.version>3.23.1</assertj.version>
        <mokito.version>4.8.1</mokito.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>


    <dependencies>
        <dependency>
            <groupId>com.example</groupId>
            <artifactId>math-system</artifactId>
            <version>1.0-SNAPSHOT</version>
            <scope>system</scope>
            <type>jar</type>
            <systemPath>/home/dmitry/Documents/wise-task/math-system-1_0-SNAPSHOT.jar</systemPath>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>RELEASE</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.assertj</groupId>
            <artifactId>assertj-core</artifactId>
            <version>3.23.1</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>RELEASE</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>
```

## 3. Написание модуля
1. GraphProperty - если реализуете свойство графа (то есть можно дать ответ да/нет на вопрос)
2. GraphCharacteristic - если реализуете числовую характеристику (то есть ответ модуля - число)

Пример простого свойства:
```java
public class NodesEqEdges implements GraphProperty {
    @Override
    public boolean execute(Graph graph) {
        return graph.getEdgeCount().equals(graph.getVertexCount());
    }
}
```

## 4. Ручное тестирование
1. Скачиваем граф файлом
![img2.png](docs%2Fimg2.png) 
2. В функции main можно создать екземляр класса модуля тестировать его
```java
import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        NodesEqEdges nodesEqEdges = new NodesEqEdges();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/graph.txt"));
        boolean result = nodesEqEdges.execute(testGraph);
        System.out.println(result);
    }
}
```

## 5. Автоматизированное тестирование
1. Скачать множество графов как файлы
2. Написать юнит тесты к разрабатываемому модулю
```java
import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class NodesEqEdgesTest {

    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        NodesEqEdges nodesEqEdges = new NodesEqEdges();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/trueGraph.txt"));
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/falseGraph.txt"));

        assertThat(nodesEqEdges.execute(trueGraph)).isTrue();
        assertThat(nodesEqEdges.execute(falseGraph)).isFalse();
    }
}
```

## 6. Сборка проекта
1. Создаете пустой артефакт с названием единтичным названию проекта и класса модуля.
2. В него добавляем только compile output.
3. В папке out/artifact будет искомый модуль. Если все сделано верно он не будет много весить.
![img3.png](docs%2Fimg3.png)

## 7. Создание PR на github
Для проверки модуля необходимо через форк создать PR в github. От одной команды 1 ПР. Перед отправкой на поверку мне
его должен просмотреть и одобрить капитан команды. Все изменения должны быть вложены в папку команды (на уровне docs и example 
вэто репозитории). Прилагать можно весь проект idea кроме папки target и .idea (папка out обязательна). Также
Необходимо создавать файл README.md в котором описывать необходимую теорию для модуля.

## 8. Загрузка модуля в систему
Для загрузки модуля в систему необходимо заполнить все поля и нажать кнопку.
Далее останется ждать его верификации
![img4.png](docs%2Fimg4.png)

## 9. Составление задачи
После того, как модуль будет добавлен в систему можно будет составлять задачи с ним в констуркторе задач

