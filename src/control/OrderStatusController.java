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
