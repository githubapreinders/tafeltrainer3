package afr.tafeltrainer3.client.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import afr.tafeltrainer3.client.ProductIO;
import afr.tafeltrainer3.shared.Product;

public class Prodlijst implements Productlists 
{

ArrayList<Product> productlijst = Productlists.productlijst ;
	
	public Prodlijst()
		{
			makeMap();
		}	

	public void makeMap() 
	{
		for(int index =1 ; index < Productlists.numberOfRecords+1; index++)
		{
			Product p = ProductIO.getProduct(index);
			productlijst.add(p);
		}
	}
	
	public ArrayList<Product> getProductlijst() 
	{
		return productlijst;
	}
}
