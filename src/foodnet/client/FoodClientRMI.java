package foodnet.client;

import foodnet.rmi.FoodService;
import foodnet.data.Dish;
import foodnet.data.Order;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;
import java.io.*;

public class FoodClientRMI {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("10.195.59.160", 1099);
        FoodService service = (FoodService) registry.lookup("FoodService");

        List<Dish> menu = service.getMenu();
        System.out.println("Menu:");
        menu.forEach(System.out::println);

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your order (comma-separated):");
        String orderString = sc.nextLine();
        List<String> names = Arrays.asList(orderString.split("\\s*,\\s*"));

        double sum = service.calculateSum(names);
        System.out.println("Order total: " + sum);

        System.out.println("Enter delivery address:");
        String addr = sc.nextLine();

        List<Dish> orderedDishes = new ArrayList<>();
        for (String n : names) {
            for (Dish d : menu) if (d.getName().equalsIgnoreCase(n.trim())) { orderedDishes.add(d); break; }
        }
        Order order = new Order(orderedDishes, addr);
        service.submitOrder(order);

        System.out.println("Order placed via RMI.");
        sc.close();
    }
}
