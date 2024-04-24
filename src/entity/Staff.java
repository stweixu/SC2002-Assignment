package entity;

public class Staff extends User {
	//private Role role;
	private String branchName;
	
	public Staff(String username, String userId, Role role, String branchName, int age, String gender) {
	    super(username, userId, age, gender, role);
	    this.branchName = branchName;
	}
	

	public String getBranchName() {
	    return branchName;
	}

	public void setBranchName(String branchName) {
	    this.branchName = branchName;
	}

}
