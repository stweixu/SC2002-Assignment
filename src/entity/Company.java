package entity;

import java.util.ArrayList;
import java.util.HashMap;

public class Company {
	private static HashMap<String, Branch> branches = new HashMap<>();
	private static ArrayList<Admin> admins = new ArrayList<>();

    public static HashMap<String, Branch> getBranch() {
        return branches;
    }

    public static void setBranch(HashMap<String, Branch> branch) {
        Company.branches = branch;
    }

    public static ArrayList<Admin> getAdmin() {
        return admins;
    }

    public static void setAdmins(ArrayList<Admin> admin) {
        Company.admins = admin;
    }
}
