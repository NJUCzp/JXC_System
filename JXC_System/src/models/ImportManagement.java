package models;
import java.util.ArrayList;

import control.Helper;
import data.Account;
import data.commodity;
import data.customer;


public class ImportManagement {
	String instruction;
	Object[][] messageTable;
	Helper helper=new Helper();
	ArrayList<String>iminfo=new ArrayList<String>();
	public void setInstruction(String instruction){
		this.instruction=instruction;
		
	}
	
	public void go(){
		char keyword=instruction.charAt(0);
		helper.setFilename("data/importsheet.txt");
		iminfo=helper.readfile();
		if(iminfo.get(0).equals("")){
			iminfo.clear();
		}
		switch(keyword){
		case 'A':{
			helper.split(instruction.substring(4));
			String cusName=helper.sArray[0];
			String commodityName=helper.sArray[1];
			int quantity=Integer.parseInt(helper.sArray[3]);
			int imprice=Integer.parseInt(helper.sArray[4]);
			int cashPay=quantity*imprice;
			int n=iminfo.size();
			
			iminfo.add(instruction+"��"+cashPay+"��"+n+"");
			helper.output(iminfo);
			System.out.println("��ӳɹ���");
		
			changeCommodity(commodityName,imprice,0,quantity);
			changeAccount(cashPay,0);
			changeCustomer(cusName,cashPay,0);
			changeHistory(commodityName);
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
			        //String tempdate=helper.sArray[5];
			        if(quantity<tempquantity){
			            setInstruction("DEL:"+sheetNumber+"��"+tempquantity+""+"��"+date);
			            go();
			            setInstruction("ADD:"+cusName.substring(4)+"��"+commodityName+"��"+number+"��"+(tempquantity-quantity)+""+"��"+imprice+""+"��"+date);
			            go();
			        }else{
			            int cashPay=tempquantity*imprice;
			            iminfo.add("DEL:"+cusName.substring(4)+"��"+commodityName+"��"+number+"��"+tempquantity+"��"+imprice+""+"��"+date+"��"+cashPay+""+"��"+sheetNumber);
			            helper.output(iminfo);
			            System.out.println("��ӳɹ���");
			
			
			            changeCommodity(commodityName,imprice,0,-quantity);
			            changeAccount(-cashPay,0);
			            changeCustomer(cusName.substring(4),-cashPay,0);
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
		default:System.out.println("��ʽ����");
		}
	}
	
	public void changeCommodity(String commodityName,int imprice,int exprice,int quantity){
		Helper helper=new Helper();
		ArrayList<String>info=new ArrayList<String>();
		ArrayList<commodity>com=new  ArrayList<commodity>();
		String newinfo="";
		helper.setFilename("data/commodity.txt");
		info=helper.readfile();
		int n=info.size();
		for(int i=0;i<n;i++){
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
		
		for(int i=0;i<n;i++){
			if(com.get(i).getName().equals(commodityName)){
				com.get(i).setQuantity(com.get(i).getQuantity()+quantity);
				com.get(i).setLatestImportPrice(imprice);
			}
			newinfo=com.get(i).getName()+"��"+com.get(i).getNumber()+"��"+com.get(i).getQuantity()+""+"��"+com.get(i).getDefaultImportPrice()+""+"��"+com.get(i).getDefaultExportPrice()+""+"��"+com.get(i).getLatestImportPrice()+""+"��"+com.get(i).getLatestExportPrice()+"";
		}
		
		helper.split(newinfo);
		int n1=n;
		
		for(int i=0;i<n;i++){
			helper.split(info.get(i));
			String name2=helper.sArray[0];
			//System.out.println(name1+" "+name2);
			if(commodityName.equals(name2)){
				info.remove(i);
				info.add(newinfo);
				//i--;
				n1--;
			}
		}
		if(n1==n){
			System.out.println("ԭ���޴���Ʒ��");
		}
		
		helper.output(info);
		
	
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
		newaccinfo=tempacc.getAllCash()+""+"��"+tempacc.getAllNeedToReceive()+""+"��"+tempacc.getAllNeedToPay()+"";
		
		accinfo.clear();
		accinfo.add(newaccinfo);
		helper.output(accinfo);

		
	}
	
	public void changeCustomer(String cusName,int cashRec,int cashPay){
		Helper helper=new Helper();
		ArrayList<String>info=new ArrayList<String>();
		ArrayList<customer>cus=new ArrayList<customer>();
		String newinfo="";
		helper.setFilename("data/customer.txt");
		info=helper.readfile();
		int n=info.size();
		for(int i=0;i<n;i++){
			customer tempcus=new customer();
			helper.split(info.get(i));
			tempcus.setName(helper.sArray[0]);
			tempcus.setPhoneNumber(helper.sArray[1]);
			tempcus.setNeedToReceive(Integer.parseInt(helper.sArray[2]));
			tempcus.setNeedToPay(Integer.parseInt(helper.sArray[3]));
			tempcus.setTotal(Integer.parseInt(helper.sArray[4]));
			cus.add(tempcus);


		}
		for(int i=0;i<n;i++){
			//System.out.println(cus.get(i).name.equals(cusName));

			if(cus.get(i).getName().equals(cusName)){
				cus.get(i).setNeedToPay(cus.get(i).getNeedToPay()+cashPay);
				cus.get(i).setNeedToReceive(cus.get(i).getNeedToReceive()+cashRec);
				newinfo=cus.get(i).getName()+"��"+cus.get(i).getPhoneNumber()+"��"+cus.get(i).getNeedToReceive()+""+"��"+cus.get(i).getNeedToPay()+"";
			}
					
		}
		if(newinfo.equals("")){
			System.out.println("ԭ���޴˿ͻ���");
		}
		
		helper.split(newinfo);
		String name1=helper.sArray[0];
		
		for(int i=0;i<n;i++){
			helper.split(info.get(i));
			String name2=helper.sArray[0];
			//System.out.println(name1+" "+name2);
			if(name1.equals(name2)){
				info.remove(i);
				info.add(newinfo);
				//i--;
			}
		}
		
		
		helper.output(info);
		
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

}
