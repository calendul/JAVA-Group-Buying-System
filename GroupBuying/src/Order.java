public class Order{
	private int groupID;
	private int productID;
	private String productName;
	private String category;
	private String size;
	private int q;
	public Order(int groupID,int productID,String productName,String category,String size,int q) {
		this.groupID=groupID;
		this.productID=productID;
		this.productName=productName;
		this.category=category;
		this.size=size;
		this.q=q;
	}
	public int getGroupID() {
		return groupID;
	}
	public String getProductName() {
		return productName;
	}
	public String getCategory() {
		return category;
	}
	public String getSize() {
		return size;
	}
	public int getQuantity() {
		return q;
	}
	
	public String displayOrder() {
		return String.format(" %d %s %s %s 數量:%d",
                productID, productName, category, size,q);
	}
}