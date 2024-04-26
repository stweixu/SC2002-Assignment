package control;

public interface StaffViewInterface {
 /**
     * An interface for Staff to view new orders to be processed under his/her branch
     * @param branchName The specific branch where the order came from in String.
     * @return void.
     */
  
  public void viewNewOrders(String branchName);
}

