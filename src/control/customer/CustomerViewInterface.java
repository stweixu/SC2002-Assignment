package control.customer;

public interface CustomerViewInterface {

	/**
     * Interface for customer to view order details
     */
	
	public void viewOrderStatus(String branchName, String orderID);
	public double viewOrderDetails(String branchName, String orderID);
}
