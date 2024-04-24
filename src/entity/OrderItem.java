package entity;

public class OrderItem {
  private MenuItem item;
  private int quantity;
  private String remarks;

  public OrderItem(MenuItem item, int quantity, String remarks) {
    this.item = item;
    this.quantity = quantity;
    this.remarks = remarks;
  }

  public void setItem(MenuItem item) {
    this.item = item;
  }

  public MenuItem getItem() {
    return item;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public String getRemarks() {
    return remarks;
  }
}
