import java.util.Arrays;
import java.util.stream.IntStream;

public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer(String fileName)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(fileName);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }
    
    

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    public int numberOfAccesses() {      
        // Using For Loops
        int totalNumberOfAccesses = 0;
        
        for (int i = 0; i < hourCounts.length; i++){
            totalNumberOfAccesses += hourCounts[i];
        }       
        //return totalNumberOfAccesses;
        
        // Using Streams
        IntStream stream = Arrays.stream(hourCounts);    
        return stream.reduce(Integer:: sum).getAsInt();  
    }
    
    public int busiestHourt() {
        
        int busiestHour = 0;      
        int mostAccesses = 0;
            
        for (int i = 0; i < hourCounts.length; i++) {
            if (hourCounts[i] > mostAccesses){
             
                busiestHour = i;
                mostAccesses = hourCounts[i];
                
            }
        }
        
        // returns the earliest hour with the largest count.
        return busiestHour;
        
    }
    
    public int quietestHour() {
        
        int leastBusyHour = 0;
        int leastAccesses = -5;
        
        for (int i = 0; i < hourCounts.length; i++) {
            
            if (leastAccesses == -5);
                leastAccesses = hourCounts[i];
            
            if (hourCounts[i] < leastAccesses) {
                leastBusyHour = i;
                leastAccesses = hourCounts[i];
            }
            
        }
        
        return leastBusyHour;
        
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
}
