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
  
  public static void addMenuItem(String branchName, String name, double price, int option) {
    try {
      Branch branch = Company.getBranch().get(branchName);
      if (branch==null) throw new BranchNotExistException();
      
      ArrayList<MenuItem> menu = branch.getMenuItems();
      if (menu==null)  throw new MenuException();
      
      for (MenuItem item : menu) {
        if (name.equals(item.getItemName()))  throw new MenuException("Menu item already exist!");
        break;
      }
      
      if (option<1 || option>MenuCategory.values().length) throw new ArrayIndexOutOfBoundsException("Invalid Menu Category!");
      	MenuItem item = new MenuItem(name, MenuCategory.values()[option-1], price);
          menu.add(item);
          branch.setMenuItems(menu);
          
          ArrayList<Object[]> table = ExcelRW.readFile(pathName, 4);
          Object[] toWrite = new Object[4];

          toWrite[0] = name;
          toWrite[1] = price;
          toWrite[2] = branchName;
          toWrite[3] = String.valueOf(MenuCategory.values()[option-1]);

          table.add(toWrite);

          ExcelRW.writeFile(table, pathName, 4);

    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
  
  public static void editMenuItemName(String branchName, int index, String name) {
	    try {
	      Branch branch = Company.getBranch().get(branchName);
	      if (branch==null) throw new BranchNotExistException();
	      
	      ArrayList<MenuItem> menu = branch.getMenuItems();
	      if (menu==null || menu.size()<0)  throw new MenuException();
	      
	      for (MenuItem item : menu) {
	        if (name==item.getItemName())  throw new MenuException("Menu name used!");
	      }
	      
	      if (index>menu.size())  throw new MenuException("Row number invalid!");
	      
	      MenuItem item = menu.get(index-1);
	          
	          ArrayList<Object[]> table = ExcelRW.readFile(pathName, 4);
	          Object[] toDelete = new Object[4];

	          toDelete[0] = item.getItemName();
	          toDelete[1] = item.getPrice();
	          toDelete[2] = branchName;
	          toDelete[3] = String.valueOf(item.getCategory());

	          for (int i=0; i<table.size(); i++){
	              if (table.get(i)[0].equals(toDelete[0]) && table.get(i)[2].equals(toDelete[2])){
	                table.remove(i);
	                  break;
	              }
	          }
	          
	          Object[] toWrite = new Object[4];

	          toWrite[0] = name;
	          toWrite[1] = toDelete[1];
	          toWrite[2] = branchName;
	          toWrite[3] = toDelete[3];

	          table.add(toWrite);

	          ExcelRW.writeFile(table, pathName, 4);
	          
	          item.setItemName(name);
	          menu.set(index-1, item);
	          branch.setMenuItems(menu);
	          
	    }
	        catch (Exception e) {
	      System.out.println(e.getMessage());
	    }
	  }

  
  public static void editMenuItemPrice(String branchName, int index, double price) {
	    try {
	      Branch branch = Company.getBranch().get(branchName);
	      if (branch==null) throw new BranchNotExistException();
	      
	      ArrayList<MenuItem> menu = branch.getMenuItems();
	      if (menu==null || menu.size()<0)  throw new MenuException();
	      
	      if (index>menu.size())  throw new MenuException("Row number invalid!");
	      
	      MenuItem item = menu.get(index-1);
	          
	          ArrayList<Object[]> table = ExcelRW.readFile(pathName, 4);
	          Object[] toDelete = new Object[4];

	          toDelete[0] = item.getItemName();
	          toDelete[1] = item.getPrice();
	          toDelete[2] = branchName;
	          toDelete[3] = String.valueOf(item.getCategory());

	          for (int i=0; i<table.size(); i++){
	              if (table.get(i)[0].equals(toDelete[0]) && table.get(i)[2].equals(toDelete[2])){
	                table.remove(i);
	                  break;
	              }
	          }
	          
	          Object[] toWrite = new Object[4];

	          toWrite[0] = toDelete[0];
	          toWrite[1] = price;
	          toWrite[2] = branchName;
	          toWrite[3] = toDelete[3];

	          table.add(toWrite);

	          ExcelRW.writeFile(table, pathName, 4);
	          
	          item.setPrice(price);
	          menu.set(index-1, item);
	          branch.setMenuItems(menu);
	    }
	        catch (Exception e) {
	      System.out.println(e.getMessage());
	    }
	  }


  public static void editMenuItemCategory(String branchName, int index, int option) {
	    try {
	      Branch branch = Company.getBranch().get(branchName);
	      if (branch==null) throw new BranchNotExistException();
	      
	      ArrayList<MenuItem> menu = branch.getMenuItems();
	      if (menu==null || menu.size()<0)  throw new MenuException();
	      
	      if (index>menu.size())  throw new MenuException("Row number invalid!");
	      if (option<1 || option>MenuCategory.values().length) throw new ArrayIndexOutOfBoundsException("Invalid Menu Category!");
	      MenuItem item = menu.get(index-1);
	      
	       ArrayList<Object[]> table = ExcelRW.readFile(pathName, 4);
	            Object[] toDelete = new Object[4];

	            toDelete[0] = item.getItemName();
	            toDelete[1] = item.getPrice();
	            toDelete[2] = branchName;
	            toDelete[3] = String.valueOf(item.getCategory());

	            for (int i=0; i<table.size(); i++){
	                if (table.get(i)[0].equals(toDelete[0]) && table.get(i)[2].equals(toDelete[2])){
	                  table.remove(i);
	                    break;
	                }
	            }
	            
	            Object[] toWrite = new Object[4];

	            toWrite[0] = toDelete[0];
	            toWrite[1] = toDelete[1];
	            toWrite[2] = branchName;
	            toWrite[3] = MenuCategory.values()[option-1];

	            table.add(toWrite);

	            ExcelRW.writeFile(table, pathName, 4);
	      
	      item.setCategory(MenuCategory.values()[option-1]);
	          menu.set(index-1, item);
	          branch.setMenuItems(menu);
	    }
	        catch (Exception e) {
	      System.out.println(e.getMessage());
	    }
	  }

  
  public static void removeMenuItem(String branchName, int index) {
	    try {
	      Branch branch = Company.getBranch().get(branchName);
	      if (branch==null) throw new BranchNotExistException();
	      
	      ArrayList<MenuItem> menu = branch.getMenuItems();
	      if (menu==null || menu.size()<0)  throw new MenuException();
	      
	      if (index>menu.size())  throw new MenuException("Row number invalid!");
	      
	      MenuItem item = menu.get(index-1);
	      
	      ArrayList<Object[]> table = ExcelRW.readFile(pathName, 4);
	  
	          Object[] toDelete = new Object[4];

	          toDelete[0] = item.getItemName();
	          toDelete[1] = item.getPrice();
	          toDelete[2] = branchName;
	          toDelete[3] = String.valueOf(item.getCategory());

	          for (int i=0; i<table.size(); i++){
	              if (table.get(i)[0].equals(toDelete[0]) && table.get(i)[2].equals(toDelete[2])){
	                  table.remove(i);
	                  break;
	              }
	          }

	          ExcelRW.writeFile(table, pathName, 4);
	          
	          menu.remove(index-1); 
	          branch.setMenuItems(menu);
	    }
	        catch (Exception e) {
	      System.out.println(e.getMessage());
	    }
	  }
  
}
