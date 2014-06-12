package models;
import java.util.ArrayList;

import control.Helper;
import data.commodity;
public class CommodityManagement {
	String instruction;
	String messageText="";
	Object[][] messageTable;
	ArrayList<String>info=new ArrayList<String>();
	ArrayList<commodity>com=new  ArrayList<commodity>();
	ArrayList<String>history=new ArrayList<String>();
	Helper helper=new Helper();
	public void setInstruction(String instruction){
		this.instruction=instruction;
		//获取历史记录
		Helper helper1=new Helper();
		helper1.setFilename("data/history.txt");
		history=helper1.readfile();
		
		helper.setFilename("data/commodity.txt");
		info=helper.readfile();
		helper.output(info);
		
		int n=info.size();
		
		com.clear();
		
		if(!info.get(0).equals("")){
		    for(int i=0;i<n;i++){
		    	if(!info.get(i).equals("")){
		    		commodity tempcom=new commodity();
			        helper.split(info.get(i));
			        tempcom.setName(helper.sArray[0]);
			        tempcom.setNumber(helper.sArray[1] );
			        tempcom.setQuantity(Integer.parseInt(helper.sArray[2]));
			        tempcom.setDefaultImportPrice(Integer.parseInt(helper.sArray[3]));
			        tempcom.setDefaultExportPrice(Integer.parseInt(helper.sArray[4]));
			        tempcom.setLatestImportPrice(Integer.parseInt(helper.sArray[5]));
			        tempcom.setLatestExportPrice(Integer.parseInt(helper.sArray[6]));
			        com.add(tempcom);
		    	}
		}
		}else{
			info.clear();
		}
	}
	

	
	public void go(){
		char keyword=instruction.charAt(0);
		switch(keyword){
		case 'A':{
			String s=instruction.substring(4);
			String newinfo;
			boolean isAlreadyThere=false;
			helper.split(s);
			for(int i=0;i<com.size();i++){
				if(com.get(i).getName().equals(helper.sArray[0]))
					isAlreadyThere=true;
			}
			if(!isAlreadyThere){
				newinfo=helper.sArray[0]+"；"+helper.sArray[1]+"；"+"0"+"；"+helper.sArray[2]+"；"+helper.sArray[3]+"；0；0";
				info.add(newinfo);
				System.out.println("成功添加！");
				messageText="graphics/success_add.png";
			}else{
				messageText="graphics/commodity/commodity_error_add.png";
				System.out.println("商品已存在");
			}
			
			helper.output(info);
			break;
		}
		case 'D':{
			String s=instruction.substring(4);
			helper.split(s);
			String name1=helper.sArray[0];
			String number1=helper.sArray[1];
			int n=info.size();
			int n1=n;
			System.out.println(history.indexOf(name1));
			System.out.println("historySize: "+history.size());
			for(int i=0;i<history.size();i++){
				System.out.println("history"+i+" : "+history.get(i));
			}
			for(int i=0;i<n;i++){
				String name2=com.get(i).getName();
				String number2=com.get(i).getNumber();
				if(name1.equals(name2)&&number1.equals(number2)&&(history.indexOf(name1)<0)){
					info.remove(i);
    				i--;
    				n--;
    				break;
				}
			}
			if(n1==n){
				messageText="graphics/commodity/commodity_error_del.png";
				System.out.println("删除失败！");
			}else{
				messageText="graphics/success_del.png";
				System.out.println("已从仓库删除！");
			} 
			helper.output(info);
			break;
		}
		case 'U':{
			String s=instruction.substring(4);
			helper.split(s);
			String name1=helper.sArray[0];
			String number1=helper.sArray[1];
			String defaultImportPrice=helper.sArray[2];
			String defaultExportPrice=helper.sArray[3];
			int n=info.size();
			int n1=n;
			
			for(int i=0;i<n;i++){
				helper.split(info.get(i));
				String name2=helper.sArray[0];
				String number2=helper.sArray[1];
				String quantity=helper.sArray[2];
				String latestImportPrice=helper.sArray[5];
				String latestExportPrice=helper.sArray[6];
				if(name1.equals(name2)&&number1.equals(number2)){
					String newinfo=name2+"；"+number2+"；"+quantity+"；"+defaultImportPrice+"；"+defaultExportPrice+"；"+latestImportPrice+"；"+latestExportPrice;

					info.remove(i);
    				info.add(newinfo);
    				i--;
    				n--;
				}
			}
			if(n1==n){
				messageText="graphics/commodity/commodity_error_upd.png";
				System.out.println("原来无此商品！");
			}else{
				messageText="graphics/success_upd.png";
				System.out.println("已成功更新！");
			} 
			helper.output(info);
			break;
		}
		case 'F':{
			String s=instruction.substring(4);
			helper.split(s);
			String name1=helper.sArray[0];
			String number1=helper.sArray[1];
			int n=info.size();
			
			for(int i=0;i<n;i++){
				helper.split(info.get(i));
				String name2=com.get(i).getName();
				String number2=com.get(i).getNumber();
				
				if(name1.equals(name2)&&number1.equals(number2)){
					messageTable=new Object[1][8];
					messageTable[0][0]=new Boolean(false);
					messageTable[0][1]=com.get(i).getName();
					messageTable[0][2]=com.get(i).getNumber();
					messageTable[0][3]=(com.get(i).getQuantity()+"");
					messageTable[0][4]=(com.get(i).getDefaultImportPrice()+"");
					messageTable[0][5]=(com.get(i).getDefaultExportPrice()+"");
					messageTable[0][6]=(com.get(i).getLatestImportPrice()+"");
					messageTable[0][7]=(com.get(i).getLatestExportPrice()+"");
					System.out.println(info.get(i));
				}
			}
			break;
		}
		case 'S':{
			int n=info.size();
			messageTable=new Object[n][8];
			for(int i=0;i<n;i++){
				//System.out.println(com.get(i).getDefaultExportPrice()+"  "+com.get(i).getDefaultImportPrice());
				messageTable[i][0]=new Boolean(false);
				messageTable[i][1]=com.get(i).getName();
				messageTable[i][2]=com.get(i).getNumber();
				messageTable[i][3]=com.get(i).getQuantity()+"";
				messageTable[i][4]=com.get(i).getDefaultImportPrice()+"";
				messageTable[i][5]=com.get(i).getDefaultExportPrice()+"";
				messageTable[i][6]=com.get(i).getLatestImportPrice()+"";
				messageTable[i][7]=com.get(i).getLatestExportPrice()+"";
			}
			break;
		}
		}
		
	}
	
	public String getMessageText(){
		return messageText;
	}
	
	public Object[][] getMessageTable(){
		return messageTable;
	}
		

}
