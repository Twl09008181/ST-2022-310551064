import static org.junit.jupiter.api.Assertions.*;

import java.util.PriorityQueue;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.swing.*;


public class PriorityQueueTest {

    private static Stream<Arguments> Provider(){

        return Stream.of(
                Arguments.of(new int[]{5,4,2,3},new int[]{2,3,4,5}),
                Arguments.of(new int[]{5,7,1,2},new int[]{1,2,5,7}),
                Arguments.of(new int[]{5,9,1,4},new int[]{1,4,5,9}),
                Arguments.of(new int[] {1,2,3,4},new int[] {1,2,3,4}),
                Arguments.of(new int[] {1,10,3,9},new int[] {1,3,9,10})
        );
    }
    @MethodSource("Provider")
    @ParameterizedTest()
    void PriorityQueue_RunTest(int[] random_array,int []correct_array){
        PriorityQueue<Integer> test = new PriorityQueue<Integer>();
        int []result = new int [random_array.length];
        for(int i = 0;i < random_array.length;i++) {
            test.add(random_array[i]);
        }
        int index = 0;
        while(!test.isEmpty()) {
            result[index] = test.poll();
            index++;
        }
        assertArrayEquals(result,correct_array);
    }

    @Test
    public void whenExceptionThrow_IllegalArgumentException(){
        Exception exception = assertThrows(IllegalArgumentException.class,()->{
            PriorityQueue<String> test = new PriorityQueue<String>(-1);
        });
        String expMsg = "java.lang.IllegalArgumentException";
        String actualMsg = exception.toString();
        assertEquals(expMsg,actualMsg);
    }
    @Test
    public void whenExceptionThrow_NullPointerException(){
        Exception exception = assertThrows(NullPointerException.class,()->{
            PriorityQueue<String> test = new PriorityQueue<String>();
            test.offer(null);
        });
        String expMsg = "java.lang.NullPointerException";
        String actualMsg = exception.toString();

        assertEquals(expMsg,actualMsg);

    }
    @Test
    public void whenExceptionThrow_next() {
        Exception exception = assertThrows(NoSuchElementException.class,()->{
            PriorityQueue<String> test = new PriorityQueue<String>();
            test.iterator().next();
        });
        String expMsg = "java.util.NoSuchElementException";
        String actualMsg = exception.toString();
        assertEquals(expMsg,actualMsg);
    }




}