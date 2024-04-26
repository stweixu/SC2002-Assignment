package control;

import java.util.ArrayList;


import entity.Branch;
import entity.Company;
import entity.MenuCategory;
import entity.MenuItem;
import entity.Staff;
import exception.BranchNotExistException;
import exception.MenuException;

// Control
public class MenuEditController {
  private static final String pathName = "data/default_menu_list.xlsx";

// add menu item to branch
  public static void addMenuItem(String branchName, String name, double price, int option) {
    try {
      Branch branch = Company.getBranch().get(branchName);	// retrieve the branch
      if (branch==null) throw new BranchNotExistException();	// if branch does not exist (i.e. due to wrong name), throw exception
      
      ArrayList<MenuItem> menu = branch.getMenuItems();		// retrieve menu items from the branch
      if (menu==null)  throw new MenuException();		// if menu array does not exist, throw exception. Possible when creating branch during runtime.

	    // Check if name is used in branch's menu. If yes, throw exception
      for (MenuItem item : menu) {
        if (name.equals(item.getItemName()))  throw new MenuException("Menu item already exist!");
        break;
      }
      
      if (option<1 || option>MenuCategory.values().length) throw new ArrayIndexOutOfBoundsException("Invalid Menu Category!");	// If menu category index out of range, throw exception
      	MenuItem item = new MenuItem(name, MenuCategory.values()[option-1], price);	// create a new MenuItem
          menu.add(item);	// add item to branch's menu
          branch.setMenuItems(menu); // update the branch's menu

	    // Update excel file
          ArrayList<Object[]> table = ExcelRW.readFile(pathName, 4);

	    // Create object for new item
          Object[] toWrite = new Object[4];

          toWrite[0] = name;
          toWrite[1] = price;
          toWrite[2] = branchName;
          toWrite[3] = String.valueOf(MenuCategory.values()[option-1]);

          table.add(toWrite);	// add the object as a new entry

          ExcelRW.writeFile(table, pathName, 4);	// update the excel table

    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

	// edit branch menu item by name
  public static void editMenuItemName(String branchName, int index, String name) {
	    try {
	      Branch branch = Company.getBranch().get(branchName);	// retrieve the branch
	      if (branch==null) throw new BranchNotExistException();	// if branch does not exist (i.e. due to wrong name), throw exception
	      
	      ArrayList<MenuItem> menu = branch.getMenuItems();		// retrieve menu items from the branch
	      if (menu==null || menu.size()<0)  throw new MenuException();	// if menu array does not exist or has no items, throw exception. Possible when creating branch during runtime.

		// Check if name is used in branch's menu. If yes, throw exception
	      for (MenuItem item : menu) {
	        if (name==item.getItemName())  throw new MenuException("Menu name used!");
	      }
	      
	      if (index<0 || index>menu.size())  throw new MenuException("Row number invalid!");	
	      
	      MenuItem item = menu.get(index-1);	// retrieve the menu item

		    // Update excel file
	          ArrayList<Object[]> table = ExcelRW.readFile(pathName, 4);	// Read the excel into a table
	          Object[] toDelete = new Object[4];

	          toDelete[0] = item.getItemName();
	          toDelete[1] = item.getPrice();
	          toDelete[2] = branchName;
	          toDelete[3] = String.valueOf(item.getCategory());

		    // delete the existing entry
	          for (int i=0; i<table.size(); i++){
	              if (table.get(i)[0].equals(toDelete[0]) && table.get(i)[2].equals(toDelete[2])){
	                table.remove(i);
	                  break;
	              }
	          }

		    // Create new object for the edit
	          Object[] toWrite = new Object[4];

	          toWrite[0] = name;
	          toWrite[1] = toDelete[1];
	          toWrite[2] = branchName;
	          toWrite[3] = toDelete[3];

		    
	          table.add(toWrite);	// add the edit as a new entry
	
	          ExcelRW.writeFile(table, pathName, 4);	// update the excel table
	          
	          item.setItemName(name);	// modify the item's name
	          menu.set(index-1, item);	// modify the item in the menu
	          branch.setMenuItems(menu);	// update the branch's menu

	          
	    }
	        catch (Exception e) {
	      System.out.println(e.getMessage());
	    }
	  }

  // edit branch menu item by price
  public static void editMenuItemPrice(String branchName, int index, double price) {
	    try {
	      Branch branch = Company.getBranch().get(branchName);	// retrieve the branch
	      if (branch==null) throw new BranchNotExistException();	// if branch does not exist (i.e. due to wrong name), throw exception
	      		
	      ArrayList<MenuItem> menu = branch.getMenuItems();		// retrieve menu items from the branch
	      if (menu==null || menu.size()<0)  throw new MenuException();	// if menu array does not exist or has no items, throw exception. Possible when creating branch during runtime.
	      
	      if (index>menu.size())  throw new MenuException("Row number invalid!");	// if attempt to access menu item outside index range, throw exception
	      
	      MenuItem item = menu.get(index-1);	// else retrieve that menu item

		    // Update excel file
	          ArrayList<Object[]> table = ExcelRW.readFile(pathName, 4);	// Read excel file into a table
	          Object[] toDelete = new Object[4];

	          toDelete[0] = item.getItemName();
	          toDelete[1] = item.getPrice();
	          toDelete[2] = branchName;
	          toDelete[3] = String.valueOf(item.getCategory());

		    // Delete the existing entry
	          for (int i=0; i<table.size(); i++){
	              if (table.get(i)[0].equals(toDelete[0]) && table.get(i)[2].equals(toDelete[2])){
	                table.remove(i);
	                  break;
	              }
	          }
		    
	            // Create new object for the edit
	          Object[] toWrite = new Object[4];

	          toWrite[0] = toDelete[0];
	          toWrite[1] = price;
	          toWrite[2] = branchName;
	          toWrite[3] = toDelete[3];

	          table.add(toWrite);	// add the edit as a new entry

	          ExcelRW.writeFile(table, pathName, 4);	// Write the updated table back to excel to ensure data persistence
	          
	          item.setPrice(price);		// modify item price
	          menu.set(index-1, item);	// modify that item in the menu
	          branch.setMenuItems(menu);	// update the branch's menu
	    }
	        catch (Exception e) {
	      System.out.println(e.getMessage());
	    }
	  }

// edit branch menu item by category
  public static void editMenuItemCategory(String branchName, int index, int option) {
	    try {
	      Branch branch = Company.getBranch().get(branchName);	// retrieve the branch
	      if (branch==null) throw new BranchNotExistException();	// if branch does not exist (i.e. due to wrong name), throw exception
	      
	      ArrayList<MenuItem> menu = branch.getMenuItems();		// retrieve menu items from the branch
	      if (menu==null || menu.size()<0)  throw new MenuException();	// if menu array does not exist or has no items, throw exception. Possible when creating branch during runtime.
	      
	      if (index<0 || index>menu.size())  throw new MenuException("Row number invalid!");	// if attempt to access menu item outside index range, throw exception
	      if (option<1 || option>MenuCategory.values().length) throw new ArrayIndexOutOfBoundsException("Invalid Menu Category!");		// If menu category index out of range, throw exception
	      MenuItem item = menu.get(index-1);	// else retrieve that menu item

		    // Update excel file
	       ArrayList<Object[]> table = ExcelRW.readFile(pathName, 4);
	            Object[] toDelete = new Object[4];

	            toDelete[0] = item.getItemName();
	            toDelete[1] = item.getPrice();
	            toDelete[2] = branchName;
	            toDelete[3] = String.valueOf(item.getCategory());

		    // Remove the existing entry
	            for (int i=0; i<table.size(); i++){
	                if (table.get(i)[0].equals(toDelete[0]) && table.get(i)[2].equals(toDelete[2])){
	                  table.remove(i);
	                    break;
	                }
	            }

		      // Create new object for the edit
	            Object[] toWrite = new Object[4];

	            toWrite[0] = toDelete[0];
	            toWrite[1] = toDelete[1];
	            toWrite[2] = branchName;
	            toWrite[3] = MenuCategory.values()[option-1];

	            table.add(toWrite);	// add the edit as a new entry

	            ExcelRW.writeFile(table, pathName, 4);	// Write the updated table back to excel to ensure data persistence
	      
	     	 item.setCategory(MenuCategory.values()[option-1]);	// modify the menu category
	          menu.set(index-1, item);	// modify the item in the menu
	          branch.setMenuItems(menu);	// update the branch's menu
	    }
	        catch (Exception e) {
	      System.out.println(e.getMessage());
	    }
	  }

  // remove menu item from branch
  public static void removeMenuItem(String branchName, int index) {
	    try {
	      Branch branch = Company.getBranch().get(branchName);	// retrieve the branch
	      if (branch==null) throw new BranchNotExistException();	// if branch does not exist (i.e. due to wrong name), throw exception
	      
	      ArrayList<MenuItem> menu = branch.getMenuItems();		// retrieve menu items from the branch
	      if (menu==null || menu.size()<0)  throw new MenuException();	// if menu array does not exist or has no items, throw exception. Possible when creating branch during runtime.
	      
	      if (index<0 || index>menu.size())  throw new MenuException("Row number invalid!");	// if attempt to access menu item outside index range, throw exception
	      
	      MenuItem item = menu.get(index-1);	// else retrieve that menu item

		// Update to excel file
	      ArrayList<Object[]> table = ExcelRW.readFile(pathName, 4);	// Read excel file into a table

		      // Create new object for the edit
	          Object[] toDelete = new Object[4];

	          toDelete[0] = item.getItemName();
	          toDelete[1] = item.getPrice();
	          toDelete[2] = branchName;
	          toDelete[3] = String.valueOf(item.getCategory());

		    // Delete the entry
	          for (int i=0; i<table.size(); i++){
	              if (table.get(i)[0].equals(toDelete[0]) && table.get(i)[2].equals(toDelete[2])){
	                  table.remove(i);
	                  break;
	              }
	          }
				
	          ExcelRW.writeFile(table, pathName, 4);	// Write the updated table back to excel to ensure data persistence

	          menu.remove(index-1); 	// remove the actual menu item from the branch
	          branch.setMenuItems(menu);	// update the menu back to the branch
	    }
	        catch (Exception e) {
	      System.out.println(e.getMessage());
	    }
	  }
  
}
