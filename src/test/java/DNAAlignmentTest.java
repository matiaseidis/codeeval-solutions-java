import org.junit.Test;

/**
 * Created by meidis on 26/05/15.
 */
public class DNAAlignmentTest {

    @Test
    public void test() {
        DNAAlignment dnaAlignment = new DNAAlignment();
        org.junit.Assert.assertEquals(1, dnaAlignment.solution("GAAAAAAT | GAAT"));
        org.junit.Assert.assertEquals(1, dnaAlignment.solution("GAAT | GAAAAAAT"));
        org.junit.Assert.assertEquals(-3, dnaAlignment.solution("GATTACA | GCATGCT"));
        org.junit.Assert.assertEquals(-3, dnaAlignment.solution("GCATGCT | GATTACA"));
        org.junit.Assert.assertEquals(-54, dnaAlignment.solution("ABCDEFGHIJABCDEFGHIJ|KLMNOPQRSTKLMNOPQRST"));
        org.junit.Assert.assertEquals(-54, dnaAlignment.solution("KLMNOPQRSTKLMNOPQRST|ABCDEFGHIJABCDEFGHIJ"));
    }
}
