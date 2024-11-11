/**
 * This class is for the object HashObject. It allows the creation of the HashObject which stores a generic key value
 * with an overriden equals value that utlizes the hashCode() method. It also stores values like frequencyCount and probeCount
 * in order to keep track of collisons and commonalities.
 * 
 * @author Brian Wu
 */
public class HashObject<T>{
    private T key;
    private int frequencyCount, probeCount;

    /**
     * The default constructor that takes in a generic item which is then stored as the key.
     * @param item A generic that is stored in the HashObject.
     */
    public HashObject(T item){
        key = item;
        frequencyCount = probeCount = 0;
    }

    /**
     * An overidden equals method that handles generic keys using the hashCode() method and
     * verifies them by converting them to their String counterparts.
     * @param compare The generic object that is comparing.
     * @return Boolean if it equals.
     */
    public boolean equals(HashObject<T> compare){

        if(this.key.hashCode() == compare.getKey().hashCode()){
            String string1, string2;
            
            string1 = key.toString();
            string2 = compare.getKey().toString();
            boolean theSame = true;

            for(int i = 0; i < string1.length(); i++){ //checking if both values are truly equal
                if(string1.charAt(i) != string2.charAt(i)){
                    theSame = false;
                }
            }

            return theSame;
        }
        return false;
    }

    /**
     * This method increments the probeCount when there is a collision on the hashtable.
     */
    public void incrementProbeCount(){ //when there is a collision
        probeCount++;
    }

    /**
     * This method increments the frequencyCount when the HashObject finds the same value in the hashtable.
     */
    public void incrementFrequencyCount(){ //when the value is the same
        frequencyCount++;
    }

    /**
     * This method returns the hashCode value from the generic key value which is used in the equals() method.
     */
    public int hashCode(){
        return key.hashCode();
    }

    /**
     * This method returns the string output of the HashObject.
     */
    public String toString(){
        return (key.toString() + " " + probeCount + " " + frequencyCount);
    }

    /**
     * This method returns the key as an object type.
     * @return Object key.
     */
    public Object getKey(){
        return key;
    }

    /**
     * This method is a getter for the frequencyCount.
     * @return frequencyCount.
     */
    public int getFrequencyCount(){
        return frequencyCount;
    }

    /**
     * This method is a getter for the probeCount.
     * @return probeCount.
     */
    public int getProbeCount(){
        return probeCount;
    }


}
