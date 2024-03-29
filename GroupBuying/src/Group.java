import java.util.ArrayList;
class Group {
    private int groupID;
    private String groupName;
    private int productID;
    private String productName;
    private int price;
    private int target;
    private String date;
    private int number;
    private Student groupLeader;
    private ArrayList<Student> members;
    private ArrayList<Order> orders;
    public Group(int groupID, String groupName, int productID, String productName, int price, int target, String date) {
        this.groupID = groupID;
        this.groupName = groupName;
        this.productID = productID;
        this.productName=productName;
        this.price = price;
        this.target = target;
        this.date = date;
        this.number = 0;
        this.members = new ArrayList<Student>();
        this.orders = new ArrayList<Order>();
    }
    
    public void setGroupLeader(Student gLeader) {
    	this.groupLeader=gLeader;
    }
    

    public Student getGroupLeader() {
    	return groupLeader;
    }
       
    public String getGroupName() {
    	return groupName;
    }
    
    public int getGroupID() {
        return groupID;
    }
    
    public int getProuctID() {
    	return productID;
    }

    public String getProductName() {
    	return productName;
    }
    

    public ArrayList<Order> getOrders(){
    	return orders;
    }

    public ArrayList<Student> getMembers(){
    	return members;
    }
    
    //創團資訊
    public String creatingInfo(String item,int limit,String date) {
        return String.format("團購成團資訊:\n團名：%s\n團主資訊:\n%s\nGroup ID:%d\n品項:%s\n數量上限：%d\n收單日期:%s\n"
        		,getGroupName(),groupLeader.getMemberInfo(),getGroupID(),item,limit,date );
    }
    
    public String display_info(String productName) {
    	if(number!=0) {
    		return String.format("團名：%s\n品項:%s\n數量上限:%d\n結單日期:%s\n團主資訊(姓名/連絡電話):\n%s\n團員訂購資訊:\n%s\n總數量:%d\n單價:%d\n總價:%d"
    				,getGroupName(),productName,target,date,groupLeader.getMemberInfo(),display_orders(),number
    				,calTotalPrice(productID)/number,calTotalPrice(productID));
    	}
    	else {
    		return String.format("團名：%s\n品項:%s\n數量上限:%d\n結單日期:%s\n團主資訊(姓名/連絡電話):\n%s\n團員訂購資訊:\n%s\n總數量:%d\n單價:%d\n總價:%d"
    				,getGroupName(),productName,target,date,groupLeader.getMemberInfo(),display_orders(),number,price,0);
    	}
    }
    
    //回傳所有訂單紀錄
    public String display_orders() {
    	StringBuilder orderInfo=new StringBuilder();
        for(int i=0;i<orders.size();i++) {
        	orderInfo.append(members.get(i).getMemberInfo()+orders.get(i).getProductName()+" "+orders.get(i).getCategory()+" "
        +orders.get(i).getSize()+" "+orders.get(i).getQuantity());
        	orderInfo.append("\n");
        }
        return orderInfo.toString();
    }
    

    public void addMember(Student s) {
        members.add(s);
    }
    
    public void deleteMember(Student s) {
        members.remove(s);
    }
    
    public Student findVeryMember(int stuID) {
        for (Student m : members) {
            if (m.getStuID() == stuID) {
                return m;
            }
        }
        return null; // Return null if no matching student is found
    }
    
    public void addOrder(int group_ID, String productName, int productID, String category, String size, int num) {
    	Order o=new Order(groupID,productID,productName, category, size, num);
    	if(groupID==group_ID) {
    		if((number+num)<=target) {
    			orders.add(o);
        		number+=num;
    		}
    		else {
    			System.out.println("Sorry, this group is no longer available.");
    		}
    	}
    }

    public void cancelOrder(int gID, int stuID) {
    	Student m=null;
    	Order o=null;
        if (gID == groupID) {
            for(int i=0;i<members.size();i++) {
            	if(members.get(i).getStuID()==stuID) {
            		m=members.get(i);
            		o=orders.get(i);
            		members.remove(m);
            		orders.remove(o);
            		number-=o.getQuantity();
            		break;
            	}
            	
            }
        }
    }

    
    //discounts
    public int calTotalPrice(int productID) {
    	if(productID==11) {
    		if(number<25) {
    			return number*price;
    		}
    		else if(number<40){
    			return number*690;
    		}
    		else {
    			return number*680;
    		}
    	}
    	else if(productID==12||productID==13) {
    		if(number<20) {
    			return number*price;
    		}
    		else {
    			return number*1280;
    		}
    	}	
    	else{
    		if(number<20) {
    			return number*price;
    		}
    		else {
    			return number*290;
    		}
    	}
    }
}

