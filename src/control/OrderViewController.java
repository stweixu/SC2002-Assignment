package control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import control.customer.CustomerViewInterface;
import entity.Branch;
import entity.Company;
import entity.Order;
import entity.OrderItem;
import exception.OrderException;

public class OrderViewController implements CustomerViewInterface, StaffViewInterface{
  public void viewOrderStatus(String branchName, String orderID) {
    try {
    	OrderStatusController.cancelOrder(branchName);
    	
      HashMap<String, Order> orderList = Company.getBranch().get(branchName).getOrders();
      if (orderList==null || orderList.size()<=0)  throw new OrderException();
      
      if (!orderList.containsKey(orderID))	throw new OrderException();
      Order order = orderList.get(orderID);
      System.out.println("Status: "+ order.getStatus());
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
  
  public double viewOrderDetails(String branchName, String orderID) {
    try {
    	OrderStatusController.cancelOrder(branchName);
    	
      HashMap<String, Order> orderList = Company.getBranch().get(branchName).getOrders();
      if (orderList==null || orderList.size()<=0)  throw new OrderException();
      
      if (!orderList.containsKey(orderID))	throw new OrderException();
      Order order = orderList.get(orderID); 
      
      ArrayList<OrderItem> orderItems = order.getItems();
      if (orderItems.size()<=0)  throw new OrderException("Cart Empty!");
      
      System.out.println("Order-ID: " + orderID);
      System.out.println("Status: " + order.getStatus());
      System.out.println("Takeaway: " + order.getIsTakeaway());
      
      System.out.printf("\t%-15s\t%s\t%s\n", "Name", "QTY", "Price");
      
      int i = 1;
      for (OrderItem item : orderItems) {
        String name = item.getItem().getItemName();
        int qty = item.getQuantity();
        double price = item.getItem().getPrice();
        System.out.printf("%d\t%-15s\t%d\t%.2f\n", i++, name, qty, qty*price);

      }
      
      double total = order.getTotalPrice();
      System.out.printf("Total: %.2f\n", total);
      
      return total;
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      return 0;
    }
  }
  
  public void viewNewOrders(String branchName) {
      try {
        HashMap<String, Order> orderList = Company.getBranch().get(branchName).getOrders();
        if (orderList==null || orderList.size()<=0)  throw new OrderException();
        
        for (Map.Entry<String, Order> e : orderList.entrySet()) {
          viewOrderDetails(branchName, e.getKey());
        }
        
      }
      catch (Exception e) {
    	  System.out.println(e.getMessage());
      }
    }

}
