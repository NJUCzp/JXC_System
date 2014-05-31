package models;
import java.util.ArrayList;

import control.Helper;
import data.Account;
import data.commodity;
import data.customer;


public class ImportManagement {
	String instruction;
	Object[][] messageTable;
	String messageText="";
	Helper helper=new Helper();
	private ArrayList<String>iminfo=new ArrayList<String>();
	ArrayList<String>cominfo=new ArrayList<String>();
	ArrayList<commodity>com=new  ArrayList<commodity>();
	ArrayList<String>cusinfo=new ArrayList<String>();
	ArrayList<customer>cus=new ArrayList<customer>();
	
	public void setInstruction(String instruction){
		this.instruction=instruction;
	}
	
	public void getInfo(){
		Helper helper1=new Helper();
		helper1.setFilename("data/importsheet.txt");
		iminfo=helper1.readfile();
		if(iminfo.get(0).equals("")){
			iminfo.clear();
		}
		System.out.println(iminfo.size());
		
		Helper helper2=new Helper();
		helper2.setFilename("data/commodity.txt");
		cominfo=helper2.readfile();
		for(int i=0;i<cominfo.size();i++){
			commodity tempcom=new commodity();
			helper2.split(cominfo.get(i));
			tempcom.setName(helper2.sArray[0]);
	        tempcom.setNumber(helper2.sArray[1] );
	        tempcom.setQuantity(Integer.parseInt(helper2.sArray[2]));
	        tempcom.setDefaultImportPrice(Integer.parseInt(helper2.sArray[3]));
	        tempcom.setDefaultExportPrice(Integer.parseInt(helper2.sArray[4]));
	        tempcom.setLatestImportPrice(Integer.parseInt(helper2.sArray[5]));
	        tempcom.setLatestExportPrice(Integer.parseInt(helper2.sArray[6]));
			com.add(tempcom);
		}
		
		Helper helper3=new Helper();
		helper3.setFilename("data/customer.txt");
		cusinfo=helper3.readfile();
		for(int i=0;i<cusinfo.size();i++){
			System.out.println(cusinfo.get(i));
			customer tempcus=new customer();
			helper3.split(cusinfo.get(i));
			tempcus.setName(helper3.sArray[0]);
			tempcus.setPhoneNumber(helper3.sArray[1]);
			tempcus.setNeedToReceive(Integer.parseInt(helper3.sArray[2]));
			tempcus.setNeedToPay(Integer.parseInt(helper3.sArray[3]));
			tempcus.setTotal(Integer.parseInt(helper3.sArray[4]));
			cus.add(tempcus);
		}
		
		System.out.println(iminfo.size());
	}
	public void go(){
		char keyword=instruction.charAt(0);
		getInfo();
		switch(keyword){
		case 'A':{
			helper.split(instruction.substring(4));
			String cusName=helper.sArray[0];
			String commodityName=helper.sArray[1];
			int quantity=Integer.parseInt(helper.sArray[3]);
			int imprice=Integer.parseInt(helper.sArray[4]);
			int cashPay=quantity*imprice;
			int n=iminfo.size();
			boolean cusExist=false;
			boolean comExist=false;
			
			for(int i=0;i<com.size();i++){
				if(com.get(i).getName().equals(commodityName))
					comExist=true;
			}
			for(int i=0;i<cus.size();i++){
				if(cus.get(i).getName().equals(cusName))
					cusExist=true;
			}
			
			if((!comExist)&&(!cusExist)){
				iminfo.add(instruction+"；"+cashPay+"；"+n+"");
				helper.output(iminfo);
				System.out.println("添加成功！");
				messageText="graphics/success_add.png";
			
				changeCommodity(commodityName,imprice,0,quantity);
				changeAccount(cashPay,0);
				changeCustomer(cusName,cashPay,0);
				changeHistory(commodityName);
			}else{
				messageText="graphics/import/import_error_add.png";
			}
			
			break;
		}
		case 'D':{
			helper.split(instruction.substring(4));
			String sheetNumber=helper.sArray[0];
			int quantity=Integer.parseInt(helper.sArray[1]);
			String date=helper.sArray[2];
			
			for(int i=0;i<iminfo.size();i++){
			    helper.split(iminfo.get(i));
			    String tempSheetNumber=helper.sArray[7];
			    if(sheetNumber.trim().equals(tempSheetNumber.trim())){
			        String cusName=helper.sArray[0];
			        String commodityName=helper.sArray[1];
			        String number=helper.sArray[2];
			        int tempquantity=Integer.parseInt(helper.sArray[3]);
			        int imprice=Integer.parseInt(helper.sArray[4]);
			        if(quantity<tempquantity){
			            setInstruction("DEL:"+sheetNumber+"；"+tempquantity+""+"；"+date);
			            go();
			            setInstruction("ADD:"+cusName.substring(4)+"；"+commodityName+"；"+number+"；"+(tempquantity-quantity)+""+"；"+imprice+""+"；"+date);
			            go();
		        		messageText="graphics/import_success_del.png";
			        }else{
			        	if(quantity==tempquantity){
			        		int cashPay=tempquantity*imprice;
				            iminfo.add("DEL:"+cusName.substring(4)+"；"+commodityName+"；"+number+"；"+tempquantity+"；"+imprice+""+"；"+date+"；"+cashPay+""+"；"+sheetNumber);
				            helper.output(iminfo);
				            System.out.println("添加成功！");
				
				            changeCommodity(commodityName,imprice,0,-quantity);
				            changeAccount(-cashPay,0);
				            changeCustomer(cusName.substring(4),-cashPay,0);
			        		messageText="graphics/import_success_del.png";

			        	}else{
			        		messageText="graphics/import/import_error_del.png";
			        	}
			            break;
			        }
			    }
			}
		    break;
		}
		case 'S':{
			int n=iminfo.size();
			helper.split(instruction.substring(4));
			String date1=helper.sArray[0];
			String date2=helper.sArray[1];
			messageTable=new Object[n][10];
			do{
			for(int i=0;i<n;i++){
				helper.split(iminfo.get(i));
				String imdate=helper.sArray[5];
				
				if(imdate.equals(date1)){
					messageTable[i][0]=new Boolean(false);
					messageTable[i][1]=helper.sArray[5];
				    messageTable[i][2]=helper.sArray[0].substring(0,3);
				    messageTable[i][3]=helper.sArray[0].substring(4);
				    messageTable[i][4]=helper.sArray[1];
				    messageTable[i][5]=helper.sArray[2];
				    messageTable[i][6]=helper.sArray[3];
				    messageTable[i][7]=helper.sArray[4];
				    messageTable[i][8]=helper.sArray[6];
				    messageTable[i][9]=helper.sArray[7];
				   
					System.out.println(imdate+":"+iminfo.get(i));
				}
				
			}
			helper.dataplusone(date1);

			}while(date1.equals(date2)==true);
			break;

		}
		case 'W':{
			messageTable=new Object[iminfo.size()][10];
			for(int i=0;i<iminfo.size();i++){
				helper.split(iminfo.get(i));
				System.out.println (iminfo.get(i));
				messageTable[i][0]=new Boolean(false);
				messageTable[i][1]=helper.sArray[5];
			    messageTable[i][2]=helper.sArray[0].substring(0,3);
			    messageTable[i][3]=helper.sArray[0].substring(4);
			    messageTable[i][4]=helper.sArray[1];
			    messageTable[i][5]=helper.sArray[2];
			    messageTable[i][6]=helper.sArray[3];
			    messageTable[i][7]=helper.sArray[4];
			    messageTable[i][8]=helper.sArray[6];
			    messageTable[i][9]=helper.sArray[7];
			}
			break;
		}
		default:System.out.println("格式错误！");
		}
	}
	
