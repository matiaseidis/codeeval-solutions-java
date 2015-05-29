import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

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

        int cacheSize = (a.length()+1)*(b.length()+1)*3;
        short[] cache = new short[cacheSize];
        Arrays.fill(cache, Short.MIN_VALUE);
        return solution(a.toCharArray(), 0, b.toCharArray(), 0, false, false, cache, b.length()+1);
    }

    private short solution(char[] a, int aIdx, char[] b, int bIdx, boolean aIndel, boolean bIndel, short[] cache, int bShift) {

        final int key = (((((aIdx * bShift) + bIdx) *3) + ((!aIndel && !bIndel) ? 0 : aIndel ? 1 : 2)));
        if (cache[key] != Short.MIN_VALUE) {
            return cache[key];
        }
        boolean aDone = aIdx == a.length;
        boolean bDone = bIdx == b.length;
        if (aDone && bDone) {
            return 0;
        } else {
            if (aDone || bDone) {
                cache[key] = (short) (aDone
                        ? solution(a, aIdx, b, bIdx + 1, true, false, cache, bShift) + (aIndel ? INDEL_EXTENSION : INDEL_START)
                        : solution(a, aIdx + 1, b, bIdx, false, true, cache, bShift) + (bIndel ? INDEL_EXTENSION : INDEL_START));
            } else {
                cache[key] =
                        (short)Math.max(
                                Math.max(
                                        solution(a, aIdx, b, bIdx + 1, true, false, cache, bShift) + (aIndel ? INDEL_EXTENSION : INDEL_START),
                                        solution(a, aIdx + 1, b, bIdx, false, true, cache, bShift) + (bIndel ? INDEL_EXTENSION : INDEL_START)
                                ),
                                solution(a, aIdx + 1, b, bIdx + 1, false, false, cache, bShift) + (a[aIdx] == b[bIdx] ? MATCH : MISMATCH)
                        );
            }
            return cache[key];
        }
    }
}
