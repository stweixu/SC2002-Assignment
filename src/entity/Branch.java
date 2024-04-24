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
  
  public Branch(String branchName, String location) {
        this.branchName = branchName;
        this.location = location;
        this.staffQuota = 4;
        this.mgrQuota = 1;
        this.staffList = new ArrayList<>();
        this.menuItems = new ArrayList<>();
        this.orders = new HashMap<String, Order>();
    }
  
  public Branch(String branchName, String location, int staffQuota) {
        this.branchName = branchName;
        this.location = location;
        this.staffQuota = staffQuota;
        this.mgrQuota = (staffQuota + 3) / 4;
        this.staffList = new ArrayList<>();
        this.menuItems = new ArrayList<>();
        this.orders = new HashMap<String, Order>();
    }

    public ArrayList<Staff> getStaffList() {
    return staffList;
  }

  public void setStaffList(ArrayList<Staff> staffList) {
    this.staffList = staffList;
  }

  public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getStaffQuota() {
        return staffQuota;
    }

    public void setStaffQuota(int staffQuota) {
        this.staffQuota = staffQuota;
    }

    public int getMgrQuota() {
        return mgrQuota;
    }

    public void setMgrQuota(int mgrQuota) {
        this.mgrQuota = mgrQuota;
    }
 
    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(ArrayList<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public HashMap<String, Order> getOrders() {
        return orders;
    }

    public void setOrders(HashMap<String, Order> orders) {
        this.orders = orders;
    }
    
    public int getNumStaff() {
        return this.numStaff;
    }

    public void setNumStaff(int numStaff) {
        this.numStaff = numStaff;
    }

    public int getNumManager() {
        return this.numManager;
    }

    public void setNumManager(int numManager) {
        this.numManager = numManager;
    }
    
}
