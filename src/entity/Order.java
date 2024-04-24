package entity;

import java.util.ArrayList;

public class Order {
	private OrderStatus status;
	private ArrayList<OrderItem> orderItems;
	private long timeElapsed;
	private Boolean isTakeaway;
	private double totalPrice;
	
	public Order(boolean isTakeaway) {
		this.isTakeaway = isTakeaway;
		status = OrderStatus.PENDING;
		orderItems = new ArrayList<OrderItem>();
	}

	public OrderStatus getStatus() {
		return this.status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public ArrayList<OrderItem> getItems() {
		return orderItems;
	}
	
	public void setItems(ArrayList<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public long getTimeElapsed() {
		return this.timeElapsed;
	}

	public void setTimeElapsed(long timeElapsed) {
		this.timeElapsed = timeElapsed;
	}

	public Boolean getIsTakeaway() {
		return this.isTakeaway;
	}

	public void setIsTakeaway(Boolean isTakeaway) {
		this.isTakeaway = isTakeaway;
	}

	public double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
