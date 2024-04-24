package entity;

public class Admin extends User{
	
	public Admin(String userName, String userId, int age, String gender) {
        super(userName, userId, age, gender, Role.ADMIN);
    }

}
