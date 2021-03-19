import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.PriorityQueue;
import java.util.stream.Stream;


public class PriorityQueueTest {

    static Stream<Arguments> getParameters(){
        return Stream.of(
                arguments(new int[]{-1,0,1,2,3}, new int[]{-1,0,1,2,3}),
                arguments(new int[]{2,2,1,0,-1}, new int[]{-1,0,1,2,2}),
                arguments(new int[]{1958,1896,2020}, new int[]{1896,1958,2020}),
                arguments(new int[]{2021,3,19,13,53}, new int[]{3,13,19,53,2021}),
                arguments(new int[]{-999,3281,1,-1000,0}, new int[]{-1000,-999,0,1,3281})
        );
    }

    @ParameterizedTest(name="#{index} - Test with Argument={0},{1}")
    @MethodSource("getParameters")
    public void PriorityQueue_RunTest(int[] random_array, int[] correct_array){
        PriorityQueue<Integer> testPriorityQueue = new PriorityQueue<>(random_array.length);
        int[] result = new int[random_array.length];

        // random_array add to PriorityQueue
        int i;
        for(i = 0; i < random_array.length; i++){
            testPriorityQueue.add(random_array[i]);
        }
        // get PriorityQueue result
        for(i = 0; i < random_array.length; i++){
            if(testPriorityQueue.peek()!=null)
                result[i] = testPriorityQueue.poll();
        }

        assertArrayEquals(correct_array, result);
    }


    // 3 unique Exceptions
    @Test
    public void ExceptionThrownTest_InitialCapacity(){
        // public PriorityQueue(int initialCapacity)
        //     Creates a PriorityQueue with the specified initial capacity that orders its elements according to their natural ordering.

        // IllegalArgumentException - if initialCapacity is less than 1
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new PriorityQueue(-1);
        });


        // public PriorityQueue(int initialCapacity,Comparator<? super E> comparator)
        //     Creates a PriorityQueue with the specified initial capacity that orders its elements according to the specified comparator.

        // IllegalArgumentException - if initialCapacity is less than 1
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> {
            new PriorityQueue(-1, null);
        });
    }

    @Test
    public void ExceptionThrownTest_Add(){
        // public boolean add(E e)
        //     Inserts the specified element into this priority queue.

        Exception exception = assertThrows(NullPointerException.class, () -> {
            new PriorityQueue().add(null);
        });

        // ClassCastException - if the specified element cannot be compared with elements currently in this priority queue according to the priority queue's ordering
        Exception exception1 = assertThrows(ClassCastException.class, () -> {
            PriorityQueue testPriorityQueue = new PriorityQueue();
            testPriorityQueue.add(0);
            testPriorityQueue.add("string");
        });
    }

    @Test
    public void ExceptionThrownTest_RemoveAll(){
        // public boolean removeAll(Collection<?> c)
        //     Description copied from class: AbstractCollection
        //     Removes all of this collection's elements that are also contained in the specified collection (optional operation). After this call returns, this collection will contain no elements in common with the specified collection.

        // NullPointerException - if this collection contains one or more null elements and the specified collection does not support null elements (optional), or if the specified collection is null
        Exception exception = assertThrows(NullPointerException.class, () -> {
            PriorityQueue testPriorityQueue = new PriorityQueue();
            testPriorityQueue.removeAll(null);
        });
    }

}
