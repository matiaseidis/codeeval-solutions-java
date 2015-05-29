import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by meidis on 26/05/15.
 */
public class StringToInt {

    public static void main(String[] args) {
        System.out.println(fromHex("ffa"));
        System.out.println(fromDec("45678"));
    }

    static int from(String src, int base, Function<Character, Integer> mapping) {
        int result = 0;
        for (char c : src.toCharArray()) {
            result *= base;
            result += mapping.apply(c);
        }
        return result;
    }

    private static int fromDec(String src) {
        char zero = '0';
        return from(src, 10, c -> c - zero);
    }

    static int fromHex(String src) {
        return from(src, 16, c -> FROM_HEX.get(c));
    }


    static Map<Character, Integer> FROM_HEX = new HashMap<>();

    static {
        FROM_HEX.put('0', 0);
        FROM_HEX.put('1', 1);
        FROM_HEX.put('2', 2);
        FROM_HEX.put('3', 3);
        FROM_HEX.put('4', 4);
        FROM_HEX.put('5', 5);
        FROM_HEX.put('6', 6);
        FROM_HEX.put('7', 7);
        FROM_HEX.put('8', 8);
        FROM_HEX.put('9', 9);
        FROM_HEX.put('a', 10);
        FROM_HEX.put('b', 11);
        FROM_HEX.put('c', 12);
        FROM_HEX.put('d', 13);
        FROM_HEX.put('e', 14);
        FROM_HEX.put('f', 15);
    }
}
