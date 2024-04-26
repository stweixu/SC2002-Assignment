package control;

import java.util.HashMap;
import java.util.Map;

import control.customer.OrderCheckOutInterface;
import control.customer.OrderCollectionInterface;
import entity.Branch;
import entity.Company;
import entity.Order;
import entity.OrderStatus;
import exception.BranchNotExistException;
import exception.OrderException;

public class OrderStatusController implements OrderProcessingInterface, OrderCollectionInterface, OrderCheckOutInterface{
     /**
     * Only Staff can use processOrder() method to update the status of the 
       processed order from a new order to be “Ready to pickup"
     * @param branchName The specific branch where the order came from in String.
     * @param id The specific order's ID in String.
     * @return void.
     */
	
	public void processOrder(String branchName, String id) {
		try {
			Branch branch = Company.getBranch().get(branchName);
			if (branch==null) throw new BranchNotExistException();
			
			HashMap<String, Order> orderList = Company.getBranch().get(branchName).getOrders();
			if (orderList==null || orderList.size()<=0)	throw new OrderException();
			
			Order order = orderList.get(id);
			orderList.remove(id);
	
			// Process order
			if (order.getStatus()==OrderStatus.NEW) {
				order.setStatus(OrderStatus.READY_TO_PICKUP);
				System.out.println("Order processed!");
			}

			orderList.put(id, order);
			branch.setOrders(orderList);
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
     /**
     * Orders are automatically canceled and removed from the " ready to pickup" 
       list if not picked up within a 100s timeframe
     * @param branchName The specific branch where the order came from in String.
     * @return void.
     */
	public static void cancelOrder(String branchName) {
		try {
			Branch branch = Company.getBranch().get(branchName);
			if (branch==null) throw new BranchNotExistException();
			
			HashMap<String, Order> orderList = Company.getBranch().get(branchName).getOrders();
			if (orderList==null || orderList.size()<=0)	throw new OrderException();
			
			for (Map.Entry<String, Order> e : orderList.entrySet()) {
				Order order = e.getValue();
				String key = e.getKey();
			
				if ((order.getStatus()==OrderStatus.READY_TO_PICKUP)&&(System.currentTimeMillis()-order.getTimeElapsed() > 100000)) {
					orderList.remove(key);
					order.setStatus(OrderStatus.CANCELLED);
					orderList.put(key, order);
				}
			}
			
			branch.setOrders(orderList);
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	
     /**
     * Customer can collect food after the order status becomes “ready to 
        pickup by using the collectOrder() method to change the status to "Completed"
     * @param branchName The specific branch where the order came from in String.
     * @param id The specific order's ID in String.
     * @return void.
     */
	public void collectOrder(String branchName, String id){
		try {
			OrderStatusController.cancelOrder(branchName);
			
			Branch branch = Company.getBranch().get(branchName);
			if (branch==null) throw new BranchNotExistException();
			
			HashMap<String, Order> orderList = branch.getOrders();
			if (orderList==null || orderList.size()<=0)	throw new OrderException();
			
			Order order = orderList.get(id);
			orderList.remove(id);
			
			if (order.getStatus()==OrderStatus.READY_TO_PICKUP) {
				order.setStatus(OrderStatus.COMPLETED);
				System.out.println("Order collected!");
			}
				
			else if (order.getStatus()==OrderStatus.NEW)
				System.out.println("Order not ready for collection!");
			else if (order.getStatus()==OrderStatus.CANCELLED)
				System.out.println("Order Cancelled!");

			orderList.put(id, order);
			branch.setOrders(orderList);
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
     /**
     * Customer can checkout their cart after finishing order by using the checkOut() method
     * @param branchName The specific branch where the order came from in String.
     * @param id The specific order's ID in String.
     * @param paid Return true if customer has made payment, otherwise return false 
     * @return void.
     */
	
	public void checkOut(String branchName, String id, boolean paid){
		
		try {
			Branch branch = Company.getBranch().get(branchName);
			if (branch==null) throw new BranchNotExistException();
			
			HashMap<String, Order> orderList = branch.getOrders();
			if (orderList==null || orderList.size()<=0)	throw new OrderException();
			
			Order order = orderList.get(id);			
			orderList.remove(id);
			if (paid) {
				order.setStatus(OrderStatus.NEW);
				order.setTimeElapsed(System.currentTimeMillis());
				System.out.println("Order sent to kitchen!");
			}
			else
				order.setStatus(OrderStatus.CANCELLED);
			
			orderList.put(id, order);
			branch.setOrders(orderList);
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
	}	
}
