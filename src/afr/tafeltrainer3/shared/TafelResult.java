package afr.tafeltrainer3.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class TafelResult implements IsSerializable 

{
	
	int tafelsoort;
	int score;
	int aantalopgaven;
		
		
		public TafelResult()
		{
			
		}
		
		public TafelResult(int tafelsoort, int score, int aantalopgaven)
		{
			this.tafelsoort=tafelsoort;
			this.score = score;
			this.aantalopgaven = aantalopgaven;
		}

		public int getTafelsoort() {
			return tafelsoort;
		}
		
		public int getScore() {
			return score;
		}
		
		public int getAantalopgaven() {
			return aantalopgaven;
		}
		
}
