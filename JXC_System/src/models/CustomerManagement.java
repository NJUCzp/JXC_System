package models;
import java.util.ArrayList;

import control.Helper;
import data.customer;


public class CustomerManagement {
	String instruction;
	String messageText="";
	Object[][] messageTable;
	ArrayList<String>info=new ArrayList<String>();
	ArrayList<customer>cus=new  ArrayList<customer>();
	Helper helper=new Helper();
	public void setInstruction(String instruction){
		this.instruction=instruction;
		helper.setFilename("data/customer.txt");
		info=helper.readfile();
		int n=info.size();
		cus.clear();
		if(!info.get(0).equals("")){
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
		}else{
			info.clear();
		}
	}
	
	public void go(){
		char keyword=instruction.charAt(0);
		switch (keyword){
		case 'A':{
			String s=instruction.substring(4)+"��0��0��0";
			boolean alreadyHere=false;
			helper.split(s);
			for(int i=0;i<cus.size();i++){
				if(cus.get(i).getName().equals(helper.sArray[0])){
					alreadyHere=true;
				}
			}
			if(!alreadyHere){
				info.add(s);
				System.out.println("�ɹ���ӣ�");
				messageText="graphics/success_add.png";
			}else{
				messageText="graphics/commodity_error_add.png";
			}
			//System.out.println(s);
			
			helper.output(info);
			break;
		}
		case 'D':{
			String s=instruction.substring(4);
			helper.split(s);
			String name1=helper.sArray[0];
			int n=info.size();
			int n1=n;
			//System.out.println(name1);
			for(int i=0;i<n;i++){
				String name2=cus.get(i).getName();
				//System.out.println(name2);
				if(name1.equals(name2)){
					info.remove(i);
    				i--;
    				n--;
    				break;
				}
			}
			if(n1==n){
				messageText="graphics/customer/customer_error_del.png";
				System.out.println("ɾ��ʧ�ܣ�");
			}else{
				messageText="graphics/success_del.png";
				System.out.println("�Ѵӿͻ��б���ɾ����");
			} 
			helper.output(info);
			break;
		}
		case 'U':{
			String s=instruction.substring(4);
			helper.split(s);
			String name1=helper.sArray[0];
			int n=info.size();
			int n1=n;
			
			for(int i=0;i<n;i++){
				helper.split(info.get(i));
				String name2=helper.sArray[0];
				String needToRec=helper.sArray[2];
				String needToPay=helper.sArray[3];
				String total=helper.sArray[4];
				if(name1.equals(name2)){
					info.remove(i);
    				info.add(s+"��"+needToRec+"��"+needToPay+"��"+total);
    				i--;
    				n--;
				}
			}
			if(n1==n){
				messageText="graphics/commodity/commodity_error_upd.png";
				System.out.println("ԭ���޴˿ͻ���");
			}else{
				messageText="graphics/success_del.png";
				System.out.println("�ѳɹ����£�");
			} 
			helper.output(info);
			break;
			
		}
		case 'F':{
			String s=instruction.substring(4);
			helper.split(s);
			String name1=helper.sArray[0];
			int n=info.size();
			
			for(int i=0;i<n;i++){
				helper.split(info.get(i));
				String name2=helper.sArray[0];
				if(name1.equals(name2)){
					System.out.println(info.get(i));
					messageTable=new Object[1][6];
					messageTable[0][0]=new Boolean(false);
					messageTable[0][1]=cus.get(i).getName();
					messageTable[0][2]=cus.get(i).getPhoneNumber();
					messageTable[0][3]=cus.get(i).getNeedToReceive()+"";
					messageTable[0][4]=cus.get(i).getNeedToPay()+"";
					messageTable[0][5]=cus.get(i).getTotal()+"";
					//messageText="<html>"+"�ͻ�������"+name2+"<br>"+"��ϵ��ʽ�� "+cus.get(i).phoneNumber+"<br>"+"Ӧ���˿�� "+cus.get(i).needToReceive+"<br>"+"Ӧ���˿��"+cus.get(i).needToPay+"<br>"+"�ϼƣ�"+cus.get(i).total+"</html>";
				}
			}
			break;
		}
		case 'S':{
			int n=info.size();
			messageTable=new Object[n][6];
			for(int i=0;i<n;i++){
				messageTable[i][0]=new Boolean(false);
				messageTable[i][1]=cus.get(i).getName();
				messageTable[i][2]=cus.get(i).getPhoneNumber();
				messageTable[i][3]=cus.get(i).getNeedToReceive()+"";
				messageTable[i][4]=cus.get(i).getNeedToPay()+"";
				messageTable[i][5]=cus.get(i).getTotal()+"";
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
