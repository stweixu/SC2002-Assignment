package entity;

public class MenuItem {
	private String itemName;
	private MenuCategory category;
	private double price;
	
	public MenuItem(String itemName, MenuCategory category, double price) {
	        this.itemName = itemName;
	        this.category = category;
	        this.price = price;
    }

	public String getItemName() {
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public MenuCategory getCategory() {
		return category;
	}
	
	public void setCategory(MenuCategory category) {
		this.category = category;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

}
