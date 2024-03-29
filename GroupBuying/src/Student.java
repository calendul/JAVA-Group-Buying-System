import java.util.ArrayList;
class Student {
    private String name;
    private int studentID;
    private String phoneNumber;
    private ArrayList<Group> groups;
    private ArrayList<Order> orders;


    public Student(String name, int studentID, String phoneNumber) {
        this.name = name;
        this.studentID = studentID;
        this.phoneNumber = phoneNumber;
        this.groups = new ArrayList<>();
        this.orders = new ArrayList<>();
    }
    
    public int getStuID() {
    	return studentID;
    }
    
    public String getMemberInfo() {
        return String.format(" 姓名: %s 連絡電話: %s ", name,phoneNumber);
    }
    
    public void addOrder(Group g,Order o) {
    	this.groups.add(g);
    	this.orders.add(o);
    }    
    //delete group & order for the member
    public void deleteOrder(Group g) {
    	this.groups.remove(g);
    	for(Order o:orders) {
    		if(o.getGroupID()==g.getGroupID()) {
    			this.orders.remove(o);
    		}
    		break;
    	}
    }
    
  //回傳個人訂單紀錄
    public String showOrder() {
        StringBuilder orderInfo = new StringBuilder();
        for(Order o:orders) {
            orderInfo.append("Group ID:").append(o.getGroupID()).append(" ").append(o.getProductName())
                    .append(" ").append(o.getCategory()).append(" ")
                    .append(o.getSize()).append(" 訂購數量:").append(o.getQuantity()).append("\n");
        }
        return orderInfo.toString();
    }
    public String lookupHistory() {
        String orderInfo = "";
        if (!orders.isEmpty()) {  // Check if the orders list is not empty
            orderInfo = showOrder();
            return orderInfo;
        } else {
            return "沒有歷史訂單紀錄";
        }

    }  
}
