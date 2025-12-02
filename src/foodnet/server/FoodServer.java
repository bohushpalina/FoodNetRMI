package foodnet.server;

import foodnet.data.Dish;
import foodnet.data.Order;
import foodnet.rmi.FoodService;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.List;

public class FoodServer extends UnicastRemoteObject implements FoodService {

    private static final List<Dish> menu = Arrays.asList(
        new Dish("Pizza", 12.5),
        new Dish("Burger", 9.0),
        new Dish("Salad", 5.0),
        new Dish("Sushi", 14.0)
    );

    protected FoodServer() throws RemoteException {
        super();
    }

    public static void main(String[] args) {
        try {
            // Запускаем реестр на порту 1099
            Registry registry = LocateRegistry.createRegistry(1099);
            FoodServer service = new FoodServer();
            registry.rebind("FoodService", service);
            System.out.println("Food Server is running on port 1099...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // --- Реализация новых методов ---

    @Override
    public List<Dish> getMenu() throws RemoteException {
        // RMI сам сериализует список и отправит его клиенту
        return menu;
    }

    @Override
    public double calculateSum(List<String> dishNames) throws RemoteException {
        double sum = 0;
        for (String name : dishNames) {
            for (Dish d : menu) {
                if (d.getName().equalsIgnoreCase(name.trim())) {
                    sum += d.getPrice();
                    break;
                }
            }
        }
        return sum;
    }

    @Override
    public void submitOrder(Order receivedOrder) throws RemoteException {
        double sum = 0;
        // Логика подсчета для отчета на сервере
        for (Dish clientDish : receivedOrder.getDishes()) {
            // Ищем цену в реальном меню (безопасность)
            for (Dish menuDish : menu) {
                if (menuDish.getName().equalsIgnoreCase(clientDish.getName())) {
                    sum += menuDish.getPrice();
                    break;
                }
            }
        }

        System.out.println("----------- New Order Received ------------");
        System.out.println("Address: " + receivedOrder.getAddress());
        System.out.println("Items:");
        for(Dish d : receivedOrder.getDishes()) {
             System.out.println(" - " + d.getName());
        }
        System.out.println("Total: " + String.format("%.2f", sum));
        System.out.println("-------------------------------------------");
    }
}