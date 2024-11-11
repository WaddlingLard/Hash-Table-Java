import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;

/**
 * A driver class that powers the whole program allowing usage of hashtables and hash objects. There are several ways to interact
 * with this class.
 * 
 * Usage: java HashtableTest <dataType> <loadFactor> [<debugLevel>]
 *      <dataSource>: 1 ==> random numbers
                      2 ==> date value as a long
                      3 ==> word list
        <loadFactor>: The ratio of objects to table size, 
                       denoted by alpha = n/m
        <debugLevel>: 0 ==> print summary of experiment
                      1 ==> save the two hash tables to a file at the end
                      2 ==> print debugging output for each insert

 * 
 * @author Brian Wu
 */
public class HashtableTest {
    
    private static final int DATATYPE_INTEGERS = 1;
    private static final int DATATYPE_DATES = 2;
    private static final int DATATYPE_WORDS = 3;
    private static final int INSERTION_OF_VALUES = Integer.MAX_VALUE;

    private static final String COMMAND_USAGE = "Usage: java HashtableTest <dataType> <loadFactor> [<debugLevel>]" + "\n"
    + "       " + "<dataSource>: 1 ==> random numbers" + "\n"
    + "       		   " + "2 ==> date value as a long" + "\n"
    + "       		   " + "3 ==> word list" + "\n"
    + "       " + "<loadFactor>: The ratio of objects to table size," + "\n" 
    + "                       " + "denoted by alpha = n/m" + "\n"
    + "       " + "<debugLevel>: 0 ==> print summary of experiment" + "\n"
    + "                     " + "1 ==> save the two hash tables to a file at the end" + "\n"
    + "                     " + "2 ==> print debugging output for each insert";

    private static final int SUMMARY_DEBUG = 0;
    private static final int DUMP_FILE_DEBUG = 1;
    private static final int INSERT_DEBUG = 2;
    private static int debugLevel;

    private static int linearUniqueInserts = 0;
    private static int doubleUniqueInserts = 0;
    private static int numberInserted = 0;

