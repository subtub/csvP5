package de.fhpotsdam.util.map;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MapUtil
{
	/**
	 * Sorts a Map from smallest to biggest. 
	 * Seems like the best answer found on a <a href="http://stackoverflow.com/questions/109383/how-to-sort-a-mapkey-value-on-the-values-in-java">Stack Overflow discussion</a>
	 * @param map map to sort by value
	 * @return the sorted map as new LinkedHashMap
	 */
    public static <K, V extends Comparable<? super V>> Map<K, V> 
        sortByValue( Map<K, V> map )
    {
        List<Map.Entry<K, V>> list =
            new LinkedList<Map.Entry<K, V>>( map.entrySet() );
        Collections.sort( list, new Comparator<Map.Entry<K, V>>()
        {
            public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
            {
                return (o1.getValue()).compareTo( o2.getValue() );
            }
        } );

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list)
        {
            result.put( entry.getKey(), entry.getValue() );
        }
        return result;
    }
}
