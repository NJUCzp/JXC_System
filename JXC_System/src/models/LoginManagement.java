package models;
import java.util.ArrayList;

import control.Helper;


public class LoginManagement {
	String instruction="";
	String messageText="";
	boolean isLoginSuccessfully=false;
	String position="";
	String name;
	Helper helper=new Helper();
	ArrayList<String>loginfo=new ArrayList<String>();
	public void setInstruction(String instruction){
		this.instruction=instruction;
		helper.setFilename("data/loginData.txt");
		loginfo=helper.readfile();
	}
	public void go(){
		String keyword=instruction.substring(0, 3);
        switch (keyword){
		case "LOG":{
			helper.split(instruction.substring(4));
			String username=helper.sArray[0];
			String password=helper.sArray[1];
			for(int i=0;i<loginfo.size();i++){
				System.out.println(username+"   "+password);
				helper.split(loginfo.get(i));
				System.out.println(helper.sArray[0]+"   "+helper.sArray[1]);
				System.out.println(helper.sArray[0].equals(username)&&helper.sArray[1].equals(password));
				if(helper.sArray[0].equals(username)&&helper.sArray[1].equals(password)){
					System.out.println("find loginfo: "+loginfo.get(i));
					messageText=loginfo.get(i);
					isLoginSuccessfully=true;
					position=helper.sArray[2];
					name=helper.sArray[0];
				}
			}
			if(!isLoginSuccessfully){
				messageText="false";
			}
			System.out.println(name+"  "+position);
			name=null;
			position=null;
			isLoginSuccessfully=false;
			break;
		}
		case "RES":{
			loginfo.add(instruction.substring(4));
			helper.output(loginfo);
			break;
		}
		default:System.out.println("Ö¸Áî´íÎó£¡");
		}

	}
	
	public String getPosition(){
		return position;
	}
	
	public String getName(){
		return name;
	}
	
	public String  getMessageText(){
		return messageText;
	}
	public boolean isSuccessful() {
		// TODO Auto-generated method stub
		return isLoginSuccessfully;
	}
	

}
