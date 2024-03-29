class Product {
    private int productID;
    private String productName;
    private int price;
    private String[] size;
    private String[] category;
    
    public Product(int productID, String productName, String[] size, String[] category, int price) {
        this.productID = productID;
        this.productName = productName;
        this.size = size;
        this.category = category;
        this.price = price; 
    }

    public int getProductID() {
        return productID;
    }
    
    public int getPrice() {
        return price;
    }

    public String getName() {
        return productName;
    }
       
    public String[] getCategories() {
    	return category;
    }
    
    public String[] getSizes() {
    	return size;
    }
   
}
