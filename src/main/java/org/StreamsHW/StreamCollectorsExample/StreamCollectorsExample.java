package org.StreamsHW.StreamCollectorsExample;

import java.util.*;
import java.util.stream.Collectors;

public class StreamCollectorsExample {

    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );

       // Создайте список заказов с разными продуктами и их стоимостями.
        List<Order> addOrders = new ArrayList<>();
        orders.stream().forEachOrdered(addOrders::add);

        //Группируйте заказы по продуктам.
        Map<String, List<Order>> groupByProduct = addOrders.stream().collect(Collectors.groupingBy(Order::getProduct));
       /* for (Map.Entry<String, List<Order>> s : groupByProduct.entrySet()) {
            System.out.println(s.getKey() + s.getValue());
        }*/
        //еще группировка - сортировка
        List<Order> sortedOrders = addOrders.stream().sorted(Comparator.comparing(Order::getProduct)).toList();
       /* for (Order s : sortedOrders) {
            System.out.println(s.getProduct());
        }*/

        //Для каждого продукта найдите общую стоимость всех заказов.
        Map<String, Double> orderCost = addOrders
                .stream()
                .collect(Collectors.groupingBy(Order::getProduct, Collectors.summingDouble(Order::getCost)));
        /*for (Map.Entry<String, Double> s : orderCost.entrySet()) {
            System.out.println(s.getKey() + " " + s.getValue());
        }*/
        //Отсортируйте продукты по убыванию общей стоимости.
        List<Order> sortedOrdersByCost = addOrders.stream().sorted(Comparator.comparing(Order::getCost).reversed()).toList();
        /*for (Order s : sortedOrdersByCost) {
            System.out.println(s.getProduct() + " " + s.getCost());
        }*/

        //Выберите три самых дорогих продукта.
        List<Order> topHighCostOrders = sortedOrdersByCost.stream().limit(3).toList();

        //Выведите результат: список трех самых дорогих продуктов и их общая стоимость.
        for (Order s : topHighCostOrders) {
            System.out.println(s.getProduct() + " " + s.getCost());
        }

    }
}
