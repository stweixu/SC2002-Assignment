package control;

import entity.Company;

import java.util.Map;

import control.admin.AdminController;
import entity.Branch;
import entity.Staff;
import entity.Role;

public class TestControl {
    public static void main(String[] args){
        CompanyController.initialise();

//        System.out.println("Admin: ");
//        System.out.println(Company.getAdmin().get(0).getUserName());
//        System.out.println(" ");

        for (Map.Entry<String,Branch> e : Company.getBranch().entrySet()){
            Branch tem = e.getValue();
            System.out.println(tem.getBranchName());
            
            System.out.println("num of staff: "+tem.getNumStaff());
            System.out.println("num of mng: "+tem.getNumManager());
            System.out.println("mng quota: "+tem.getMgrQuota());
            System.out.println("staff quota: "+tem.getStaffQuota());
            

//            System.out.println("Staff: ");
//
//            for (int j=0; j < tem.getStaffList().size(); j++){
//               System.out.println(tem.getStaffList().get(j).getUserId());
//            }
//            
//            System.out.println("Menu: ");
//
//            for (int j=0; j < tem.getMenuItems().size(); j++){
//                System.out.println(tem.getMenuItems().get(j).getItemName());
//            }
            
            System.out.println(" ");
        }

        
        // Branch tem = new Branch("NUS", "NUS", 6);

        // AdminController.openBranch(tem);
        // for (Map.Entry<String,Branch> e : Company.getBranch().entrySet()){
        //     Branch tem1 = e.getValue();
        //     System.out.println(tem1.getBranchName());
        // }

        // AdminController.closeBranch(tem);
        // for (Map.Entry<String,Branch> e : Company.getBranch().entrySet()){
        //     Branch tem1 = e.getValue();
        //     System.out.println(tem1.getBranchName());
        // }

        // Branch temB = Company.getBranch().get("JP");
        // Staff temS = new Staff("khoa", "khoatrn", Role.MANAGER, temB.getBranchName(), 22, "M");
        // //AdminController.addStaff(temS, temB);

        // // for (int i=0; i<temB.getStaffList().size(); i++){
        // //     System.out.println(temB.getStaffList().get(i).getUserId());
        // // }

        // AdminController.deleteStaff(temS);
    }
}