	public void changeCommodity(String commodityName,int imprice,int exprice,int quantity){
		Helper helper=new Helper();
		String newinfo="";
		int n=cominfo.size();
		for(int i=0;i<n;i++){
			if(com.get(i).getName().equals(commodityName)){
				com.get(i).setQuantity(com.get(i).getQuantity()+quantity);
				com.get(i).setLatestImportPrice(imprice);
			}
			newinfo=com.get(i).getName()+"；"+com.get(i).getNumber()+"；"+com.get(i).getQuantity()+""+"；"+com.get(i).getDefaultImportPrice()+""+"；"+com.get(i).getDefaultExportPrice()+""+"；"+com.get(i).getLatestImportPrice()+""+"；"+com.get(i).getLatestExportPrice()+"";
		}
		helper.split(newinfo);
		int n1=n;
		
		for(int i=0;i<n;i++){
			helper.split(cominfo.get(i));
			String name2=helper.sArray[0];
			//System.out.println(name1+" "+name2);
			if(commodityName.equals(name2)){
				cominfo.remove(i);
				cominfo.add(newinfo);
				//i--;
				n1--;
			}
		}
		if(n1==n){
			System.out.println("原来无此商品！");
		}
		
		helper.setFilename("data/commodity.txt");
		helper.output(cominfo);
		
	
	}
	
