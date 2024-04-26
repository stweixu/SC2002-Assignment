package control.admin;

import java.util.Map;


import entity.Staff;
import entity.Role;
import entity.Branch;
import entity.Company;
import exception.AddStaffException;
import exception.BranchExistedException;
import exception.BranchNotExistException;
import exception.CloseBranchException;
import exception.PromotionException;
import exception.StaffExistedException;

/**
 * AdminController contains all the operations that can be done by
 * an Admin user. To adhere to SRP, it uses methods from 
 * ManpowerController and BranchOperationController
 */

public class AdminController {
	
	
	/**
	 * Uses ManPowerController class
	 * Display company staff list by their branch
	 */
	public static void displayStaffByBranch() {
		ManpowerController.displayStaffByBranch();
	}
	
	/**
	 * Uses ManPowerController class
	 * Display company staff list by their roles
	 */
	public static void displayStaffByRole() {
		ManpowerController.displayStaffByRole();
	}

	/**
	 * Uses ManPowerController class
	 * Display company staff list by their gender
	 */
	public static void displayStaffByGender() {
		ManpowerController.displayStaffByGender();
	}

	/**
	 * Uses ManPowerController class
	 * Display company staff list by their age in ascending order
	 */
	public static void displayStaffByAge() {
		ManpowerController.displayStaffByAge();
	}
	
	
	/**
	 * Transfer staff from one branch to another
	 * @param staff  Staff to be transferred
	 * @param branchKey Branch to be transferred into
	 */
    public static void transferStaff(Staff staff, String branchKey) throws StaffExistedException, AddStaffException {
    	
    	Staff tempStaff = new Staff(staff.getUserName(), staff.getUserId(), staff.getRole(), 
    								branchKey, staff.getAge(), staff.getGender());
    	tempStaff.setPassword(staff.getPassword());
    	
    	Branch branch = Company.getBranch().get(branchKey);
    	
    	deleteStaff(staff);
    	addStaff(tempStaff, branch);
    }

    /**
     * Uses ManpowerController methods to add new staff into database
     * @param staff New staff object to be added
     * @param branch Branch to be assigned into
     */
    public static void addStaff(Staff staff, Branch branch) throws StaffExistedException, AddStaffException{
        ManpowerController.addStaff(staff, branch);
    }

    /**
     * Uses ManpowerController to remove staff from database
     * @param staff Staff to be removed
     */
    public static void deleteStaff(Staff staff){
        ManpowerController.deleteStaff(staff);
    }

    /**
     * Edit the username of a given staff
     * @param staff Staff to be edited
     * @param name New username
     */
    public static void editStaffName(Staff staff, String name) throws BranchNotExistException, StaffExistedException, AddStaffException{
        Staff temStaff = staff;
        Branch temBranch = getBranch(staff.getBranchName());
        temStaff.setUserName(name);
        deleteStaff(staff);
        addStaff(temStaff, temBranch);
    }

    /**
     * Edit the login ID of a given staff
     * @param staff Staff to be edited
     * @param Id New login ID
     * @throws BranchNotExistException
     * @throws StaffExistedException
     * @throws AddStaffException
     */
    public static void editStaffId(Staff staff, String Id) throws BranchNotExistException, StaffExistedException, AddStaffException{
        Staff temStaff = staff;
        Branch temBranch = getBranch(staff.getBranchName());
        temStaff.setUserId(Id);
        deleteStaff(staff);
        addStaff(temStaff, temBranch);
    }

    /**
     * Edit the role of a given staff
     * @param staff Staff to be edited
     * @param role New role
     */
    public static void editStaffRole(Staff staff, Role role) throws BranchNotExistException, StaffExistedException, AddStaffException{
        Staff temStaff = staff;
        Branch temBranch = getBranch(staff.getBranchName());
        temStaff.setRole(role);
        deleteStaff(staff);
        addStaff(temStaff, temBranch);
    }

    /**
     * Edit the branch of a given staff by deleting the staff from
     * Original branch and adding into new Branch
     * @param staff Staff to be edited
     * @param branch New branch
     */
    public static void editStaffBranch(Staff staff, Branch branch) throws StaffExistedException, AddStaffException {
        deleteStaff(staff);
        addStaff(staff, branch);
        
    }

    /**
     * Promote a Staff to Manager
     * @param staff
     * @throws PromotionException
     * @throws BranchNotExistException
     * @throws StaffExistedException
     * @throws AddStaffException
     */
    public static void promoteStaff(Staff staff) throws PromotionException, BranchNotExistException, StaffExistedException, AddStaffException{

        Branch branch = null;

        for (Map.Entry<String,Branch> e : Company.getBranch().entrySet()){
            if (e.getKey().equals(staff.getBranchName())){
                branch = e.getValue();
            }
        }

        if (staff.getRole().equals(Role.STAFF) && (branch.getNumManager() < (branch.getNumStaff() + 3)/4)){ 
            editStaffRole(staff, Role.MANAGER);
        } else if (staff.getRole().equals(Role.MANAGER)) {
            throw new PromotionException("Manager cannot be promoted");
        } else throw new PromotionException("Manager Quota Full");
    }

    /**
     * Add a new branch object into database
     * @param branch New branch to be added
     */
    public static void openBranch(Branch branch) throws BranchExistedException{
        BranchOperationController.openBranch(branch);
    }

    /**
     * Delete an existing branch from database
     * @param branch Branch to be deleted
     * @throws CloseBranchException
     */
    public static void closeBranch(Branch branch) throws CloseBranchException{
        BranchOperationController.closeBranch(branch);
    }

    /**
     * Get the branch object with the branchName as the key
     * in HashMap<String branchName, Branch branch>
     * @param branchName Key in HashMap
     * @return Branch object
     */
    public static Branch getBranch(String branchName) throws BranchNotExistException{
        Branch branch = null;
        boolean found = false;
        
        for (Map.Entry<String,Branch> e : Company.getBranch().entrySet()){
            if (e.getKey().equals(branchName)){
                branch = e.getValue();
                found = true;
                break;
            }
        }
        if (!found) throw new BranchNotExistException();
        return branch;
    }

}
