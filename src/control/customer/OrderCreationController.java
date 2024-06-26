package control.customer;

import java.util.ArrayList;
import java.util.HashMap;

import entity.Branch;
import entity.Company;
import entity.MenuItem;
import entity.Order;
import entity.OrderItem;
import entity.OrderStatus;
import exception.BranchNotExistException;
import exception.MenuException;
import exception.OrderException;

// Control
public class OrderCreationController{
  
  private static int id = 100;

	/**
     * createOrder() creates new order in a branch
     * @param branchName The branch to add the order in String
     * @param takeaway Whether customer chose takeaway option in boolean
     * @return orderID in String
     */
  public static String createOrder(String branchName, boolean takeaway) {
    try {
      Branch branch = Company.getBranch().get(branchName);
      if (branch==null) throw new BranchNotExistException();
      
      HashMap<String, Order> orderList = branch.getOrders();
      if (orderList==null)  throw new OrderException();
      
      Order order = new Order(takeaway);
      String orderID = String.valueOf(id++);
      orderList.put(orderID, order);
      branch.setOrders(orderList);
      return orderID;
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

	/**
     * addToOrder() add new item to the order
     * @param branchName The branch to add to the order in String
     * @param orderID The orderID to add to the order in String
     * @param index The index of item in the menu to add to the order in int
     * @param quantity The quantity of item to add to the order in int
     * @param remarks The remarks of item to add to the order in String
     * @return void
     */
  public static void addToOrder(String branchName, String orderID, int index, int quantity, String remarks) {
    
    try {          
      Branch branch = Company.getBranch().get(branchName);
      if (branch==null) throw new BranchNotExistException();
      
      HashMap<String, Order> orderList = branch.getOrders();
      if (orderList==null || orderList.size()<=0)  throw new OrderException();
      
      Order order = orderList.get(orderID);
      ArrayList<OrderItem> orderItems = order.getItems();
      
      ArrayList<MenuItem> menu = branch.getMenuItems();
      if (index>menu.size())  throw new MenuException("Row number invalid!");
      
      MenuItem menuItem = menu.get(index-1);
      
      OrderItem item = new OrderItem(menuItem, quantity, remarks);
      orderItems.add(item);
      order.setItems(orderItems);
      order.setTotalPrice(order.getTotalPrice()+(quantity*menuItem.getPrice()));
      
      orderList.put(orderID, order);
      branch.setOrders(orderList);
    
      System.out.println("Item added to order list!");
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
	
  /**
     * editOrder() edit item in the order
     * @param branchName The branch to edit the order in String
     * @param orderID The orderID to edit the order in String
     * @param index The index of item in the menu to edit to the order in int
     * @param quantity The quantity of item to edit to the order in int
     * @param remarks The remarks of item to edit to the order in String
     * @return void
     */
  public static void editOrder(String branchName, String orderID, int index, int quantity, String remarks) {
    try {
      Branch branch = Company.getBranch().get(branchName);
      if (branch==null) throw new BranchNotExistException();
      
      HashMap<String, Order> orderList = branch.getOrders();
      if (orderList==null || orderList.size()<=0)  throw new OrderException();
      
      Order order = orderList.get(orderID);
      orderList.remove(orderID);
      
      ArrayList<OrderItem> orderItems = order.getItems();
      if (index>orderItems.size())  throw new OrderException("Row number invalid!");
      
      OrderItem item = orderItems.get(index-1);
      
      order.setTotalPrice(order.getTotalPrice()-(item.getQuantity()*item.getItem().getPrice()));
      item.setQuantity(quantity);  
      item.setRemarks(remarks);
      orderItems.set(index-1, item);
      order.setTotalPrice(order.getTotalPrice()+(quantity*item.getItem().getPrice()));
      
      order.setItems(orderItems);
      
      orderList.put(orderID, order);
      branch.setOrders(orderList);
    
      System.out.println("Item edited in order list!");
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

	/**
     * deleteFromOrder() delete item to the order
     * @param branchName The branch to add to the order in String
     * @param orderID The orderID to add to the order in String
     * @param index The index of item in the menu to add to the order in int
     * @return void
     */
  public static void deleteFromOrder(String branchName, String orderID, int index){
	    try {
	      Branch branch = Company.getBranch().get(branchName);
	      if (branch==null) throw new BranchNotExistException();
	      
	      HashMap<String, Order> orderList = branch.getOrders();
	      if (orderList==null || orderList.size()<=0)  throw new OrderException();
	      
	      Order order = orderList.get(orderID);
	      orderList.remove(orderID);
	      
	      ArrayList<OrderItem> orderItems = order.getItems();
	      if (index>orderItems.size())  throw new OrderException("Row number invalid!");
	      
	      OrderItem item = orderItems.get(index-1);
	      
	      order.setTotalPrice(order.getTotalPrice()-(item.getQuantity()*item.getItem().getPrice()));
	      
	      orderItems.remove(index-1);
	      order.setItems(orderItems);
	      
	      orderList.put(orderID, order);
	      branch.setOrders(orderList);
	    
	      System.out.println("Item removed from order list!");

	    }
	    catch (Exception e) {
	      System.out.println(e.getMessage());
	    }
	  }
}
