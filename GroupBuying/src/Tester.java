import java.util.Scanner;
import java.util.ArrayList;
public class Tester {
	public static void main(String[] args) {
		//建立products data
		//帽踢
		String[]s1= {"S","M","L","XL"};
		String[]c1= {"粉色簡約款","冰藍色簡約款","綠色四維堂款","米色四維堂款"};
		Product hoodie=new Product(11,"NCCU帽T",s1,c1,800);
		//棒球外套
		String[]s2= {"S","M","L","XL","2L","3L"};
		String[]c2= {"駝色","黑色","深藍色"};
		Product varsityJacket=new Product(12,"NCCU棒球外套",s2,c2,1480);
		//飛行夾克
		String[]s3= {"S","M","L","XL","2L","3L"};
		String[]c3= {"軍綠色","海軍藍色","鋼鐵黑色"};
		Product jacket=new Product(13,"飛行夾克",s3,c3,1480);
		//短T
		String[]s4= {"S","M","L","XL","2L"};
		String[]c4= {"淺灰藍經典","沙白經典","白色政在下山中","黑色政在下山中"};
		Product tShirt=new Product(14,"短T",s4,c4,350);
		//Groups(all the groups created by different groupLeaders will be saved in this array)
		ArrayList <Group> groups=new ArrayList<Group>();
		//新增products
		ArrayList <Product> products=new ArrayList<Product>();
		products.add(hoodie);
		products.add(varsityJacket);
		products.add(jacket);
		products.add(tShirt);
		ProductList productList=new ProductList(products);
		//執行
		Scanner sc=new Scanner(System.in);
		boolean shouldContinue = true;
		while (shouldContinue){
			System.out.println("請選擇執行動作:\n" + "(a)團主成團 (b)團員加入 (c)查詢購物團資訊 (d)搜尋您的歷史記錄 (e)取消訂單 (q)Quit:");
            String action = sc.next().toLowerCase();
            switch(action) {
            case "q":
            	for(Group g:groups) {
                	System.out.println(g.display_info(productList.getProductName(g.getGroupID()/ 1000000)));
                	System.out.println("--------------------------------------");
                }
            	shouldContinue = false;
                break;
            //團主成團
            case "a":
                System.out.println("請輸入你的學號,姓名,電話(用空白鍵分隔):");
                int stuID = sc.nextInt();
                String lastStuID = String.format("%03d", stuID % 1000);
                String name = sc.next();
                String phoneNum = sc.next();
                String lastPhoneNum = phoneNum.substring(7);//取電話號碼末三碼
                System.out.println("請輸入團名:");
                String groupName = sc.next();
                System.out.println("請選擇品項:");
                productList.showAllProducts();
                int productID = sc.nextInt();
                String productName = productList.getProductName(productID);
                int price = productList.getPrice(productID);
                String stringProductID = String.format("%02d", productID); // Ensure productID is two digits
                int groupID = Integer.parseInt(stringProductID + lastStuID + lastPhoneNum);//group ID由product ID、團主學號和電話末三碼組成
                System.out.println("請輸入品項數量上限:");
                int target = sc.nextInt();
                System.out.println("請輸入結單日期:");
                String date = sc.next();
                GroupLeader leaderStu=new GroupLeader(name,stuID,phoneNum);
                Group g = leaderStu.create_group(groupID, groupName, productID, productName, price, target, date);
                g.setGroupLeader(leaderStu);
                groups.add(g);
                leaderStu.addGroups(g);
                System.out.println("--------------------------------------");
                System.out.println(g.creatingInfo(productName, target, date));
                continue;
            //團員加入
            case "b":
            	if(groups.size()==0) {
            		System.out.println("目前沒有可參加的團購團");
            		break;
            	}
                System.out.println("請輸入你的學號,姓名,電話(用空白鍵分隔):");
                int memberStuID = sc.nextInt();
                String memberName = sc.next();
                String memberPhoneNum = sc.next();
                System.out.println("請輸入欲加入購物團的Group ID:");
                int groupNumber = sc.nextInt();
                Group selectedGroup = null;
                for (Group g1 : groups) {
                    if (g1.getGroupID() == groupNumber) {
                        selectedGroup = g1;
                        Student s =new Student(memberName, memberStuID, memberPhoneNum);
                        g1.addMember(s);//團購團添加成員
                        int firstTwoGroupID = g1.getGroupID() / 1000000;
                        String slectedProductName = "";
                        for (Product p : products) {
                            if (p.getProductID() == firstTwoGroupID) {
                                slectedProductName = p.getName();
                                System.out.println("團購品項:" + slectedProductName);
                                break;
                            }
                        }

                        System.out.println("請輸入欲選購顏色款式:" + productList.getProductCategory(firstTwoGroupID));
                        int colorChoice = sc.nextInt();
                        String selectedCategory = productList.getColor(firstTwoGroupID, colorChoice);

                        System.out.println("請輸入size:" + productList.getProductSize(firstTwoGroupID));
                        String selectedSize = sc.next().toUpperCase();

                        System.out.println("請輸入選購數量");
                        int quantity = sc.nextInt();
                        //new order
                        Order o=new Order(groupNumber,firstTwoGroupID,slectedProductName,selectedCategory,selectedSize,quantity);
                        s.addOrder(g1,o);//添加成員的訂單紀錄
                        
                        System.out.println("注意:同一個團購團中，一個人只能有一筆選購紀錄。\n以下為您的訂購訊息，請確認是否無誤(確認訂購並結束打0，重新輸入打1):");
                        System.out.printf("%s %s %s %s %s 數量:%d%n",
                                 memberName, memberPhoneNum, slectedProductName, selectedCategory, selectedSize, quantity);

                        int confirmation = sc.nextInt();
                        if (confirmation == 0) {
                        	System.out.println("確認訂購。");
                        	selectedGroup.addOrder(selectedGroup.getGroupID(), slectedProductName, firstTwoGroupID, selectedCategory
                        			, selectedSize, quantity);
                        }
                        else {
                        	System.out.println("先前訂單已被取消，請重新輸入。");
                        	s.deleteOrder(g1);//刪除成員的訂單紀錄
                            g1.deleteMember(s);//團購團刪除該成員
                            continue;
                        	}
                    }
                }

                if (selectedGroup == null) {
                    System.out.println("Corresponding group cannot be found.");
                    continue;
                }
                break;
            //查詢購物團資訊
            case "c":
                System.out.println("請輸入欲團購之品項:");
                productList.showAllProducts();
                int pID=sc.nextInt();
                System.out.printf("以下為所有品項為%s的團購團:\n",productList.getProductName(pID));
                Group selectedg=null;
                for(Group g1:groups) {
                	if(g1.getGroupID() / 1000000==pID) {
                		selectedg=g1;
                		System.out.printf("ID:%d 團名:%s\n",g1.getGroupID(),g1.getGroupName());
                	}
                }
                if(selectedg==null){
            		System.out.println("Sorry, 沒有可查詢的團購團。");
            		break;
            	}
                System.out.println("請輸入欲查詢之團購團ID:");
                int gID=sc.nextInt();
                System.out.println("--------------------------------------");
                for(Group g2:groups) {
                	if(gID==g2.getGroupID()) {
                		System.out.println(g2.display_info(productList.getProductName(gID/ 1000000)));
                		System.out.println("--------------------------------------");
                	}
                }
                break;
            //搜尋您的歷史記錄 
            case "d":
            	System.out.println("(1)搜尋您的所有歷史訂單紀錄 (2)搜尋您的成團歷史紀錄");
            	int choice = sc.nextInt();
            	if (choice == 1) {
            	    System.out.println("請輸入你的學號:");
            	    int stuid = sc.nextInt();
            	    System.out.println("--------------------------------------");
            	    boolean hasJoinedAnyGroup = false;

            	    for (Group g2 : groups) {
            	        if (g2.findVeryMember(stuid) != null) {
            	            System.out.println(g2.findVeryMember(stuid).lookupHistory());
            	            hasJoinedAnyGroup = true;
            	        }
            	    }

            	    if (!hasJoinedAnyGroup) {
            	        System.out.println("你沒有參加任何團購團");
            	    }

            	} else if (choice == 2) {
            	    System.out.println("請輸入你的學號:");
            	    int stuid = sc.nextInt();
            	    System.out.println("--------------------------------------");
            	    boolean hasCreatedAnyGroup = false;

            	    for (Group g2 : groups) {
            	        if (g2.getGroupLeader().getStuID() == stuid) {
            	            System.out.println(g2.getGroupLeader().lookupHistory());
            	            hasCreatedAnyGroup = true;
            	            break;
            	        }
            	    }

            	    if (!hasCreatedAnyGroup) {
            	        System.out.println("你沒有創建任何團購團");
            	    }
            	}

            	break;
            //取消訂單 	
            case "e":
            	System.out.println("請輸入團購團ID:");
                int groupid = sc.nextInt();
                System.out.println("請輸入你的學號:");
                int studentid = sc.nextInt();
                System.out.println("--------------------------------------");
                boolean orderFound = false;

                for (Group group : groups) {
                    for (Student m : group.getMembers()) {
                        if (m.getStuID() == studentid) {
                            m.deleteOrder(group); // 刪除成員自己的訂單紀錄
                            group.cancelOrder(groupid, studentid); // 團購團刪除該訂單
                            System.out.printf("已成功刪除您在團購團%s的訂單!\n", groupid);
                            orderFound = true;
                            break;
                        }
                    }
                }

                if (!orderFound) {
                    System.out.println("Sorry, we cannot find your order.");
                }
                break;
            }
        }
		sc.close();
	}
} 
    


