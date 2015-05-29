import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by meidis on 26/05/15.
 */
public class DNAAlignment {

    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        DNAAlignment dnaAlignment = new DNAAlignment();
        while ((line = buffer.readLine()) != null) {
            line = line.trim();
            System.out.println(dnaAlignment.solution(line));
        }
    }

    static int MATCH = 3;
    static int MISMATCH = -3;
    static int INDEL_START = -8;
    static int INDEL_EXTENSION = -1;

    public int solution(String s) {

        String[] splitted = s.split("\\|");
        String a = splitted[0].trim();
        String b = splitted[1].trim();

        int cacheSize = (int) Math.pow(2, 20);
        int[] cache = new int[cacheSize];
        Arrays.fill(cache, Integer.MIN_VALUE);
        return solution(a.toCharArray(), 0, b.toCharArray(), 0, false, false, cache);
    }

    private int solution(char[] a, int aIdx, char[] b, int bIdx, boolean aIndel, boolean bIndel, int[] cache) {
        final int key = (((((aIdx << 9) | bIdx) << 1) | (aIndel ? 1 : 0)) << 1) | (bIndel ? 1 : 0);
        if (cache[key] != Integer.MIN_VALUE) {
            return cache[key];
        }
        boolean aDone = aIdx == a.length;
        boolean bDone = bIdx == b.length;
        if (aDone && bDone) {
            return 0;
        } else {
            if (aDone || bDone) {
                cache[key] = aDone
                        ? solution(a, aIdx, b, bIdx + 1, true, false, cache) + (aIndel ? INDEL_EXTENSION : INDEL_START)
                        : solution(a, aIdx + 1, b, bIdx, false, true, cache) + (bIndel ? INDEL_EXTENSION : INDEL_START);
            } else {
                cache[key] =
                        Math.max(
                                Math.max(
                                        solution(a, aIdx, b, bIdx + 1, true, false, cache) + (aIndel ? INDEL_EXTENSION : INDEL_START),
                                        solution(a, aIdx + 1, b, bIdx, false, true, cache) + (bIndel ? INDEL_EXTENSION : INDEL_START)
                                ),
                                solution(a, aIdx + 1, b, bIdx + 1, false, false, cache) + (a[aIdx] == b[bIdx] ? MATCH : MISMATCH)
                        );
            }
            return cache[key];
        }
    }
}
