package afr.tafeltrainer3.client;

import java.util.*;

public class Oefen
{

	public static void main(String[] args)
	{
		Map<Integer, Integer> map = new HashMap<Integer,Integer>();
		map.put(0, 2);
		map.put(1, 0);
		map.put(2, 2);
		map.put(3, 1);
		map.put(4, 2);
		map.put(5, 0);
		map.put(6, 2);
		map = MapUtil.sortByValue(map);
		for(Map.Entry<Integer, Integer> entry : map.entrySet())
		{
			System.out.println(entry.getKey() + "value: "+ entry.getValue());
		}
		
		
	}
}
