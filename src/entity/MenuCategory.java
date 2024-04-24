package entity;

public enum MenuCategory {
    SET_MEAL,
    BURGER,
    SIDE,
    DRINK;
	
    public String toString() {
        return name().replaceAll("_", " "); // Convert enum name to a more readable format
}
	}
