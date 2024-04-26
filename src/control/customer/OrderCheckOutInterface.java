package control.customer;

public interface OrderCheckOutInterface {

	/**
     * Interface for customer to checkout order
     * @param branchName The branch to checkout the order in String
     * @param id The orderID to checkout the order in String
     * @param send Specifies to checkout as READY_TO_PICKUP or CANCELLED
     * @return void
     */
	public void checkOut(String branchName, String id, boolean send);
}