	public void changeAccount(int cashPay,int cashRec){
		Helper helper=new Helper();
		ArrayList<String>accinfo=new ArrayList<String>();
		ArrayList<Account>acc=new ArrayList<Account>();
		String newaccinfo="";
		Account tempacc=new Account();
		helper.setFilename("data/account.txt");
		accinfo=helper.readfile();
		helper.split(accinfo.get(0));
		tempacc.setAllCash(Integer.parseInt(helper.sArray[0]));
		tempacc.setAllNeedToReceive(Integer.parseInt(helper.sArray[1])+cashPay);
		tempacc.setAllNeedToPay(Integer.parseInt(helper.sArray[2])+cashRec);
		acc.add(tempacc);
		newaccinfo=tempacc.getAllCash()+""+"；"+tempacc.getAllNeedToReceive()+""+"；"+tempacc.getAllNeedToPay()+"";
		
		accinfo.clear();
		accinfo.add(newaccinfo);
		helper.output(accinfo);

		
	}
	
	public void changeCustomer(String cusName,int cashRec,int cashPay){
		Helper helper=new Helper();
		
		String newinfo="";
		int n=cusinfo.size();

		for(int i=0;i<n;i++){
			//System.out.println(cus.get(i).name.equals(cusName));

			if(cus.get(i).getName().equals(cusName)){
				cus.get(i).setNeedToPay(cus.get(i).getNeedToPay()+cashPay);
				cus.get(i).setNeedToReceive(cus.get(i).getNeedToReceive()+cashRec);
				newinfo=cus.get(i).getName()+"；"+cus.get(i).getPhoneNumber()+"；"+cus.get(i).getNeedToReceive()+""+"；"+cus.get(i).getNeedToPay()+"";
			}
		}
		if(newinfo.equals("")){
			System.out.println("原来无此客户！");
		}
		
		helper.split(newinfo);
		String name1=helper.sArray[0];
		
		for(int i=0;i<n;i++){
			helper.split(cusinfo.get(i));
			String name2=helper.sArray[0];
			if(name1.equals(name2)){
				cusinfo.remove(i);
				cusinfo.add(newinfo);
				//i--;
			}
		}
		
		helper.setFilename("data/customer.txt");
		helper.output(cusinfo);
		
	}
	public void changeHistory(String commodityName){
		helper.setFilename("data/history.txt");
		ArrayList<String>hisinfo=helper.readfile();
        if(hisinfo.get(0).equals(""))
        	hisinfo.clear();
		if(hisinfo.indexOf(commodityName)<0)
			hisinfo.add(commodityName);
		helper.output(hisinfo);
	}
	
	public Object[][] getMessageTable(){
		return messageTable;
	}
	public String getMessageText(){
		return messageText;
	}

}
