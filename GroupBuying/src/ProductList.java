import java.util.ArrayList;
class ProductList {
    private ArrayList<Product> products;
    public ProductList(ArrayList<Product> products) {
        this.products = products;
    }
    
    public String getProductName(int productID) {
    	String ProductName="";
    	for(Product p:products) {
    		if(p.getProductID()==productID) {
    			ProductName=p.getName();
    		}
    	}
        return ProductName;
    }
    
    public int getPrice(int productID) {
    	int productPrice=0;
    	for(Product p:products) {
    		if(p.getProductID()==productID) {
    			productPrice=p.getPrice();
    		}
    	}
        return productPrice;
    }
  //在問題中展示所有產品名稱
    public void showAllProducts() {
    	for (Product p : products) {
            System.out.println("(" + p.getProductID() + ")" + p.getName());
        }
    }
    public String getProductCategory(int productID) {
        StringBuilder categories = new StringBuilder();
        for(Product p:products) {
        	if(p.getProductID()==productID) {
        		for (int i = 0; i < p.getCategories().length; i++) {
                    categories.append(String.format("(%d)%s ", i + 1, p.getCategories()[i]));
                }
        	}
        }
        return categories.toString();
    }
    
    public String getProductSize(int productID) {
        StringBuilder sizes = new StringBuilder();
        for(Product p:products) {
        	if(p.getProductID()==productID) {
        		for (int i = 0; i < p.getSizes().length; i++) {
                    sizes.append(String.format( p.getSizes()[i])+" ");
                }
        	}
        }
        return sizes.toString();
    }
    
    public String getColor(int productID, int colorChoiceNum) {
    	StringBuilder choice = new StringBuilder();
    	for(Product p:products) {
        	if(p.getProductID()==productID) {
        		for (int i = 0; i < p.getCategories().length; i++) {
        			if(i+1==colorChoiceNum)
                    choice.append(p.getCategories()[i]);
                }
        	}
        }
    	return choice.toString();
    }
    
}
