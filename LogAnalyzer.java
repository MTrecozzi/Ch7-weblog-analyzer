
import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    private int [] dayCounts;
    private int[] monthCounts;
    
    // Use a LogfileReader to access the data.
    private LogfileReader hourReader;
    private LogfileReader dayReader;
    private LogfileReader monthReader;
    private LogfileReader yearReader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer(String fileName)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        dayCounts = new int[31];
        monthCounts = new int[13];
        // Create the hourReader to obtain the data.
        hourReader = new LogfileReader(fileName);
        dayReader = new LogfileReader(fileName);
        monthReader = new LogfileReader(fileName);
        
        
        analyzeHourlyData();
        analyzeDailyData();
        analyzeMonthlyData();
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(hourReader.hasNext()) {
            LogEntry entry = hourReader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }
    
    public void analyzeDailyData() {
     
        while (dayReader.hasNext()){
            LogEntry dayEntry = dayReader.next();  
            
            int day = dayEntry.getDay();
            dayCounts[day]++;   
        }   
        
    }
    
    public void analyzeMonthlyData(){
        
     while (monthReader.hasNext()){
         
         LogEntry monthEntry = monthReader.next();
         int month = monthEntry.getMonth();
         monthCounts[month]++;
         
        }
     
    }
    
    public void printAccessesPerMonth() {
        
        for (int i = 1; i < monthCounts.length; i++) {
            
            System.out.println("Month #" + i + ": " + monthCounts[i]);
        }
        
    }
    
    public int busiestDay() {    
        int busiestDay = 0;      
        int mostAccesses = -5;
            
        for (int i = 1; i < dayCounts.length; i++) {
            if (dayCounts[i] > mostAccesses){
             
                busiestDay = i;
                mostAccesses = dayCounts[i];
                
            }
        }    
        // returns the earliest hour with the largest count.
        return busiestDay;
    }
    
    public int quietestDay() {
        
        int leastBusyDay = 0;
        int leastAccesses = -5;
        
        for (int i = 1; i <= dayCounts.length; i++) {
            
            if (leastAccesses == -5);
                leastAccesses = dayCounts[i];
            
            if (dayCounts[i] < leastAccesses) {
                leastBusyDay = i;
                leastAccesses = dayCounts[i];
            }
            
        }
        
        return leastBusyDay;
        
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
    
    public int busiestHour() {    
        int busiestHour = 0;      
        int mostAccesses = -5;
            
        for (int i = 0; i < hourCounts.length; i++) {
            if (hourCounts[i] > mostAccesses){
             
                busiestHour = i;
                mostAccesses = hourCounts[i];
                
            }
        }    
        // returns the earliest hour with the largest count.
        return busiestHour;
    }
    
    public int busiestTwoHourPeriod() {
     
        int busiestTwoHourStart = 0;
        int mostAccesses = 0;
        
        for (int i = 0; i < hourCounts.length - 1; i++) {
            
            int biHourAccesses = hourCounts[i] + hourCounts[i+1];
            
            if (biHourAccesses > mostAccesses) {
                
             busiestTwoHourStart = i;
             mostAccesses = biHourAccesses;
                
            }
            
        }
        
        return busiestTwoHourStart;
        
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
        hourReader.printData();
    }
}
