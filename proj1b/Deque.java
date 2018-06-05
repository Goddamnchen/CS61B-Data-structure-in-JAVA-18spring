public interface Deque <T>{
    /* all of the methods in LinkedListDeque and ArrayDeque*/
    void addFirst(T x);

    void addLast(T x);

    T removeFirst();

    T removeLast();

    int size();

    boolean isEmpty();

    T get(int index);

    void printDeque();

}