    public static void main(String[] args){

        TwinPrimeGenerator generate = new TwinPrimeGenerator();
        DecimalFormat df = new DecimalFormat("0.00");
        Random numberGenerator = new Random();
        Scanner wordReader, wordReader2;
        Hashtable linearP;
        Hashtable doubleP;
        HashObject insert;
        int dataType, isDuplicate;
        debugLevel = 0; //preemptive set
        String dataTypeName = "";
        boolean correctFormat = false;

        if(args.length == 2 || args.length == 3){
            if(args.length == 3){ //set debug level
                debugLevel = Integer.parseInt(args[2]);
            }
            int m = TwinPrimeGenerator.generateTwinPrime(95500, 96000); //size generation of table
            int n = (int)  Math.ceil(Double.parseDouble(args[1]) * m);

            linearP = new LinearProbing(m, n);
            doubleP = new DoubleHashing(m, n);

            dataType = Integer.parseInt(args[0]);
            if(dataType == DATATYPE_INTEGERS){ //datatype is using integer

                correctFormat = true;
                dataTypeName = "Integers";
                int value;

                for(int i = 0; i < INSERTION_OF_VALUES ; i++){

                    value = numberGenerator.nextInt();

                    HashObject temp1, temp2, grabber1, grabber2;
                    temp1 = new HashObject(value);
                    temp2 = new HashObject(value);

                    try {
                        isDuplicate = linearP.insert(temp1);
                        grabber1 = linearP.getObject(isDuplicate);
                        linearPrintInsert(grabber1, isDuplicate);

                        isDuplicate = doubleP.insert(temp2);
                        grabber2 = doubleP.getObject(isDuplicate);
                        doublePrintInsert(grabber2, isDuplicate);
                    } catch (Exception e) {
                        break;
                    }
                }         

            }else if(dataType == DATATYPE_DATES){ //datatype is using dates

                dataTypeName = "Dates";
                long current = new Date().getTime();
                correctFormat = true;

                for(int i = 0; i < INSERTION_OF_VALUES; i++){
                    current += 1000;
                    Date date = new Date(current);
                    insert = new HashObject(date);

                    try {
                        isDuplicate = linearP.insert(insert);
                        HashObject temp = linearP.getObject(isDuplicate);
                        linearPrintInsert(temp, isDuplicate);
                    } catch (Exception e) {
                        break;
                    }
                }

                for(int i = 0; i < INSERTION_OF_VALUES; i++){ 
                    current += 1000;
                    Date date = new Date(current);
                    insert = new HashObject(date);

                    try {
                        isDuplicate = doubleP.insert(insert);
                        HashObject temp = doubleP.getObject(isDuplicate);
                        doublePrintInsert(temp, isDuplicate);
                    } catch (Exception e) {
                        break;
                    }
                }

            }else if(dataType == DATATYPE_WORDS){ //datatype is using words

                correctFormat = true;
                dataTypeName = "Word-List";
                File words = new File("word-list.txt");
                try{
                    wordReader = new Scanner(words);
                    String word;
                    
                    for(int i = 0; i < INSERTION_OF_VALUES; i++){
                        word = wordReader.nextLine();
                        insert = new HashObject(word);

                        try {
                            isDuplicate = linearP.insert(insert);
                            HashObject temp = linearP.getObject(isDuplicate);
                            numberInserted++;
                            linearPrintInsert(temp, isDuplicate);
                        } catch (Exception e) {
                            break;
                        }  
                    }

                    numberInserted = 0;
                    wordReader2 = new Scanner(words);

                    // the lines commented below are a method that i implemented where all the
                    // data that is produced in debuglevel 2 is dumped into a file

                    // File doubleTranscriptDump = new File("doubleTranscriptDump.txt");

                    // if(doubleTranscriptDump.exists()){
                    //     doubleTranscriptDump.delete();
                    // }

                    // PrintWriter doubleWriterDump = new PrintWriter(doubleTranscriptDump);

                    for(int i = 0; i < INSERTION_OF_VALUES; i++){ 
                        word = wordReader2.nextLine();
                        // doubleWriterDump.println(word);
                        insert = new HashObject(word);
                        // doubleWriterDump.println(insert.getKey());

                        try {
        
                            isDuplicate = doubleP.insert(insert);
                            HashObject temp = doubleP.getObject(isDuplicate);
                            numberInserted++;

                            // if(temp.getFrequencyCount() == 1){
                            //     doubleWriterDump.println(numberInserted + ": DOUBLE HASHING: SUCCESSFUL INSERTION OF ELEMENT " + temp.getKey() + " at " + isDuplicate + "\n");
                            // }else{
                            //     doubleWriterDump.println(numberInserted + ": DOUBLE HASHING: DUPLICATE INSERTION OF ELEMENT " + temp.getKey() + " at " + isDuplicate + "\n");
                            // }

                            doublePrintInsert(temp, isDuplicate);
                        } catch (Exception e) {
                            break;
                        }
                    }

                    // doubleWriterDump.close();

                }catch(FileNotFoundException e){
                    System.out.println(e);
                }
            }else{ //value isn't utilized
                System.out.println(COMMAND_USAGE);
            }

            HashObject[] linearH = linearP.getHashTable();
            HashObject[] doubleH = doubleP.getHashTable();
            int linearTotalProbes, linearTotalInserts;
            int doubleTotalProbes, doubleTotalInserts;
            double linearAverageProbes, doubleAverageProbes;

            linearTotalProbes = linearTotalInserts = doubleTotalProbes = doubleTotalInserts = 0;

            for(int i = 0; i < linearH.length; i++){
                if(linearH[i] == null){

                }else{
                    HashObject temp = linearH[i];
                    linearTotalProbes += temp.getProbeCount();
                    linearTotalInserts += temp.getFrequencyCount();
                }
            }
    
            for(int i = 0; i < doubleH.length; i++){
                if(doubleH[i] == null){

                }else{
                    HashObject temp = doubleH[i];
                    doubleTotalProbes += temp.getProbeCount();
                    doubleTotalInserts += temp.getFrequencyCount();
                }
            }

            linearAverageProbes = Double.parseDouble(df.format((linearTotalProbes * 1.0) / n));
            doubleAverageProbes = Double.parseDouble(df.format((doubleTotalProbes * 1.0) / n));

            String test1 = (linearAverageProbes + "");
            String test2 = (doubleAverageProbes + "");

            if(test1.length() == 3){ //these if-statements are to add an additional zero to the "number" if the value is rounded up to something like X.Y as opposed to X.YZ
                test1 += "0";
            }

            if(test2.length() == 3){
                test2 += "0";
            }

            if(debugLevel == SUMMARY_DEBUG && correctFormat){ //if-statements to check debugLevel

                System.out.println("HashtableTest: Found a twin prime table capacity: " + m);
                System.out.println("Hashtable: Input: " + dataTypeName + "   Loadfactor: " + df.format(Double.parseDouble(args[1])));
                System.out.println("        Using Linear Probing");
                System.out.println("HashtableTest: size of hash table is " + n);
                System.out.println("        Inserted " + linearTotalInserts + " elements, of which " + (linearTotalInserts - linearUniqueInserts) + " were duplicates");
                System.out.println("        Avg. no. of probes = " + test1);

                System.out.println("        Using Double Hashing");
                System.out.println("HashtableTest: size of hash table is " + n);
                System.out.println("        Inserted " + doubleTotalInserts + " elements, of which " + (doubleTotalInserts - doubleUniqueInserts) + " were duplicates");
                System.out.println("        Avg. no. of probes = " + test2);

            }else if(debugLevel == DUMP_FILE_DEBUG && correctFormat){

                File temp1 = new File("linear-dump.txt");
                File temp2 = new File("double-dump.txt");

                if(temp1.exists()){ //checking if files exist from previous run, if so delete them
                    temp1.delete();
                }
                
                if(temp2.exists()){
                    temp2.delete();
                }
                
                File dump1 = new File("linear-dump.txt");
                File dump2 = new File("double-dump.txt");

                try {
                    PrintWriter linearWriter = new PrintWriter(dump1);
                    PrintWriter doubleWriter = new PrintWriter(dump2);

                    for(int i = 0; i < linearH.length; i++){
                        if(linearH[i] == null){

                        }else{
                            HashObject temp = linearH[i];
                            linearWriter.println("table[" + i + "]: " + temp.getKey().toString() + " " + temp.getFrequencyCount() + " " + temp.getProbeCount());
                        }
                    }

                    for(int i = 0; i < doubleH.length; i++){
                        if(doubleH[i] == null){

                        }else{
                            HashObject temp = doubleH[i];
                            doubleWriter.println("table[" + i + "]: " + temp.getKey().toString() + " " + temp.getFrequencyCount() + " " + temp.getProbeCount());
                        }
                    }
                    
                    linearWriter.close();
                    doubleWriter.close();

                    System.out.println("HashtableTest: Found a twin prime table capacity: " + m);
                    System.out.println("Hashtable: Input: " + dataTypeName + "   Loadfactor: " + df.format(Double.parseDouble(args[1])));
                    System.out.println("        Using Linear Probing");
                    System.out.println("HashtableTest: size of hash table is " + n);
                    System.out.println("        Inserted " + linearTotalInserts + " elements, of which " + (linearTotalInserts - linearUniqueInserts) + " were duplicates");
                    System.out.println("        Avg. no. of probes = " + test1);
                    System.out.println("HashtableTest: Saved dump of hash table\n");

                    System.out.println("        Using Double Hashing");
                    System.out.println("HashtableTest: size of hash table is " + n);
                    System.out.println("        Inserted " + doubleTotalInserts + " elements, of which " + (doubleTotalInserts - doubleUniqueInserts) + " were duplicates");
                    System.out.println("        Avg. no. of probes = " + test2);
                    System.out.println("HashtableTest: Saved dump of hash table");

                } catch (FileNotFoundException e) {
                    System.out.println("FILE DOESN'T EXIST!");
                }
            }
            
        }else{
            System.out.println(COMMAND_USAGE);
        }
    }

