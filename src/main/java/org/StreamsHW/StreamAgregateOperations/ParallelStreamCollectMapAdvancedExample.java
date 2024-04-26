package org.StreamsHW.StreamAgregateOperations;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParallelStreamCollectMapAdvancedExample {

    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Student1", Map.of("Math", 90, "Physics", 85)),
                new Student("Student2", Map.of("Math", 95, "Physics", 88)),
                new Student("Student3", Map.of("Math", 88, "Chemistry", 92)),
                new Student("Student4", Map.of("Physics", 78, "Chemistry", 85))
        );


        //Создайте коллекцию студентов, где каждый студент содержит информацию о предметах, которые он изучает, и его оценках по этим предметам.
        List<Student> addedStudents = new ArrayList<>();
        students.stream().forEach(addedStudents::add);

        //Используйте Parallel Stream для обработки данных и создания Map, где ключ - предмет, а значение - средняя оценка по всем студентам.
        Map<String, Double> averageAssessmentByGrade =
                addedStudents.stream()
                        .parallel()
                        .map(Student::getGrades)
                        .flatMap(s -> s.entrySet().stream())
                        .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.averagingDouble(Map.Entry::getValue)));

        //Выведите результат: общую Map с средними оценками по всем предметам.
        for (Map.Entry<String, Double> s : averageAssessmentByGrade.entrySet()) {
            System.out.print(s.getKey() + " ");
            System.out.println(s.getValue());
        }


    }
}

