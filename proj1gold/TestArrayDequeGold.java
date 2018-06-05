import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
public class TestArrayDequeGold {
    public static String[] os = new String[100];

    @Test
    public void randomTest(){
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();
        for(int i =0; i < 100; i++){
            double rn = StdRandom.uniform(8);
            if ( rn < 2 ){
                Integer expected = ads1.removeFirst();
                Integer actual = sad1.removeFirst();
                String message  = recordOP("removeFirst()", i);
                assertEquals(message + expected + "!",expected,actual);
            }
            else if ( rn >=2 && rn < 4 ){
                ads1.addLast(i);
                sad1.addLast(i);
                Integer expected = ads1.get(ads1.size() - 1);
                Integer actual = sad1.get(sad1.size() - 1);
                String message  = recordOP("addLast(" + i + ")", i);
                assertEquals(message, expected, actual);
            }
            else if( rn >=4 && rn <6 ){
                ads1.addFirst(i);
                sad1.addFirst(i);
                Integer expected = ads1.get(0);
                Integer actual = sad1.get(0);
                String message  = recordOP("addFirst(" + i + ")", i);
                assertEquals(message , expected, actual);
            }
            else if(rn >=6){
                Integer expected = ads1.removeLast();
                Integer actual = sad1.removeLast();
                String message  = recordOP("removeLast()", i);
                assertEquals(message, expected, actual);
            }
        }
    }
    private String recordOP(String name, int index){
        os[index] = name;
        String output = "\n";
        for( int i = 0; i <= index; i++ ){
            output = output + os[i] + "\n";
            i++;
        }
        return output;
    }
}
