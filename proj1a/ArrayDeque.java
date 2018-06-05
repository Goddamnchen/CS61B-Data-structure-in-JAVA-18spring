public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int prev;
    private int next;
    public ArrayDeque(){
        items = (T []) new Object[8];       //generic 列表对象 的特定语法
        size = 0;
        prev = 0;
        next = 1;
    }

    /**
     * @param index
     * @return the new address of the sentinel after moving --> left
     */
    private int minusOne(int index){
        if(index == 0){
            return  items.length - 1;
        }
        return (index - 1) % items.length;
    }

    /**
     * @param index
     * @return the new address of the sentinel after moving -->right
     */
    private int plusOne(int index){
        return (index + 1) % items.length;
    }


    public void addFirst(T item){
        if(size == items.length - 2){
            resize(items.length);
        }
        items[prev] = item;
        prev = minusOne(prev);
        size++;
    }

    public void addLast(T item){
        if(size == items.length - 2){
            resize(items.length);
        }
        items[next] = item;
        next = plusOne(next);
        size++;
    }

    /**deleted the element next to sentinel prev by moving prev and decreasing the size.
     * return the element being deleted.
     */
    public T removeFirst(){
        if (size == 0 ){
            return null;
        }
        prev = plusOne(prev);
        T deleted = items[prev];
        items[prev] = null;     /*对于generic style来说remove后还要清除可能存在的非primitive type reference*/
        size--;
        checkUserR();           /* check to shrink the deque and use memory more efficiently*/
        return deleted;
    }

    /**deleted the element ahead to sentinel next by moving prev and decreasing the size.
     * return the element being deleted
     */
    public T removeLast(){
        if(size == 0){
            return null;
        }
        next = minusOne(next);
        T deleted = items[next];
        items[next] = null;
        size--;
        checkUserR();
        return deleted;
    }

    public boolean isEmpty() {
        if(size == 0) {
            return true;
        }
        return false;
    }

    public void printDeque(){
        printDeque(plusOne(prev), minusOne(next));      //传入当前array第一个与最后一个元素
    }

    /** helper method of printDeque()
     * print the deque from the element next to prev sentinel to the element ahead of next sentinel
     * @param first
     * @param last
     */
    private void printDeque(int first, int last){
        if(first == last){
            System.out.print(items[first] + " ");
            return;
        }
        printDeque(first, minusOne(last));
        System.out.print(items[last] + " ");

    }

    public T get(int index){
        if(size == 0 || (index <= prev && index >= next)){      //判断index是否out of bound
            System.out.print("out of bound, going to return ");
            return null;       ////这里0 暂时=null
        }
        return items[index];
    }

    /**@return the size of occupied array space between sentinels
     */
    public int size(){
        return size;
    }

    private void resize(int capacity){      //依据fullsize时next, prev是否直接相连分成两种情况
                                            // 1.prev在头,next在尾：resize后prev和next及携带原array都不变
                                            // 2.prev，next相邻：resize后next不变,prev携带部分array右移

        T[] a =(T[]) new Object[capacity * 2];
        if(prev < next){
            System.arraycopy(items, 0, a, 0, capacity);     //prev,next位置都不变
            items = a;
        }
        else if(prev > next){
            System.arraycopy(items,0, a, 0, next +1 );       //copy表头到n
            System.arraycopy(items, prev, a, prev + capacity, capacity - prev);         //copy prev到列表尾部
            prev = prev + capacity;
            items = a;
        }
    }

    /** check the item.length and usage ratio
     * if not using memory efficiently, half the space of deque*/
    private void checkUserR(){
        double usage_ratio = (double)size / (double)items.length;
        if (items.length >= 16 && usage_ratio <= 0.25){
            halfDeque(items.length);
        }

    }

    /** helper method for checkUserR
     * implement memory saving operation
     * @param capacity
     */
    private void halfDeque(int capacity){
        T[] a = (T []) new Object[capacity/2];
        if(prev < next){        //prev回到初始位置0, 则next回到size+1的address
            System.arraycopy(items, prev, a, 0, size +2);
            prev = 0;
            next = prev + size +1;
            items = a;
        }
        else if(prev > next) {          //array头到的位置next不变，prev及以后前移1/2length
            System.arraycopy(items, 0, a, 0, next +1 );     //copy array头-->next
            System.arraycopy(items, prev, a, prev - capacity/2, capacity - prev );
            prev = prev - capacity/2;
            items = a ;
        }

    }
    /** Test */
    public static void main(String args[]){
        ArrayDeque<String> a = new ArrayDeque<>();
        a.addFirst("D");
        a.addFirst("C");
        a.addLast("E");
        a.addLast("F");
        a.addLast("G");
        a.addLast("H");
        a.addLast("I");
        a.addFirst("B");
        a.addFirst("A");
        System.out.println("removing "+ a.removeLast());
        System.out.println("removing "+ a.removeLast());
        System.out.println("removing "+ a.removeFirst());
        System.out.println("removing "+ a.removeLast());
        System.out.println("removing "+ a.removeLast());
        a.printDeque();
        System.out.println();
        System.out.println("array size is " + a.size());
        System.out.println(a.get(1));

    }
}
