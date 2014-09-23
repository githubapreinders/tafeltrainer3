package afr.tafeltrainer3.shared;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;



public class VragenSet implements Serializable    {

	private static final long serialVersionUID = 7199493572623744274L;
	public static Map<Integer, Opgave> opgaven = new HashMap<Integer, Opgave>();

		public VragenSet() {
			
		}
			
	public static void putInHashmap(int id, Opgave opgave) {
		
		opgaven.put(id, opgave);
	}

	public Opgave getOpgave(int id) {
		return opgaven.get(id);
	}

}













	
	