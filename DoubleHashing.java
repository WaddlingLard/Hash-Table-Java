/**
 * This class is a child class of the hashtable class that utlizes double hashing.
 * 
 * @author Brian Wu
 */
public class DoubleHashing extends Hashtable{

    /**
     * The default constructor that calls super().
     * @param size Size of the array.
     * @param hashTableSize Size of the hashtable.
     */
    public DoubleHashing(int size, int hashTableSize){
        super(size, hashTableSize);
    }

    @Override
    public int insert(HashObject item) throws Exception{
        int primaryFirst, primarySecond, location;
        primaryFirst = primaryHashFunction(item);
        primarySecond = secondaryHashFunction(item);
        location = doubleHashing(item, primaryFirst, primarySecond);
        boolean inserted = false;

        while(!inserted){

            if(itemsIn == hashTableSize){
                throw new Exception("hash table overflow");
            }

            if(hashtable[location] == null){ //new location found
                hashtable[location] = item;
                inserted = true;
                itemsIn++;

                hashtable[location].incrementFrequencyCount();
                hashtable[location].incrementProbeCount();

            }else if (hashtable[location].equals(item)){ //when there is a duplicate
                hashtable[location].incrementFrequencyCount();
                inserted = true;
            }else{ //when the current location is filled and doesn't equal to the value that is being inserted
                item.incrementProbeCount();
                location = doubleHashing(item, primaryFirst, primarySecond);
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
     * This method combines all the values together in order to design a double hashing technique
     * to insert values in the hashtable.
     * @param item Item that is being inserted.
     * @param first PrimaryHashFunction value.
     * @param second SecondaryHashFunction value.
     * @return Location where the next probe should take place on the hashtable.
     */
    private int doubleHashing(HashObject item, int first, int second){ //this method could be wrong
        int num1, num2;
        num1 = first;
        num2 = second;
        return positiveMod(num1 + (item.getProbeCount() * num2), tableSize);
    }
}
