import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class LongestLines {
	public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        int k = Integer.parseInt(buffer.readLine());
        List<String> result = new ArrayList<String>();
        while ((line = buffer.readLine()) != null) 
        	result.add(line);
        Collections.sort(result, new Comparator<String>() {
        	public int compare(String o1, String o2) {
        		return Integer.compare(o2.length(), o1.length());
        	}
        });
        for(int i = 0 ; i<k; i++)
        	System.out.println(result.get(i));
//        PriorityQueue<String> heap = new PriorityQueue<String>(k, new Comparator<String>() {
//        	public int compare(String o1, String o2) {
//        		return Integer.compare(o1.length(), o2.length());
//        	}
//        });
//        while ((line = buffer.readLine()) != null) {
//            line = line.trim();
//            heap.add(line);
//            while(heap.size()>k)
//            	heap.poll();
//        }
//        
//        Stack<String> result = new Stack<String>();
//        for(int i = 0 ; i<k; i++)
//        	result.addAll(heap);
//        	
//        for(int i = 0 ; i<k; i++) //while(!result.isEmpty())	
//        	System.out.println(result.pop());
    }
}
