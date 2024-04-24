package control.admin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import entity.Staff;
import entity.User;
import exception.AddStaffException;
import exception.StaffExistedException;
import entity.Role;
import entity.Admin;
import entity.Branch;
import entity.Company;
import control.ExcelRW;

public class ManpowerController {
	
	public static void displayStaffByBranch() {
		System.out.println("Filtered by Branch");
		
		System.out.printf("%-10s", "BRANCH");
		System.out.printf("%-19s", "USERNAME");
		System.out.printf("%-13s", "LOGIN ID");
		System.out.printf("%-10s", "ROLE");
		System.out.printf("%-8s", "GENDER");
		System.out.printf("%-7s %n", "AGE");

		for (Branch branch: Company.getBranch().values()) {			
			for (Staff staff: branch.getStaffList()) {
				System.out.printf("%-10s", staff.getBranchName());
				System.out.printf("%-19s", staff.getUserName());
				System.out.printf("%-13s", staff.getUserId());
				System.out.printf("%-10s", staff.getRole());
				System.out.printf("%-8s", staff.getGender());
				System.out.printf("%-7s %n", staff.getAge());
			}
			System.out.println("");
		}
	}
	
	public static void displayStaffByRole() {
		System.out.println("Filtered by Role");
		
		System.out.printf("%-10s", "ROLE");
		System.out.printf("%-19s", "USERNAME");
		System.out.printf("%-13s", "LOGIN ID");
		System.out.printf("%-10s", "BRANCH");		
		System.out.printf("%-8s", "GENDER");
		System.out.printf("%-7s %n", "AGE");

		
		for (Admin admin: Company.getAdmin()) {
			System.out.printf("%-10s", admin.getRole());
			System.out.printf("%-19s", admin.getUserName());
			System.out.printf("%-13s", admin.getUserId());
			System.out.printf("%-10s", "Company");		
			System.out.printf("%-8s", admin.getGender());
			System.out.printf("%-7s %n", admin.getAge());			
		}
		
		System.out.println("");
		
		for (Branch branch: Company.getBranch().values()) {
			
			for (Staff staff: branch.getStaffList()) {
				if (staff.getRole() == Role.STAFF) {
					System.out.printf("%-10s", staff.getRole());
					System.out.printf("%-19s", staff.getUserName());
					System.out.printf("%-13s", staff.getUserId());
					System.out.printf("%-10s", staff.getBranchName());
					System.out.printf("%-8s", staff.getGender());
					System.out.printf("%-7s %n", staff.getAge());
				}
			}
		}
		
		System.out.println("");
		
		for (Branch branch: Company.getBranch().values()) {
			
			for (Staff staff: branch.getStaffList()) {
				if (staff.getRole() == Role.MANAGER) {
					System.out.printf("%-10s", staff.getRole());
					System.out.printf("%-19s", staff.getUserName());
					System.out.printf("%-13s", staff.getUserId());
					System.out.printf("%-10s", staff.getBranchName());
					System.out.printf("%-8s", staff.getGender());
					System.out.printf("%-7s %n", staff.getAge());
				}
			}
		}
		
		System.out.println("");
	}
	
