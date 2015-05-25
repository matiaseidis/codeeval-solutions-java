import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by us on 23/05/15.
 */
public class LongestCommonSubsequence {

    /*
     * Dynamic programming
     */
    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {

            String[] splitted = line.trim().split(";");

            String a = splitted[0];
            String b = splitted[1];

            int[][] matrix = new int[a.length() + 1][b.length() + 1];

            for (int i = 1; i <= a.length(); i++) {
                for (int j = 1; j <= b.length(); j++) {
                    char aCurrent = a.charAt(i - 1);
                    char bCurrent = b.charAt(j - 1);
                    if (aCurrent == bCurrent) {
                        matrix[i][j] = matrix[i - 1][j - 1] + 1;
                    } else {
                        matrix[i][j] = Math.max(matrix[i][j - 1], matrix[i - 1][j]);
                    }
                }
            }

            StringBuilder sb = new StringBuilder();

            int x = a.length();
            int y = b.length();
            int current = matrix[x][y];

            while (x > 0 && y > 0) {
                while (x > 0 && matrix[x-1][y] == current) {
                    x--;
                }
                while (y > 0 && matrix[x][y-1] == current) {
                    y--;
                }
                if(x > 0) {
                    current = matrix[--x][--y];
                    sb.append(a.charAt(x));
                }
            }

            System.out.println(sb.reverse().toString());
        }
    }

    /*
     * Recursive
     */
    public static void mainRecursive(String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {

            String[] splitted = line.trim().split(";");

            String a = splitted[0];
            String b = splitted[1];

            System.out.println(lcs(a.toCharArray(), a.length(), b.toCharArray(), b.length()));
        }
    }

    private static String lcs(char[] a, int aIdx, char[] b, int bIdx) {
        if (aIdx == 0 || bIdx == 0) {
            return "";
        } else if (a[aIdx - 1] == b[bIdx - 1]) {
            return lcs(a, aIdx - 1, b, bIdx - 1) + a[aIdx - 1];
        } else {
            String aPath = lcs(a, aIdx - 1, b, bIdx);
            String bPath = lcs(a, aIdx, b, bIdx - 1);
            return aPath.length() > bPath.length() ? aPath : bPath;
        }
    }

}