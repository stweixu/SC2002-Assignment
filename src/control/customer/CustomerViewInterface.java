package control.customer;

public interface CustomerViewInterface {
	public void viewOrderStatus(String branchName, String orderID);
	public double viewOrderDetails(String branchName, String orderID);
}
