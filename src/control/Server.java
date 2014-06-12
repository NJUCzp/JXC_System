package control;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class Server {
    private ServerSocket serversocket;
    PrintWriter writer;
    BufferedReader reader;
    ObjectOutputStream oos;
    String instruction="";
    JXC_Controller controller=new JXC_Controller();
    public Server(){
    	try {
			serversocket=new ServerSocket(3000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void initial(){
    		while(true){
    			try{
	    			Socket socket=serversocket.accept();
	    			writer=new PrintWriter(socket.getOutputStream());
	    			reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    			oos=new ObjectOutputStream(new DataOutputStream(socket.getOutputStream()));
	    			while((instruction=reader.readLine())!=null){
	    				System.out.println("instruction receive: "+instruction);
	    				controller.setInstruction(instruction);
	    				if(instruction.length()<=15){
	    					if(instruction.equals("ACCOUNT_DET:")||instruction.equals("STOCK_SHO")||instruction.equals("COMMODITY_SHO:")||instruction.equals("CUSTOMER_SHO:")||instruction.equals("EXPORT_WHOLE:")||instruction.equals("IMPORT_WHOLE:")){
								System.out.println("server send table...");
								oos.writeObject((Object)controller.getMessageTable());
								oos.flush();
							}else{
								System.out.println("server send message: "+controller.getMessageText());
								oos.writeObject(controller.getMessageText());
						    	oos.flush();
							}	    		
	    				}else{
	    					if(instruction.substring(0, 14).equals("COMMODITY_FIN:")||instruction.substring(0, 13).equals("CUSTOMER_FIN:")||instruction.substring(0, 11).equals("IMPORT_SHO:") ||instruction.substring(0, 11).equals("EXPORT_SHO:")){
	    						System.out.println("server send table...");
								oos.writeObject((Object)controller.getMessageTable());
								oos.flush();
	    					}else{
								System.out.println("server send message: "+controller.getMessageText());
								oos.writeObject(controller.getMessageText());
						    	oos.flush();
							}	    		
	    				}
								
	    			}	    			
    			}catch(Exception e){
    				e.printStackTrace();
    			}
    		}
    }  
	public static void main(String[] args) {
		Server server=new Server();
		server.initial();// TODO Auto-generated method stub

	}

}
