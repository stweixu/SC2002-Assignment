package control;

import java.util.ArrayList;

import entity.Branch;
import entity.Company;
import entity.Staff;

public class StaffViewController {
	public static void displayStaffList(String branchName) {
		
		Branch branch = Company.getBranch().get(branchName);	// retrieve the branch from the company
		ArrayList<Staff> staffList = branch.getStaffList();	// retrieve the staff list from the branch
		
		System.out.printf("%-15s\t%s\t%-10s\t%s\n", "Name", "Age", "Gender","Role");

		// Iterate and print the staff list
		for (Staff staff : staffList) {
			System.out.printf("%-15s\t%d\t%-10s\t%s\n", staff.getUserName(), staff.getAge(), staff.getGender(), staff.getRole());
		}
	}
}
