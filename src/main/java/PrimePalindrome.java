import java.io.IOException;
import java.util.Arrays;

/**
 * Created by meidis on 26/05/15.
 */
public class PrimePalindrome {

    public static void main (String[] args) throws IOException {
        System.out.println(primePalindrome(1000));
    }

    private static int primePalindrome(int limit) {

        boolean[] registry = new boolean[limit];
        Arrays.fill(registry, true);
        registry[0] = false;
        registry[1] = false;
        for(int i = 2; i<limit; i++) {
            if(registry[i]){
                int j = 2 * i;
                while(j < limit) {
                    registry[j] = false;
                    j += i;
                }
            }
        }
        for(int i = limit-1; i>1; i--) {
            if(registry[i] && isPalindrome(i)) {
                return i;
            }
        }
        return 2;
    }

    private static boolean isPalindrome(int src) {
        String iStr = Integer.toString(src);
        for(int i = 0; i<iStr.length()/2; i++) {
            if(iStr.charAt(i) != iStr.charAt(iStr.length()-1-i)) {
                return false;
            }
        }
        return true;
    }

}
