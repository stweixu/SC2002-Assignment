package control;

public interface OrderProcessingInterface {
     /**
     * An interface for Staff to update the status of the 
       processed order from a new order to be “Ready to pickup"
     * @param branchName, a specific branch in String.
     * @param id , id of an order in String.
     * @return void.
     */
	public void processOrder(String branchName, String id);
}
