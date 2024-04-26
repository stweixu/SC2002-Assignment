package control;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

import entity.Branch;
import entity.Admin;
import entity.Company;
import entity.Staff;
import entity.Role;
import entity.MenuItem;
import entity.MenuCategory;

/**
 * CompanyController read data from excel file and 
 * instantiates a Company object during run-time 
 * by calling CompanyController.initialise
 */

public class CompanyController {
    /**
     * loadBranch() Loads the list of branches in the excel sheet into the company's databse
     * @return void.
     */

    private static void loadBranch(){
        // read data from branch_list.xlsx
        ArrayList<Object[]> branchRead = ExcelRW.readFile("data/default_branch_list.xlsx", 3);
        HashMap<String,Branch> branches = new HashMap<String,Branch>();

        // iterate through branch_list and add new branch to the database (Company)
        for (int i = 1; i < branchRead.size(); i++){
            Branch tem = new Branch( (String) branchRead.get(i)[0], (String) branchRead.get(i)[1], (int) Math.round((Double)branchRead.get(i)[2]));
            branches.put(tem.getBranchName(),tem);
        }
        // set added branch to the database (Company)
        Company.setBranch(branches);
    }

    /**
     * loadStaff() Loads the list of staffs in the excel sheet into the company's branch databse
     * @return void.
     */
    private static void loadStaff(){
        // read data from staff_list.xlsx
        ArrayList<Object[]> staffRead = ExcelRW.readFile("data/default_staff_list.xlsx", 6);

        // iterate through staff_list and add new staff/admin to the database (Company)
        for (int i = 1; i < staffRead.size(); i++){
            if (staffRead.get(i)[2].equals("A")){
                ArrayList<Admin> tem = Company.getAdmin();
                Admin temAdmin = new Admin( (String) staffRead.get(i)[0], (String) staffRead.get(i)[1], (int) Math.round((Double)staffRead.get(i)[4]), (String) staffRead.get(i)[3]);
                tem.add(temAdmin);
                Company.setAdmins(tem);
            
            } else {

                Role role;
                if (staffRead.get(i)[2].equals("S")) role = Role.STAFF;
                else role = Role.MANAGER;

                Branch temBranch;
                HashMap<String,Branch> temBranches = Company.getBranch();

                // iterate through staff_list and add new staff members to the database (Branch)
                for (Map.Entry<String,Branch> e : temBranches.entrySet()){
                    if (e.getKey().equals(staffRead.get(i)[5])){
                        temBranch = e.getValue();
                        Staff temStaff = new Staff((String)staffRead.get(i)[0], (String)staffRead.get(i)[1], role, temBranch.getBranchName(), (int) Math.round((Double)staffRead.get(i)[4]), (String) staffRead.get(i)[3]);
                        ArrayList<Staff> temStaffs = temBranch.getStaffList();
                        temStaffs.add(temStaff);
                        temBranch.setStaffList(temStaffs);
                        
                        if (role.equals(Role.STAFF))temBranch.setNumStaff(temBranch.getNumStaff() + 1);
                        else temBranch.setNumManager(temBranch.getNumManager() + 1);
                        
                        temBranches.put(temBranch.getBranchName(), temBranch);
                        Company.setBranch(temBranches);
                        break;
                    }
                }
            }
        }
    }

     /**
     * loadMenu() Loads the list of menu items in the excel sheet into the company's branch databse
     * @return void.
     */
    private static void loadMenu(){
        // read data from menu_list.xlsx
        ArrayList<Object[]> menuRead = ExcelRW.readFile("data/default_menu_list.xlsx", 4);

        // iterate through menu_list and add new items to the database
        for (int i = 1; i < menuRead.size(); i++){

            MenuCategory Category = MenuCategory.BURGER;

            switch ((String) menuRead.get(i)[3]) {
                case "side":
                    Category = MenuCategory.SIDE;
                    break;

                case "set meal":
                    Category = MenuCategory.SET_MEAL;
                    break;

                case "burger":
                    Category = MenuCategory.BURGER;
                    break;
                
                case "drink":
                    Category = MenuCategory.DRINK;
                    break;
                
                default: 
                    break;
            }

            MenuItem temItem = new MenuItem((String) menuRead.get(i)[0], Category, (Double) menuRead.get(i)[1]);

            Branch temBranch;
            HashMap<String,Branch> temBranches = Company.getBranch();

            // iterate through menu_list and add new menu items to the database (Branch)
            for (Map.Entry<String,Branch> e : temBranches.entrySet()){
                if (e.getKey().equals(menuRead.get(i)[2])){
                    temBranch = e.getValue();
                    ArrayList<MenuItem> temItems = temBranch.getMenuItems();
                    temItems.add(temItem);
                    temBranch.setMenuItems(temItems);
                    temBranches.put(temBranch.getBranchName(), temBranch);
                    Company.setBranch(temBranches);
                    break;
                }
            }
        }
    }

     /**
     * initialise() Loads the excel files containing the list of branches, staffs, and menu items into the company's database
     * @return void.
     */
    public static void initialise(){
        loadBranch();    
        loadStaff();
        loadMenu();
    }
    
/**
     * displayBranch() Displays the list of branches in the company's database
     * @return void.
     */
    public static void displayBranch() {
    	
    	HashMap<String, Branch> branchList = Company.getBranch();
    	
    	System.out.println("\n----Branch----");
    	for (String branchKey : branchList.keySet()) {
    		System.out.println(branchKey);
    	}
    	
    	System.out.print("---------------");
    }
}
