package control;

import java.util.ArrayList;

import entity.Branch;
import entity.Company;
import entity.MenuItem;
import exception.BranchNotExistException;
import exception.MenuException;

public class MenuViewController {
	public static void displayMenu(String branchName) {

		try {
			Branch branch = Company.getBranch().get(branchName);
			if (branch==null) throw new BranchNotExistException();
			
			ArrayList<MenuItem> menu = branch.getMenuItems();
			if (menu==null || menu.size()<0)	throw new MenuException();

			System.out.printf("\t%-15s\t%s\t%s\n", "Name", "Price", "Category");
			
			int i = 1;
			for (MenuItem item : menu) {
				System.out.printf("%d\t%-15s\t%.2f\t%s\n", i++, item.getItemName(), item.getPrice(), item.getCategory());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
