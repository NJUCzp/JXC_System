package App;

import java.io.*;
import java.net.Socket;











//import control.JXC_Controller;
import ui.JXC_View;
public class JXC_App {
	private Socket socket;
	private String message;
	private String instruction="";
	private Object[][] messageTable;
	private Object messageObject;
	private BufferedReader reader;
	private PrintWriter writer;
	private ObjectInputStream ois;
	public void initial(){
		try{
			socket=new Socket("127.0.0.1",3000);
			InputStreamReader stream=new InputStreamReader(socket.getInputStream());
			reader=new BufferedReader(stream);
			writer=new PrintWriter(socket.getOutputStream());
			ois=new ObjectInputStream(new DataInputStream(socket.getInputStream()));

		}catch(IOException e){
			System.out.println("IOException:"+e);
		}
		Thread readerThread=new Thread(new Reader());
		readerThread.start();
	}
	
	public void sendMessageText(String message){
		instruction=message;
		System.out.println("send: "+instruction);
		try{
			System.out.println("client send message...");
			writer.println(instruction);
			writer.flush();
		}catch(Exception e){
			System.out.println("Exception:"+e);
		}
	}
	
	public String receiveMessageText(){
		message=messageObject.toString();
		System.out.println("messageReceived: "+message);
		return message;
	}
	
	public Object[][] receiveMessageTable(){
		messageTable=(Object[][])messageObject;
	    return messageTable;
	}
	
	class Reader implements Runnable{
		@Override
		public void run() {
			System.out.println("Thread Reader is running...");
			try{
					System.out.println("Waiting for text...");
					while((messageObject=ois.readObject())!=null){
						messageObject=ois.readObject();
					}
				
			}catch(Exception e){
				e.printStackTrace();
			}// TODO Auto-generated method stub
		}
	}

	public static void main(String[] args) {
	    JXC_App app=new JXC_App();
		JXC_View view=new JXC_View();
	    view.initialApp(app);// TODO Auto-generated method stub
	    app.initial();
	}
}
