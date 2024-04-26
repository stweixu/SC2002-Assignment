package control;

public interface OrderProcessingInterface {
     /**
     * An interface for Staff to update the status of the 
       processed order from a new order to be “Ready to pickup"
     * @param branchName The specific branch where the order came from in String.
     * @param id The specific order's ID in String.
     * @return void.
     */
	public void processOrder(String branchName, String id);
}
