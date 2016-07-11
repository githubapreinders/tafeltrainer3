package afr.tafeltrainer3.client.events;

import java.io.Serializable;
import java.util.ArrayList;

import afr.tafeltrainer3.client.shop.Product;

public class EventProductsRetrieved extends DataEvent implements Serializable
{

private static final long serialVersionUID = 3757517060264068516L;
private ArrayList<Product> products;

	public EventProductsRetrieved()
	{
		
	}

	public ArrayList<Product> getProducts() 
	{
		return products;
	}

	public void setProducts(ArrayList<Product> products) 
	{
		this.products = products;
	}


}
