package entity;

public class Admin extends User{
	/**
     * Admin contructor 
     * @param userName Name of Admin.
     * @param userId ID of Admin.
     * @param age age of Admin.
     * @param gender gender of Admin.
     * @return void.
     */
	public Admin(String userName, String userId, int age, String gender) {
        super(userName, userId, age, gender, Role.ADMIN);
    }

}
