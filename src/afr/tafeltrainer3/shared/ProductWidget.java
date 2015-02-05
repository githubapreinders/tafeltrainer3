package afr.tafeltrainer3.shared;

import afr.tafeltrainer3.client.MyResources;
import afr.tafeltrainer3.client.tafeltrainer3messages;
import afr.tafeltrainer3.client.shop.Shop;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
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
private MyResources resources ;

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
		
		resources = GWT.create(MyResources.class);
		this.productimage = new Image(findResource(getresourcename(product.getUrl())));
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
	
	
	public String getresourcename(String url)
	{
		StringBuilder s = new StringBuilder(url);
		s.delete(url.length()-4, url.length());
		s.delete(0, s.lastIndexOf("/")+1);
		return s.toString();
		
		
		
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
	
	//gets the thumbnail from a resourcefile in the client instead of making a call to the server.
	
	public ImageResource findResource(String identifier)
	{
		switch(identifier)
		{
		case "alfaromeo": return resources.alfaromeo(); 
		case "audi": return resources.audi(); 
		case "bentley": return resources.bentley(); 
		case "bmw": return resources.bmw(); 
		case "bugatti": return resources.bugatti(); 
		case "buick": return resources.buick(); 
		case "chevrolet": return resources.chevrolet(); 
		case "lexus": return resources.lexus(); 
		case "mercedes": return resources.mercedes(); 
		case "pontiac": return resources.pontiac(); 
		case "porsche": return resources.porsche(); 
		case "rollsroyce": return resources.rollsroyce(); 
		
		case "amethist": return resources.amethist(); 
		case "diamant": return resources.diamant(); 
		case "goud": return resources.goud(); 
		case "kristal": return resources.kristal(); 
		case "robijn": return resources.robijn(); 
		case "saffier": return resources.saffier(); 
		case "smaragd": return resources.smaragd(); 
		
		case "beren_op_paarden": return resources.beren_op_paarden(); 
		case "boze_uil": return resources.boze_uil(); 
		case "emoe": return resources.emoe(); 
		case "geit_achterop_fiets": return resources.geit_achterop_fiets(); 
		case "grappig_aapje": return resources.grappig_aapje(); 
		case "hond_in_koelkast": return resources.hond_in_koelkast(); 
		case "hond_met_bril": return resources.hond_met_bril(); 
		case "poes_in_wc": return resources.poes_in_wc(); 
		case "poes_wasbeertjes": return resources.poes_wasbeertjes(); 
		case "poes": return resources.poes(); 
		case "verklede_hond": return resources.verklede_hond(); 
		
		case "egelbaby": return resources.egelbaby(); 
		case "gekko": return resources.gekko(); 
		case "gorilla": return resources.gorilla(); 
		case "leguaan": return resources.leguaan(); 
		case "rhinoceros": return resources.rhinoceros(); 
		case "rups": return resources.rups(); 
		case "slang": return resources.slang(); 
		case "tijger": return resources.tijger(); 
		case "walrus": return resources.walrus(); 
		case "zeehondpuppy": return resources.zeehondenpuppy(); 
		
		case "kaketoe": return resources.kaketoe(); 
		case "kolibri": return resources.kolibri(); 
		case "papegaai": return resources.papegaai(); 
		case "pauw": return resources.pauw(); 
		case "roodkuifpapegaai": return resources.roodkuifpapegaai(); 
		case "uil": return resources.uil(); 
		case "uilskuiken": return resources.uilskuiken(); 
		case "zwaluw": return resources.zwaluw(); 
		
		case "asterix": return resources.asterix(); 
		case "donald_duck": return resources.donald_duck(); 
		case "garfield": return resources.garfield(); 
		case "homersimpson": return resources.homersimpson(); 
		case "lucky_luke": return resources.lucky_luke(); 
		case "rozepanter": return resources.rozepanter(); 
		case "scoobydoo": return resources.scoobydoo(); 
		case "shrek": return resources.shrek(); 
		case "snoopy": return resources.snoopy(); 
		case "spongebob": return resources.spongebob();
		
		case "Arjen_Robben": return resources.Arjen_Robben();
		case "Cristiano_Ronaldo": return resources.Cristiano_Ronaldo();
		case "Daley_Blind": return resources.Daley_Blind();
		case "Eden_Hazard": return resources.Eden_Hazard();
		case "Gareth_Bale": return resources.Gareth_Bale();
		case "Joe_Hart": return resources.Joe_Hart();
		case "Jordy_Clasie": return resources.Jordy_Clasie();
		case "Karim_Benzema": return resources.Karim_Benzema();
		case "Lionel_Messi": return resources.Lionel_Messi();
		case "Luis_Suares": return resources.Luis_Suares();
		case "Robert_Lewandowski": return resources.Robert_Lewandowski();
		case "Zlatan_Ibrahimovic": return resources.Zlatan_Ibrahimovic();
		case "Robin_van_Persie": return resources.Robin_van_Persie();
		
		case "amsterdam": return resources.amsterdam();
		case "beijing": return resources.beijing();
		case "istanboel": return resources.istanboel();
		case "londen": return resources.londen();
		case "mexicocity": return resources.mexicocity();
		case "moskou": return resources.moskou();
		case "newyork": return resources.newyork();
		case "parijs": return resources.parijs();
		case "riodejaneiro": return resources.riodejaneiro();
		case "sidney": return resources.sidney();
		case "singapore": return resources.singapore();
		
		case "axolotl": return resources.axolotl();
		case "dolfijn": return resources.dolfijn();
		case "koraalduivel": return resources.koraalduivel();
		case "mandarijnvis": return resources.mandarijnvis();
		case "octopus": return resources.octopus();
		case "papegaaivis": return resources.papegaaivis();
		case "pincetvis": return resources.pincetvis();
		case "zeehond": return resources.zeehond();
		case "zeeschildpad": return resources.zeeschildpad();
		
		} 
		return resources.kids();
	}
	
}
