package control.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import entity.Branch;
import entity.Company;
import exception.BranchExistedException;
import exception.BranchNotExistException;
import exception.CloseBranchException;
import control.ExcelRW;

/**
 * Controls the opening and closing of branches
 * by adding or removing from HashMap<String branchName, branch Branch>
 */

public class BranchOperationController {
    
	/**
	 * Add new branch object into HashMap that records all Branch
	 * @param new branch
	 * @throws BranchExistedException if duplicate name
	 */
	
    public static void openBranch(Branch branch) throws BranchExistedException {
        HashMap<String, Branch> temBranches = Company.getBranch();

        for (Map.Entry<String,Branch> e : temBranches.entrySet()){
            if (e.getKey().equals(branch.getBranchName())){
                throw new BranchExistedException();
            }
        }

        temBranches.put(branch.getBranchName(),branch);
        Company.setBranch(temBranches);

        ArrayList<Object[]> table = ExcelRW.readFile("data/default_branch_list.xlsx", 3);
        Object[] toWrite = new Object[3];

        toWrite[0] = branch.getBranchName();
        toWrite[1] = branch.getLocation();
        toWrite[2] = (double) branch.getStaffQuota();

        table.add(toWrite);

        ExcelRW.writeFile(table, "data/default_branch_list.xlsx",3);
    }

    
    /**
     * Remove branch from HashMap that records all Branch
     * @param branch to be closed
     * @throws CloseBranchException if there are staff yet to be removed/transferred
     */
    
    public static void closeBranch(Branch branch) throws CloseBranchException{
        
        HashMap<String, Branch> temBranches = Company.getBranch();
        
        for (Map.Entry<String,Branch> e : temBranches.entrySet()){
            if (e.getKey().equals(branch.getBranchName())){
            	Branch temBranch = e.getValue();
            	if (temBranch.getNumStaff() > 0 || temBranch.getNumManager() > 0) throw new CloseBranchException();
            	temBranches.remove(e.getKey());
                break;
            }
        }

        Company.setBranch(temBranches);

        ArrayList<Object[]> table = ExcelRW.readFile("data/default_branch_list.xlsx", 3);
        Object[] toWrite = new Object[3];

        
        toWrite[0] = branch.getBranchName();
        toWrite[1] = branch.getLocation();
        toWrite[2] = (double) branch.getStaffQuota();
        
        for (int i=0; i<table.size(); i++){
            if (table.get(i)[0].equals(toWrite[0])){
                table.remove(i);
                break;
            }
        }

        ExcelRW.writeFile(table, "data/default_branch_list.xlsx",3);
    }

}
