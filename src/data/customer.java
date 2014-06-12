package data;

public class customer {
	private String name="";
	private String phoneNumber="";
	private int needToPay=0;
	private int needToReceive=0;
	private int total=0;
	
	public void setName(String name){
		this.name=name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber=phoneNumber;
	}
	
	public String getPhoneNumber(){
		return phoneNumber;
	}
	
	public void setNeedToPay(int needToPay){
		this.needToPay=needToPay;
	}
	
	public int getNeedToPay(){
		return needToPay;
	}
	
	public void setNeedToReceive(int needToReceive){
		this.needToReceive=needToReceive;
	}
	
	public int getNeedToReceive(){
		return needToReceive;
	}
	
	public void setTotal(int total){
		this.total=total;
	}
	
	public int getTotal(){
		return total;
	}

}
