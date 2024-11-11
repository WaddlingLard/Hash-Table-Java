/**
 * This class is a child class of the hashtable class that utlizes linear probing.
 * 
 * @author Brian Wu
 */
public class LinearProbing extends Hashtable{

    /**
     * The default constructor that calls super().
     * @param size Size of the array.
     * @param hashTableSize Size of the hashtable.
     */
    public LinearProbing(int size, int hashTableSize){
        super(size, hashTableSize);
    }

    @Override
    public int insert(HashObject item) throws Exception{
        int primary, location;
        primary = primaryHashFunction(item);
        location = linearProbing(item, primary);
        boolean inserted = false;
        
        while(!inserted){ //inserting value into hashtable
            
            if(itemsIn == hashTableSize){
                throw new Exception("hash table overflow");
            }

            if(hashtable[location] == null){
                hashtable[location] = item;
                inserted = true;
                itemsIn++;

                hashtable[location].incrementFrequencyCount();
                hashtable[location].incrementProbeCount();

            }else if(hashtable[location].equals(item)){
                hashtable[location].incrementFrequencyCount();
                inserted = true;
            }else{
                item.incrementProbeCount();
                location = linearProbing(item, primary);
            }
        }
        numberOfInsertions++;
        return location;
    }

    @Override
    public int search(HashObject item) {
        for(int i = 0; i < tableSize; i++){
            if(hashtable[i].equals(item)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public void delete(HashObject item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    /**
     * This method combines all the values together in order to design a linear probing technique
     * to insert values in the hashtable.
     * @param item Item that is being inserted.
     * @param primary PrimaryHashFunction value.
     * @return Location where the next probe should take place on the hashtable.
     */
    private int linearProbing(HashObject item, int primary){
        return positiveMod((primary + item.getProbeCount()), tableSize);
    }
}

