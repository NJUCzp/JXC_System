package ui;
import java.awt.Image;

import App.JXC_App;

public class JXC_View {
	String instruction="";
	String messageText="";
	Object[][] messageTable;
	JXC_App app;
	
	public void initialApp(JXC_App app){
		this.app=app;
		mainFrame main=new mainFrame();
		//mainGUI maingui=new mainGUI(main);
		LoginGUI login=new LoginGUI(this,main);
		//main.setContentPane(maingui);
		main.setContentPane(login);
	}
	
    public void setInstruction(String s){
		instruction=s;
		System.out.println("set: "+instruction);
		app.sendMessageText(instruction);
		//System.out.println("view"+instruction);
	}
	/*public String getInstruction(){
		instruction=app.receiveMessageText();
		return instruction;
	}*/
	/*public void sendMessageText(String s){
		app.sendMessageText(s);
	}*/
	/*public void setMessageText(String s){
		messageText=s;
	}*/
	public String receiveMessageText(){
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		messageText=app.receiveMessageText();
		System.out.println("viewMessageReceived: "+messageText);
		return messageText;
	}
	
	public Object[][] receiveMessageTable(){
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		messageTable=app.receiveMessageTable();
		return messageTable;
	}

}