	public static void displayStaffByGender() {
		
		String[] genderArray = {"M", "F"};
		
		System.out.println("Filtered by Gender");
		
		System.out.printf("%-8s", "GENDER");
		System.out.printf("%-19s", "USERNAME");
		System.out.printf("%-13s", "LOGIN ID");
		System.out.printf("%-10s", "BRANCH");
		System.out.printf("%-10s", "ROLE");
		System.out.printf("%-7s %n", "AGE");
		
		for (String s: genderArray) {
			
			for (Admin admin: Company.getAdmin()) {
				if (admin.getGender().equals(s)) {
					System.out.printf("%-8s", admin.getGender());
					System.out.printf("%-19s", admin.getUserName());
					System.out.printf("%-13s", admin.getUserId());
					System.out.printf("%-10s", "Company");
					System.out.printf("%-10s", admin.getRole());
					System.out.printf("%-7s %n", admin.getAge());		
				}
			}
			
			for (Branch branch: Company.getBranch().values()) {
				
				for (Staff staff: branch.getStaffList()) {
					if (staff.getGender().equals(s)) {
						System.out.printf("%-8s", staff.getGender());
						System.out.printf("%-19s", staff.getUserName());
						System.out.printf("%-13s", staff.getUserId());
						System.out.printf("%-10s", staff.getBranchName());
						System.out.printf("%-10s", staff.getRole());
						System.out.printf("%-7s %n", staff.getAge());
					}
				}
			}
			System.out.println("");	
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void displayStaffByAge() {
		
		ArrayList<User> allStaffList = new ArrayList<>();
		
		for (Admin admin: Company.getAdmin()) {
			allStaffList.add(admin);
		}
		
		for (Branch branch: Company.getBranch().values()) {
			for (Staff staff: branch.getStaffList()) {
				allStaffList.add(staff);
			}
		}
			
		Collections.sort(allStaffList);
		
		System.out.println("Filtered by Age");
		
		System.out.printf("%-7s", "AGE");
		System.out.printf("%-19s", "USERNAME");
		System.out.printf("%-13s", "LOGIN ID");
		System.out.printf("%-10s", "BRANCH");
		System.out.printf("%-10s", "ROLE");
		System.out.printf("%-8s %n", "GENDER");

		for (User user: allStaffList) {
			if (user instanceof Admin) {
				Admin tempAdmin = (Admin) user;
				
				System.out.printf("%-7s", tempAdmin.getAge());	
				System.out.printf("%-19s", tempAdmin.getUserName());
				System.out.printf("%-13s", tempAdmin.getUserId());
				System.out.printf("%-10s", "Company");
				System.out.printf("%-10s", tempAdmin.getRole());
				System.out.printf("%-8s %n", tempAdmin.getGender());
			} else {
				Staff staff = (Staff) user;
				
				System.out.printf("%-7s", staff.getAge());
				System.out.printf("%-19s", staff.getUserName());
				System.out.printf("%-13s", staff.getUserId());
				System.out.printf("%-10s", staff.getBranchName());
				System.out.printf("%-10s", staff.getRole());
				System.out.printf("%-8s %n", staff.getGender());
			}
		}
			System.out.println("");
	}
    
    public static void addStaff(Staff staff, Branch branch) throws StaffExistedException, AddStaffException{
        ArrayList<Staff> temStaffs = branch.getStaffList();
        Role role = staff.getRole();

        for (int i=0; i<temStaffs.size(); i++){
            if (temStaffs.get(i).getUserId().equals(staff.getUserId())) throw new StaffExistedException();
        }

        int quota = 0;
        if (role.equals(Role.MANAGER)) {
            quota = (branch.getNumStaff() + 3) / 4;
            if (branch.getNumManager() >= quota) throw new AddStaffException("Manager limit reached");
        }
        else {
            quota = branch.getStaffQuota();
            if (branch.getNumStaff() >= quota) throw new AddStaffException("Staff limit reached");
        }

        temStaffs.add(staff);
        branch.setStaffList(temStaffs);

        int numStaff = branch.getNumStaff();
        int numManager = branch.getNumManager();

        if (role.equals(Role.MANAGER)){
            numManager += 1;
        } else numStaff += 1;

        branch.setNumManager(numManager);
        branch.setNumStaff(numStaff);

        //write to database, to be implemented
        ArrayList<Object[]> table = ExcelRW.readFile("data/default_staff_list.xlsx", 6);
        Object[] toWrite = new Object[6];

        toWrite[0] = staff.getUserName();
        toWrite[1] = staff.getUserId();
        toWrite[2] = (role == Role.STAFF)? "S" : "M";
        toWrite[3] = staff.getGender();
        toWrite[4] = (double) staff.getAge();
        toWrite[5] = branch.getBranchName();

        table.add(toWrite);

        boolean success = ExcelRW.writeFile(table, "data/default_staff_list.xlsx", 6);
        
        return;

    }

    public static void deleteStaff(Staff staff){

        String branchName = staff.getBranchName();
        Branch branch = Company.getBranch().get(branchName);
        ArrayList<Staff> temStaffs = branch.getStaffList();
        temStaffs.remove(staff);
        branch.setStaffList(temStaffs);

        int numStaff = branch.getNumStaff();
        int numManager = branch.getNumManager();

        if (staff.getRole().equals(Role.MANAGER)){
            numManager -= 1;
        } else numStaff -= 1;

        branch.setNumManager(numManager);
        branch.setNumStaff(numStaff);

        //write to database, to be implemented
        ArrayList<Object[]> table = ExcelRW.readFile("data/default_staff_list.xlsx", 6);

        Role role = staff.getRole();
        Object[] toDelete = new Object[6];

        toDelete[0] = staff.getUserName();
        toDelete[1] = staff.getUserId();
        toDelete[2] = (role == Role.STAFF)? "S" : "M";
        toDelete[3] = staff.getGender();
        toDelete[4] = (double) staff.getAge();
        toDelete[5] = branch.getBranchName(); 

        for (int i=0; i<table.size(); i++){
            if (table.get(i)[1].equals(toDelete[1])){
                table.remove(i);
                break;
            }
        }

        ExcelRW.writeFile(table, "data/default_staff_list.xlsx", 6);
    }

}
