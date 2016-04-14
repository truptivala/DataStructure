
public class TMHashTable3 {
    // for simplicity size is taken as 2^4
	
    private static final int SIZE = 37;
    
    private Entry table[] = new Entry[SIZE];
    
    public int hashCode(String k) {
        final int seed = 37;
        int result = 1;
        result = seed * result + ((k == null) ? 0 : k.hashCode());
        result = seed * result;
        return result;
    }
    
    class Entry {
        final String key;
        String value;
        Entry next;
 
        Entry(String k, String v) {
            key = k;
            value = v;
        }
 
        public String getValue() {
            return value;
        }
 
        public void setValue(String value) {
            this.value = value;
        }
 
        public String getKey() {
            return key;
        }
    }
 
    /**
     * Returns the entry associated with the specified key in the
     * HashMap.  Returns null if the HashMap contains no mapping
     * for the key.
     */
    public Entry get(String k) {
    	int index = 0;
    	index = hashCode(k);
        Entry e = table[index];
 
        // if bucket is found then traverse through the linked list and
        // see if element is present
        while(e != null) {
            if(e.key.equals(k)) {
                return e;
            }
            e = e.next;
        }
        return null;
    }
 
    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old
     * value is replaced.
     */
    public void put(String k, String v) {
    	
    	int index = 0;
    	index = hashCode(k);
        Entry e = table[index];
        
        if(e != null) {
            // it means we are trying to insert duplicate
            // key-value pair, hence overwrite the current
            // pair with the old pair
            if(e.key.equals(k)) {
                e.value = v;
            } else {
                // traverse to the end of the list and insert new element 
                // in the same bucket
                while(e.next != null) {
                    e = e.next;
                }
                Entry entryInOldBucket = new Entry(k, v);
                e.next = entryInOldBucket;
            }
        } else {
            // new element in the map, hence creating new bucket
            Entry entryInNewBucket = new Entry(k, v);
            table[index] = entryInNewBucket;
        }
    }
 
    // for testing our own map
    public static void main(String[] args) {
        TMHashTable3 tmhm = new TMHashTable3();
        
        tmhm.put("1", "112334");
        tmhm.put("2", "2");
        tmhm.put("2", "3");
        tmhm.put("9", "Ketanvalajskdjksjdkjsndkjs");
 
        Entry e = tmhm.get("2");
        System.out.println(""+e.getValue());
    }
}