    /**
     * This method is for debugLevel = 2 when the HashObjects are output into the linearProbing console with a provided HashObject and location.
     * @param item HashObject to be printed out.
     * @param location Location of the object.
     */
    public static void linearPrintInsert(HashObject item, int location){
        if(debugLevel == INSERT_DEBUG && item.getFrequencyCount() > 1){
            System.out.println(numberInserted + ": LINEAR PROBING: DUPLICATE INSERTION OF ELEMENT " + item.getKey().toString() + " at " + location + "\n");
        }else if(debugLevel == INSERT_DEBUG && item.getFrequencyCount() == 1){
            System.out.println(numberInserted + ": LINEAR PROBING: SUCCESSFUL INSERTION OF ELEMENT " + item.getKey().toString() + " at " + location + "\n");
        }

        if(item.getFrequencyCount() == 1){ //updating when there is a new hashobject added
            linearUniqueInserts++;
        }
        
    }

    /**
     * This method is for debugLevel = 2 when the HashObjects are output into the doubleHashing console with a provided HashObject and location.
     * @param item HashObject to be printed out.
     * @param location Location of the object.
     */
    public static void doublePrintInsert(HashObject item, int location){
        if(debugLevel == INSERT_DEBUG && item.getFrequencyCount() > 1){
            System.out.println(numberInserted + ": DOUBLE HASHING: DUPLICATE INSERTION OF ELEMENT " + item.getKey().toString() + " at " + location + "\n");
        }else if(debugLevel == INSERT_DEBUG && item.getFrequencyCount() == 1){
            System.out.println(numberInserted + ": DOUBLE HASHING: SUCCESSFUL INSERTION OF ELEMENT " + item.getKey().toString() + " at " + location + "\n");
        }
        
        if(item.getFrequencyCount() == 1){
            doubleUniqueInserts++;
        }
    }
}

