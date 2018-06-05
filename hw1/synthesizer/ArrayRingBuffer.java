package synthesizer;
import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    public int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    public int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        this.fillCount = 0;
        this.first = 0;
        this.last = 0;
        this.capacity = capacity;

        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
    }
    private class ItemIterator implements Iterator<T>{
        private int index;
        private int flag = 0;       //当isFull时，flag来判定iterate完毕
        public ItemIterator(){
            index = first;
        }
        @Override
        public boolean hasNext() {
            if (isEmpty()) return false;
            if (index == moveRight(last)) flag += 1;        //首位相连的情况下，index = first刚从头开始时满足-->flag = 1；
                                                            //index 遍历到array尾部时, index = moveRight(last)-->flag = 2;
            if (isFull()){
                if (flag == 2) return false;
                else return true;
            } else {
                if (index > last && index < first ) return false;
                else if (index > last && index > first) return false;
                else if (index < last && index < first) return false;
                else return true;
            }

        }
        @Override
        public T next(){
            T returnValue = rb[index];
            index = moveRight(index);
            return returnValue;
        }
        private int moveRight(int index){
            if (index == capacity - 1) index = 0;
            else index += 1;
            return index;
        }
    }
    @Override
    public Iterator<T> iterator(){
        return new ItemIterator();
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        if (fillCount == capacity) {
            throw new RuntimeException("RIng buffer overflow");
        } else if (fillCount == 0) {      //当第一次往Queue中添加时，保持first,last位置不变且指向同一item；
            rb[last] = x;
            fillCount += 1;
            return;
        } else if (last == capacity - 1) {
            this.last = 0;
        } else {
            last += 1;
        }
        rb[last] = x;
        fillCount += 1;
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if (fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T returnValue = rb[first];
        rb[first] = null;       //清除当前Queue中oldest item;
        if (fillCount == 1) {        //当删除Queue中最后一个item时，保持fisrt, last位置不变且指向同一item；
            return returnValue;
        } else if (first == capacity - 1){
            first = 0;
        } else {
            first += 1;
        }
        fillCount -= 1;
        return returnValue;
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        return rb[first];
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
}
