package data;

public class commodity {
	private String name;
	private String number;
	private int quantity;
	private int defaultImportPrice;
	private int defaultExportPrice;
	private int latestImportPrice;
	private int latestExportPrice;
	private int importQuantity;
	private int exportQuantity;
	
	public void setName(String name){
		this.name=name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setNumber(String number){
		this.number=number;
	}
	
	public String getNumber(){
		return number;
	}
	
	public void setQuantity(int quantity){
		this.quantity=quantity;
	}
	
	public int getQuantity(){
		return  quantity; 
	}
	
	public void setDefaultImportPrice(int defaultImportPrice){
		this.defaultImportPrice=defaultImportPrice;
	}
	
	public int getDefaultImportPrice(){
		return defaultImportPrice;
	}
	
	public void setDefaultExportPrice(int defaultExportPrice){
		this.defaultExportPrice=defaultExportPrice;
	}
	
	public int getDefaultExportPrice(){
		return defaultExportPrice;
	}
	
	public void setLatestImportPrice(int latestImportPrice){
		this.latestImportPrice=latestImportPrice;
	}
	
	public int getLatestImportPrice(){
		return latestImportPrice;
	}
	
	public void setLatestExportPrice(int latestExportPrice){
		this.latestExportPrice=latestExportPrice;
	}
	
	public int getLatestExportPrice(){
		return latestExportPrice;
	}
	
	public void setImportQuantity(int importQuantity){
		this.importQuantity=importQuantity;
	}
	
	public int getImportQuantity(){
		return importQuantity;
	}
	
	public void setExportQuantity(int exportQuantity){
		this.exportQuantity=exportQuantity;
	}
	
	public int getExportQuantity(){
		return exportQuantity;
	}

}
