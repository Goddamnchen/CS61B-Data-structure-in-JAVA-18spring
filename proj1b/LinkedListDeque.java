public class LinkedListDeque<T> implements Deque<T>{
    public class IntNode{
        /*不能使用static定义此类，因为static方法或者类不能使用任何未初始化的属性
        此处外部类所将声明的T则为未初始化的属性
         */
        private IntNode prev;
        private T item;
        private IntNode next;
        private IntNode(T item, IntNode prev, IntNode next){
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }
    private IntNode sentinel;
    private int size;

    public LinkedListDeque(){
        sentinel = new IntNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
    public LinkedListDeque(T x){
        sentinel = new IntNode(null, null, null);
        sentinel.next = new IntNode(x, sentinel, sentinel);
        size = 1;
    }
    @Override
    public void addFirst(T x){
        sentinel.next = new IntNode(x, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size = size +1;
    }
    @Override
    public void addLast(T x){
        sentinel.prev = new IntNode(x, sentinel.prev, sentinel);
        sentinel.prev.prev.next =  sentinel.prev;
        size = size +1;
    }
    @Override
    public T removeFirst(){
        if(size == 0){
            System.out.println("Null list, nothing to deleted");
            return null;
        }
        T deleted_item = sentinel.next.item;         //return value
        sentinel.next.next.prev = sentinel;         //先将第一个后面的元素的prev指针与sentienl相连
        sentinel.next = sentinel.next.next;
        size = size - 1;
        return deleted_item;
    }
    @Override
    public T removeLast(){
        if(size == 0){
            System.out.println("Null list, nothing to deleted");
            return null;
        }
        T deleted_item = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size = size - 1;
        return deleted_item;
    }
    @Override
    public int size(){
        return size;
    }
    @Override
    public boolean isEmpty() {
        if(sentinel.next == sentinel){
            return true;
        }
        return false;
    }
    @Override
    public T get(int index){
        if(index > (size - 1)){
            return null;        //改return null;
        }
        IntNode ptr = sentinel;
        for(int i = 0; i <= index; i++){
            ptr = ptr.next;
        }
        return ptr.item;
    }

    public T getRecursive(int index){
        if(index > (size -1)){
            return null;
        }
        return getRecursive(index, sentinel);
    }

    public T getRecursive(int index, IntNode p){
        /*同理此方法不能设定为静态方法，因为T还未初始化*/
        if(index == 0){
            return p.next.item;
        }
        return getRecursive(index - 1, p.next);

    }
    @Override
    public void printDeque(){
        if(size == 0){
            System.out.println("Null List, no node exists");
            return;
        }
        printDeque(sentinel.next, sentinel.prev);
    }

    public void printDeque(IntNode first, IntNode last){
        /* Prints the items in the deque from first to last, separated by a space. */
        if(first == last){
            System.out.print(last.item + " ");
            return;
        }
        printDeque(first, last.prev);
        System.out.print(last.item + " ");
    }
    public static void main(String args[]) {
        LinkedListDeque<String> L = new LinkedListDeque<>();
        L.addFirst("B");
        L.addLast("C");
        L.addLast("D");
        L.addFirst("A");
        L.printDeque();
        System.out.println(L.get(1));
        System.out.println(L.removeLast());
        System.out.println(L.get(0));
        System.out.println(L.removeFirst());
        L.addFirst(("D"));
        System.out.println(L.get(0));
    }

}
