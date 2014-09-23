package afr.tafeltrainer3.shared;

import afr.tafeltrainer3.client.tafeltrainer3messages;
import afr.tafeltrainer3.client.shop.Shop;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ProductWidget extends Composite implements Cloneable
{
private Button inwagentje;
private HorizontalPanel hpanel = new HorizontalPanel();
private Image productimage;
private Label prijs;
public int price;
private Product product;
private Shop shop;
public String url;
private tafeltrainer3messages messages;
private VerticalPanel vpanel0= new VerticalPanel();
private VerticalPanel vpanel=new VerticalPanel();	

	//wordt als kopie aan de tafeltrainer toegevoegd
	public ProductWidget(String url)
	{
		this.vpanel0.setBorderWidth(1);
		initWidget(vpanel0);
		this.vpanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		this.vpanel.setStyleName("productframelarger");
		this.productimage = new Image(url);
		this.productimage.setStyleName("productimagelarger");
		this.vpanel.add(productimage);
		this.vpanel0.add(vpanel);
	}
	
	//wordt als eerste vanuit de shop aangemaakt
	public ProductWidget(Product product, Shop shop,tafeltrainer3messages messages)
	{
		initWidget(vpanel0);
		this.productimage = new Image(product.getUrl());
		this.price = product.getPrice();
		this.url = product.getUrl();
		this.shop = shop;
		this.product = product;
		this.messages=messages;
		this.vpanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		this.vpanel.setStyleName("productframe");
		this.productimage.setStyleName("productimage");
		this.productimage.addClickHandler(new InwagentjeClickHandler());
		this.inwagentje = new Button(messages.winkeltje_productwidget_inwagentje());
		this.inwagentje.setStyleName("smallbutton");
		this.inwagentje.addClickHandler(new InwagentjeClickHandler());
		this.prijs = new Label( String.valueOf(price)+" credits");
		this.prijs.setStyleName("productlabel");
		this.prijs.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		this.vpanel.add(productimage);
		this.vpanel.add(prijs);
		this.hpanel.add(inwagentje);
		this.vpanel.add(hpanel);
		this.vpanel0.add(vpanel);
	}
	
	//verwijdert prijs en button van de oorspronkelijke widget
	public void changeWidget()
	{
		this.prijs.removeFromParent();
		this.inwagentje.removeFromParent();
		this.vpanel.setStyleName("productframelarger");
		this.productimage.setStyleName("productimagelarger");
	}
	
	//maakt een nieuwe widget die aan het winkelwagentje wordt toegevoegd
	private class InwagentjeClickHandler implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event) 
		{
			ProductWidget p = new ProductWidget(getProduct(),shop,messages);
			shop.addToCart(p);
		}
	}
	public ProductWidget getProductWidget()
	{
		return this;
	}
	
	public Product getProduct() {
		return this.product;
	}
	
	public int getPrice() {
		return price;
	}

	public String getUrl() {
		return url;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
}
