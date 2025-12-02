package foodnet.rmi;

import foodnet.data.Dish;
import foodnet.data.Order;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface FoodService extends Remote {
    
    // Клиент ждет List<Dish>, а не String
    List<Dish> getMenu() throws RemoteException;

    // Клиент вызывает calculateSum и передает List<String>
    double calculateSum(List<String> dishNames) throws RemoteException;

    // Клиент вызывает submitOrder
    void submitOrder(Order order) throws RemoteException;
}