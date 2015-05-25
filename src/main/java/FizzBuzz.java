import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
public class FizzBuzz {
    public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();
            String[] splitted = line.split(" ");
            int x = Integer.parseInt(splitted[0]);
            int y = Integer.parseInt(splitted[1]);
            int n = Integer.parseInt(splitted[2]);
            
            Set<Integer> xSet = new HashSet<Integer>();
            Set<Integer> ySet = new HashSet<Integer>();
            
            int xIdx = x;
            int yIdx = y;
            
            while(xIdx <= n) {
            	xSet.add(xIdx);
            	xIdx += x;
            }
            
            while(yIdx <= n) {
            	ySet.add(yIdx);
            	yIdx += y;
            }
            
            StringBuilder sb = new StringBuilder();
            for(int i = 1; i<=n; i++) {
            	boolean xOk = xSet.contains(i);
            	boolean yOk = ySet.contains(i);
            	
            	if(xOk || yOk) {
            		if(xOk) {
            			sb.append("F");
            		}
            		if(yOk) {
            			sb.append("B");
            		}
            	} else {
            		sb.append(i);
            	}
            	
            	sb.append(" ");
            }
            System.out.println(sb.toString());
        }
    }
}
