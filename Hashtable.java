/**
 * This class is abstract that allows the creation of hashtables. 
 * 
 * @author Brian Wu
 */
public abstract class Hashtable<T> {
    
    protected HashObject<T>[] hashtable;
    protected int tableSize;
    protected int numberOfInsertions, itemsIn;
    protected int hashTableSize;

    /**
     * The default constructor that takes in values for the size of the array and the size for the hashtable
     * @param size Size of the array. 
     * @param hashTableSize Size of the hashtable.
     */
    public Hashtable(int size, int hashTableSize){
        hashtable = new HashObject[size];
        tableSize = size;
        numberOfInsertions = 0;
        itemsIn = 0;
        this.hashTableSize = hashTableSize;
    }

    /**
     * Abstract method that inserts values into the hashtable.
     * @param item Item to be inserted.
     * @return Location of where the item was inserted.
     * @throws Exception If the number of insertions goes past the size of the hashtable (hashTableSize).
     */
    public abstract int insert(HashObject<T> item) throws Exception;

    /**
     * Abstract method to be implemented in the future.
     * @param item Item to be deleted.
     */
    public abstract void delete(HashObject<T> item);

    /**
     * Abstract method that allows the capabilities to search for an item.
     * @param item Item that is being searched for.
     * @return Integer value of the location where the item is.
     */
    public abstract int search(HashObject<T> item);

    /**
     * Due to the possibility of negative values being used in the primary and secondary hash functions
     * there needs to be a method that counteracts that. This is that method.
     * @param dividend The value that is being divided up.
     * @param divisor The size of the table.
     * @return A value that is used to finding the location for a HashObject.
     */
    protected int positiveMod (int dividend, int divisor) {
        int quotient = dividend % divisor;
        if (quotient < 0)
        quotient += divisor;
        return quotient;
    }
        
    /**
     * This method returns a value that is utlized by both linear probing and double hashing classes.
     * @param key The key that is being set.
     * @return Value for H(1).
     */
    protected int primaryHashFunction(HashObject<T> key){
        return positiveMod(key.hashCode(), tableSize);
    }

    /**
     * This method returns the secondary value for the double hashing class.
     * @param key The key that is being set.
     * @return Value for H(2).
     */
    protected int secondaryHashFunction(HashObject<T> key){
        return 1 + positiveMod(key.hashCode(), tableSize - 2);
    }

    /**
     * This method is a getter for the numberOfInsertions value.
     * @return A integer of the insertions.
     */
    public int getNumberOfInsertions(){
        return this.numberOfInsertions;
    }

    /**
     * This method returns a reference to an element on the hashtable with a provided location
     * @param location The index value of the object.
     * @return A hashobject.
     */
    public HashObject getObject(int location){
        return hashtable[location];
    }

    /**
     * This method is a getter for the itemsIn value.
     * @return An integer of the items.
     */
    public int getItemsIn(){
        return this.itemsIn;
    }

    /**
     * This method returns a copy of the hashtable.
     * @return An array of HashObjects.
     */
    public HashObject<T>[] getHashTable(){
        HashObject[] items = new HashObject[tableSize];

        for(int i = 0; i < tableSize; i++){
            items[i] = hashtable[i];
        }

        return items;
    }

}

