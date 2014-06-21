package control;
import models.AccountManagement;
import models.CommodityManagement;
import models.CustomerManagement;
import models.ExportManagement;
import models.ImportManagement;
import models.LoginManagement;
import models.StockManagement;
public class JXC_Controller {
	String instruction;
	String messageText="";
	Object messageTable[][];
	public CommodityManagement commodity=new CommodityManagement();
	public StockManagement stock=new StockManagement();
	public ImportManagement importer=new ImportManagement();
	public ExportManagement exporter=new ExportManagement();
	public CustomerManagement customer=new CustomerManagement();
	public AccountManagement account=new AccountManagement();
	public LoginManagement login=new LoginManagement();
	
	public void setInstruction(String instruction){
		this.instruction=instruction;
		this.go();
	}
	public void setMessageTable(Object[][] table){
		messageTable=table;
	}
	public void setMessageText(String s){
		messageText=s;
	}
	public String getMessageText(){
		return messageText;
	}
	public Object[][] getMessageTable(){
		return messageTable;
	}
	
	public void go(){
		System.out.println("controller get ins: "+instruction);
		String keyword="";
		keyword=instruction.substring(0, 2);
		switch(keyword){
		case "CO":{
			commodity.setInstruction(instruction.substring(10));
			commodity.go();
			if(instruction.substring(10).charAt(0)=='S'||instruction.substring(10).charAt(0)=='F'){
				setMessageTable(commodity.getMessageTable());
			}
			setMessageText(commodity.getMessageText());
			break;
		}
		case "AC":{
			account.setInstruction(instruction.substring(8));
			account.go();
			if(instruction.substring(8).charAt(0)=='D'){
				setMessageTable(account.getMessageTable());
				System.out.println(account.getMessageTable()[0][0]);
			}else{
				setMessageText(account.getMessageText()); 
			}
			break;
		}
		case "CU":{
			customer.setInstruction(instruction.substring(9));
			customer.go();
			if(instruction.substring(9).charAt(0)=='S'||instruction.substring(9).charAt(0)=='F'){
				setMessageTable(customer.getMessageTable());
			}
			setMessageText(customer.getMessageText());
			break;
		}
		case "EX":{
			exporter.setInstruction(instruction.substring(7));
			exporter.go();
			if(instruction.substring(7).charAt(0)=='S'||instruction.substring(7).charAt(0)=='W'){
				setMessageTable(exporter.getMessageTable());
			}
			setMessageText(exporter.getMessageText());
			break;
		}
		case "IM":{
			importer.setInstruction(instruction.substring(7));
			importer.go();
			if(instruction.substring(7).charAt(0)=='S'||instruction.substring(7).charAt(0)=='W'){
				setMessageTable(importer.getMessageTable());
			}
			setMessageText(importer.getMessageText());
			System.out.println("messageText:"+importer.getMessageText());
			break;
		}
		case "ST":{
			stock.setInstruction(instruction);
			stock.go();
			setMessageTable(stock.getMessageTable());
			break;
		}
		case "LO":{
			login.setInstruction(instruction.substring(6));
			login.go();
			//System.out.println(login.getMessageText());
			setMessageText(login.getMessageText());
			break;
		}
		default:System.out.println("∏Ò Ω¥ÌŒÛ£°");
		    break;
		}
	}

}
