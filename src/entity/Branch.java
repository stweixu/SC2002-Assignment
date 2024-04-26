package entity;

import java.util.ArrayList;
import java.util.HashMap;

public class Branch {
  private String branchName;
  private String location;
  private int staffQuota;
  private int mgrQuota;
  private int numStaff = 0;
    private int numManager = 0;
  private ArrayList<Staff> staffList;
  private ArrayList<MenuItem> menuItems;
  private HashMap<String, Order> orders;
  

  /**
     * Branch contructor 
     * @param branchName Name of Branch.
     * @param location location of Branch.
     */
  public Branch(String branchName, String location) {
        this.branchName = branchName;
        this.location = location;
        this.staffQuota = 4;
        this.mgrQuota = 1;
        this.staffList = new ArrayList<>();
        this.menuItems = new ArrayList<>();
        this.orders = new HashMap<String, Order>();
    }
  /**
     * Branch contructor 
     * @param branchName Name of Branch.
     * @param location location of Branch.
     * @param staffQuota staff quota of Branch.
     */
  public Branch(String branchName, String location, int staffQuota) {
        this.branchName = branchName;
        this.location = location;
        this.staffQuota = staffQuota;
        this.mgrQuota = (staffQuota + 3) / 4;
        this.staffList = new ArrayList<>();
        this.menuItems = new ArrayList<>();
        this.orders = new HashMap<String, Order>();
    }

    /**
     * To get staff's list
     * @return the staff's list.
     */
    public ArrayList<Staff> getStaffList() {
    return staffList;
  }

    /**
     * To set staff's list
     * @param staffList The new staff's list to set
     * @return void.
     */
  public void setStaffList(ArrayList<Staff> staffList) {
    this.staffList = staffList;
  }

    /**
     * To get Branch's name
     * @return the Branch's name.
     */
  public String getBranchName() {
        return branchName;
    }

    /**
     * To set Branch's name
     * @param branchName The new Branch's name to set
     * @return void.
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    /**
     * To get the branch's location
     * @return the location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * To set Branch's location
     * @param location The new Branch's location to set
     * @return void.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * To get Branch's staff quota
     * @return the staff quota.
     */
    public int getStaffQuota() {
        return staffQuota;
    }

    /**
     * To set staff quota
     * @param staffQuota The new Branch's staff quota to set
     * @return void.
     */
    public void setStaffQuota(int staffQuota) {
        this.staffQuota = staffQuota;
    }

    /**
     * To get manager quota
     * @return the manager quota.
     */
    public int getMgrQuota() {
        return mgrQuota;
    }

    /**
     * To set staff quota
     * @param mgrQuota The new Branch's manager quota to set
     * @return void.
     */
    public void setMgrQuota(int mgrQuota) {
        this.mgrQuota = mgrQuota;
    }
 
    /**
     * To get menu
     * @return the Branch's menu.
     */
    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }

    /**
     * To set menu
     * @param menuItems The new Branch's menu to set
     * @return void.
     */
    public void setMenuItems(ArrayList<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    /**
     * To get the Branch's orders
     * @return the orders.
     */
    public HashMap<String, Order> getOrders() {
        return orders;
    }

    /**
     * To set orders
     * @param orders The new Branch's order to set
     * @return void.
     */
    public void setOrders(HashMap<String, Order> orders) {
        this.orders = orders;
    }
    
    /**
     * To get the number of staff
     * @return the number of staff.
     */
    public int getNumStaff() {
        return this.numStaff;
    }

    /**
     * To set the number of staff
     * @param numStaff The new Branch's menu to set
     * @return void.
     */
    public void setNumStaff(int numStaff) {
        this.numStaff = numStaff;
    }

    /**
     * To get number of managers
     * @return the number of managers.
     */
    public int getNumManager() {
        return this.numManager;
    }

    /**
     * To set the number of managers
     * @param numManager The new Branch's menu to set
     * @return void.
     */
    public void setNumManager(int numManager) {
        this.numManager = numManager;
    }
    
}
