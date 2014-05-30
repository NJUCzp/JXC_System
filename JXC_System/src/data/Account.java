package data;

public class Account {
	private int allCash=0;
	private int allNeedToPay=0;
	private int allNeedToReceive=0;
	
	public void setAllCash(int allCash){
		this.allCash=allCash;
	}
	public int getAllCash(){
		return allCash;
	}
	
	public void setAllNeedToPay(int allNeedToPay){
		this.allNeedToPay=allNeedToPay;
	}
	public int getAllNeedToPay(){
		return allNeedToPay;
	}
	
	public void setAllNeedToReceive(int allNeedToReceive){
		this.allNeedToReceive=allNeedToReceive;
	}
	public int getAllNeedToReceive(){
		return allNeedToReceive;
	}
	
	

}
