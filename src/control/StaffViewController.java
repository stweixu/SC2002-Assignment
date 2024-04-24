package control;

import java.util.ArrayList;

import entity.Branch;
import entity.Company;
import entity.Staff;

public class StaffViewController {
	public static void displayStaffList(String branchName) {
		
		Branch branch = Company.getBranch().get(branchName);
		ArrayList<Staff> staffList = branch.getStaffList();
		
		System.out.printf("%-15s\t%s\t%-10s\t%s\n", "Name", "Age", "Gender","Role");
		
		for (Staff staff : staffList) {
			System.out.printf("%-15s\t%d\t%-10s\t%s\n", staff.getUserName(), staff.getAge(), staff.getGender(), staff.getRole());
		}
	}
}
