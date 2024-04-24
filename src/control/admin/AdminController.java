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

public class AdminController {
	
	public static void displayStaffByBranch() {
		ManpowerController.displayStaffByBranch();
	}

	public static void displayStaffByRole() {
		ManpowerController.displayStaffByRole();
	}

	public static void displayStaffByGender() {
		ManpowerController.displayStaffByGender();
	}

	public static void displayStaffByAge() {
		ManpowerController.displayStaffByAge();
	}
	
    public static void transferStaff(Staff staff, String branchKey) throws StaffExistedException, AddStaffException {
    	
    	Staff tempStaff = new Staff(staff.getUserName(), staff.getUserId(), staff.getRole(), 
    								branchKey, staff.getAge(), staff.getGender());
    	tempStaff.setPassword(staff.getPassword());
    	
    	Branch branch = Company.getBranch().get(branchKey);
    	
    	deleteStaff(staff);
    	addStaff(tempStaff, branch);
    }
    
    public static void assignStaff(Staff staff, Role role, Branch branch) throws StaffExistedException, AddStaffException{
        staff.setRole(role);
        staff.setBranchName(branch.getBranchName());
        addStaff(staff, branch);
    }

    public static void addStaff(Staff staff, Branch branch) throws StaffExistedException, AddStaffException{
        ManpowerController.addStaff(staff, branch);
    }

    public static void deleteStaff(Staff staff){
        ManpowerController.deleteStaff(staff);
    }

    public static void editStaffName(Staff staff, String name) throws BranchNotExistException, StaffExistedException, AddStaffException{
        Staff temStaff = staff;
        Branch temBranch = getBranch(staff.getBranchName());
        temStaff.setUserName(name);
        deleteStaff(staff);
        addStaff(temStaff, temBranch);
    }

    public static void editStaffId(Staff staff, String Id) throws BranchNotExistException, StaffExistedException, AddStaffException{
        Staff temStaff = staff;
        Branch temBranch = getBranch(staff.getBranchName());
        temStaff.setUserId(Id);
        deleteStaff(staff);
        addStaff(temStaff, temBranch);
    }

    public static void editStaffRole(Staff staff, Role role) throws BranchNotExistException, StaffExistedException, AddStaffException{
        Staff temStaff = staff;
        Branch temBranch = getBranch(staff.getBranchName());
        temStaff.setRole(role);
        deleteStaff(staff);
        addStaff(temStaff, temBranch);
    }

    public static void editStaffBranch(Staff staff, Branch branch) throws StaffExistedException, AddStaffException {
        deleteStaff(staff);
        addStaff(staff, branch);
        
    }

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

    public static void openBranch(Branch branch) throws BranchExistedException{
        BranchOperationController.openBranch(branch);
    }

    public static void closeBranch(Branch branch) throws CloseBranchException{
        BranchOperationController.closeBranch(branch);
    }

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
