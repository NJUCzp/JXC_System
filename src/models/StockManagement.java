package models;
import java.util.ArrayList;

import control.Helper;


public class StockManagement {
	String instruction;
	Object[][] messageTable=new Object[100][11];
	Helper helper=new Helper();
	ArrayList<String>iminfo=new ArrayList<String>();
	ArrayList<String>exinfo=new ArrayList<String>();
	ArrayList<String>cominfo=new ArrayList<String>();
	ArrayList<String>history=new ArrayList<String>();
	public void setInstruction(String instruction){
		this.instruction=instruction;
	}	
	public void go(){
		if(instruction.equals("STOCK_SHO")){			
			//获取历史记录，并对于每一种商品，进行如下操作
			helper.setFilename("data/history.txt");
			history=helper.readfile();
			int n0=history.size();
			System.out.println("History Size: "+n0);
			for(int j=0;j<n0;j++){
				int imquantity=0;
				int totalimprice=0;
				int exquantity=0;
				int totalexprice=0;
			

			    String name=history.get(j);
			    String number="";
			    //获取进货数量、进货总价
			    Helper helper1=new Helper();
			    helper1.setFilename("data/commodity.txt");
			    cominfo=helper1.readfile();
			    for(int i=0;i<cominfo.size();i++){
			    	helper.split(cominfo.get(i));
			    	if(helper.sArray[0].equals(name)){
			    		number=helper.sArray[1];
			    	}
			    }
			    Helper helper2=new Helper();
			    helper2.setFilename("data/importsheet.txt");
			    iminfo=helper2.readfile();
			    int n=iminfo.size();
			    
					for(int i=0;i<n;i++){
						helper.split(iminfo.get(i));
						String commodityname=helper.sArray[1];
						if(name.equals(commodityname)&&iminfo.get(i).charAt(0)=='A'){
							imquantity=imquantity+Integer.parseInt(helper.sArray[3]);
							totalimprice=totalimprice+Integer.parseInt(helper.sArray[6].replace(" ", ""));
						}else{
							imquantity=imquantity-Integer.parseInt(helper.sArray[3]);
							totalimprice=totalimprice-Integer.parseInt(helper.sArray[6].replace(" ", ""));
						}
					}
				
				//获取销售数量、销售总价
				Helper helper3=new Helper();
				helper3.setFilename("data/exportsheet.txt");
				exinfo=helper3.readfile();
				int n1=exinfo.size();
				
					for(int i=0;i<n1;i++){
						helper.split(exinfo.get(i));
						String commodityname=helper.sArray[1];
						if(name.equals(commodityname)&&exinfo.get(i).charAt(0)=='A'){
							exquantity=exquantity+Integer.parseInt(helper.sArray[3]);
							totalexprice=totalexprice+Integer.parseInt(helper.sArray[6]);
						}else{
							exquantity=exquantity-Integer.parseInt(helper.sArray[3]);
							totalexprice=totalexprice-Integer.parseInt(helper.sArray[6]);
						}
					}
								
				//输出
				int averageimprice;
				int averageexprice;
				int averageprice;
				
				if(imquantity==0)
					averageimprice=0;
				else
					averageimprice=totalimprice/imquantity;
				
				if(exquantity==0)
					averageexprice=0;
				else
					averageexprice=totalexprice/exquantity;
				
				if(imquantity==exquantity)
					averageprice=0;
				else
					averageprice=(totalimprice-totalexprice)/(imquantity-exquantity);
				System.out.println(name+" "+number+" "+imquantity+" "+averageimprice+" "+totalimprice+" "+exquantity+" "+averageexprice+" "+totalexprice+" "+(imquantity-exquantity)+" "+averageprice+" "+(totalimprice-totalexprice));
			    messageTable[j][0]=name;
			    messageTable[j][1]=number;
			    messageTable[j][2]=imquantity+"";
			    messageTable[j][3]=averageimprice+"";
			    messageTable[j][4]=totalimprice+"";
			    messageTable[j][5]=exquantity+"";
			    messageTable[j][6]=averageexprice+"";
			    messageTable[j][7]=totalexprice+"";
			    messageTable[j][8]=(imquantity-exquantity)+"";
			    messageTable[j][9]=averageprice+"";
			    messageTable[j][10]=(totalimprice-totalexprice)+"";
			
			}
		}else{
			System.out.println("指令错误！");
		}
		
	}
	public Object[][] getMessageTable(){
		return messageTable;
	}

}
