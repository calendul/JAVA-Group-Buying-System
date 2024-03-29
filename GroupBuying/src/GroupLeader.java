import java.util.ArrayList;
class GroupLeader extends Student {
	private ArrayList<Group> createdGroups;
	public GroupLeader(String name, int studentID, String phoneNumber) {
	        super(name, studentID, phoneNumber);
	        this.createdGroups=new ArrayList<Group>();
	    }
	 public Group create_group(int group_ID, String groupName, int productID,
			 String productName, int price, int target, String date) {
	        Group group = new Group(group_ID, groupName, productID, productName, price, target, date);
	        group.setGroupLeader(this);
	        return group;
	    }
	 public void addGroups(Group g) {
		 createdGroups.add(g);
	    }
	 public String lookupHistory() {
		    StringBuilder historyInfo = new StringBuilder();
		    if (createdGroups != null) {
		        for (Group g : createdGroups) {
		            if (g != null) {
		                String groupHistory = g.display_info(g.getProductName());
		                if (!groupHistory.isEmpty()) {
		                    historyInfo.append(groupHistory).append("\n");
		                }
		            }
		        }
		        return historyInfo.toString();
		    }

		    else{
		        return "沒有歷史成團紀錄";
		    }
		}
}


