package afr.tafeltrainer3.client;

import afr.tafeltrainer3.shared.TafelResult;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TafelResultWidget extends Composite

{
private VerticalPanel vpanel1 = new VerticalPanel();
private HorizontalPanel hpanel0= new HorizontalPanel();
private HorizontalPanel hpanel1= new HorizontalPanel();
private FlowPanel fpanel1 = new FlowPanel();
private Label lbltafelnummer;
private Label lblhowmuch;
private Label lblhowgood;
private TafelResult tafelresult;

public TafelResultWidget(TafelResult tafelresult)
	{
		//hpanel1.setCellVerticalAlignment(hpanel1, HasVerticalAlignment.ALIGN_MIDDLE);
		this.tafelresult = tafelresult;
		initWidget(hpanel0);
		lbltafelnummer = new Label("Tafel van " + String.valueOf(tafelresult.getTafelsoort()) + ": ");
		makeGraph(tafelresult.getScore());
		lblhowmuch = new Label(String.valueOf(tafelresult.getAantalopgaven())+" sommen, " + 
		tafelresult.getScore() + "% goed");
		lblhowmuch.setStyleName("kleinelabels");
		lblhowgood = new Label(",beheersingsniveau:" + determineBeheersingsNiveau(tafelresult.getScore()));
		lblhowgood.setStyleName("kleinelabels");
		vpanel1.setCellVerticalAlignment(lbltafelnummer, HasVerticalAlignment.ALIGN_BOTTOM);
		vpanel1.setCellVerticalAlignment(fpanel1, HasVerticalAlignment.ALIGN_MIDDLE);
		vpanel1.setCellVerticalAlignment(hpanel1, HasVerticalAlignment.ALIGN_TOP);
		vpanel1.add(fpanel1);
		vpanel1.add(lbltafelnummer);
		hpanel1.add(lblhowmuch);
		hpanel1.add(lblhowgood);
		vpanel1.add(hpanel1);
		hpanel0.add(vpanel1);
	}

/**
 * 
 * @param score maakt een balkje dat grafisch weergeeft hoeveel procent beheersingsniveau er
 * voor een bepaalde tafel is
 */
	private void makeGraph(int score) 
		{
			 int groenblok = score/5;
			 int roodblok = 20-groenblok;
			 for(int i =0 ;i<groenblok;i++)
			 {
				 Button groen = new Button();
				 groen.setStyleName("groenblok");
				 fpanel1.add(groen);
			 }
			 for(int j =0;j<roodblok;j++)
			 {			 
				 Button rood = new Button();
				 rood.setStyleName("roodblok");
				 fpanel1.add(rood);
			 }
		}
	
	private String determineBeheersingsNiveau(int score)
	{
		int p =0;
		String level = "";
		if(score<=80)p =5;
		if(score<=85&&score>80)p=4;
		if(score<=90&&score>85)p=3;
		if(score<=95&&score>90)p=2;
		if(score>95)p=1;
		switch(p)
		{
		case (1) : level = "Uitstekend";this.lbltafelnummer.setStyleName("kleinelabelsuitstekend"); break;
		case (2) : level = "Goed";this.lbltafelnummer.setStyleName("kleinelabelsgoed"); break;
		case (3) : level = "Voldoende";this.lbltafelnummer.setStyleName("kleinelabelsvoldoende"); break;
		case (4) : level = "Matig"; this.lbltafelnummer.setStyleName("kleinelabelsmatig");break;
		case (5) : level = "Zwak";this.lbltafelnummer.setStyleName("kleinelabelszwak"); break;
		 default : level = "Ondetermineerbaar";this.lbltafelnummer.setStyleName("kleinelabels");break;
		
		}
		return level;
	}

	
}
