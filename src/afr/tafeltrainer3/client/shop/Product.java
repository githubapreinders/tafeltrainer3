package afr.tafeltrainer3.client.shop;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Product implements IsSerializable

{

/**
 * 
 */
private static final long serialVersionUID = -6790735301799433101L;
private int id;
private String url;
private String message;
private int price;
private Productcategorie productcategorie;
	
	

	public Product()
	{
		
	}
	
	public Product(int id,String url, int price, Productcategorie cat)
	{
		this.id = id;
		this.url = url;
		this.price = price;
		this.productcategorie = cat;
		this.message = "";
	}
	
	public Product(String url, String message, String categorie)
	{
		this.url = url;
		this.message = message;
		this.productcategorie = Productcategorie.valueOf(categorie);
	}
	
	
	public Productcategorie getProductcat() {
		return productcategorie;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
