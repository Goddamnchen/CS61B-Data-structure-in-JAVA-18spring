import java.util.Formatter;

/**
 * A naked recursive list of integers, similar to what we saw in lecture 3, but
 * with a large number of additional methods.
 *
 * @author P. N. Hilfinger, with some modifications by Josh Hug and melaniecebula
 *         [Do not modify this file.]
 */
public class IntList {
    /**
     * First element of list.
     */
    private int first;
    /**
     * Remaining elements of list.
     */
    private IntList rest;
    /**
     * A List with first FIRST0 and rest REST0.
     */
    public IntList(int first0, IntList rest0) {
        first = first0;
        rest = rest0;
    }

    /**
     * A List with null rest, and first = 0.
     */
    public IntList() {
    /* NOTE: public IntList () { }  would also work. */
        this(0, null);
    }

    /**
     * Returns a list equal to L with all elements squared. Destructive.
     */
    public static void dSquareList(IntList L) {

        while (L != null) {
            L.first = L.first * L.first;
            L = L.rest;
        }
    }

    /**
     * Returns a list equal to L with all elements squared. Non-destructive.
     */
    public static IntList squareListIterative(IntList L) {
        if (L == null) {
            return null;
        }
        IntList res = new IntList(L.first * L.first, null);
        IntList ptr = res;
        L = L.rest;
        while (L != null) {
            ptr.rest = new IntList(L.first * L.first, null);
            L = L.rest;
            ptr = ptr.rest;
        }
        return res;
    }

    /**
     * Returns a list equal to L with all elements squared. Non-destructive.
     */
    public static IntList squareListRecursive(IntList L) {
        if (L == null) {
            return null;
        }
        return new IntList(L.first * L.first, squareListRecursive(L.rest));
    }

    /** DO NOT MODIFY ANYTHING ABOVE THIS LINE! */


    /**
     * Returns a list consisting of the elements of A followed by the
     * *  elements of B.  May modify items of A. Don't use 'new'.
     */

    public static IntList dcatenate(IntList A, IntList B) {
        /** !!fucking awesome recursion !
         * 当前栈所调用的递归方法得到的（递归方法返回值）不一定是当前栈（当前递归轮次）的返回值
         * 每一轮递归都可以返回任意的值，或者当前轮次的本地变量（包括上一轮传入的参数）
         * 每一轮return如果都是return递归方法本身的话，相当于把base的return层层传递到最外层的递归方法
         * 当返回值不为空且使用迭代进行操作时，需要额外的pointer来保证返回头指针只想的整个列表；
         *
         */
        if(A.rest == null) {
            A.rest = B;
            return A;
        }
        dcatenate(A.rest , B);
        return A;
        /** iteration
         IntList ptr = A;
         while(ptr.rest != null){
         ptr = ptr.rest;
         }
         ptr.rest = B;
         return A;
         */
    }

    /**
     * Returns a list consisting of the elements of A followed by the
     * * elements of B.  May NOT modify items of A.  Use 'new'.
     */
    public static IntList catenate(IntList A, IntList B) {
        /** !!fucking awesome recursion !
         * IntList C 可有可无，保证每轮递归方法返回一个值为A or B 指针对应的值的Linked List就行
         * 画进出栈+返回值的图更容易理解
         * 递归创建新的合并的Linked List的道理好比倒着创建Linked List
         *  递归中的栈顶的返回值（base）                       《==  IntList L = new IntList(15, null);
         *  递归中的栈中的返回值（new current value + 指向上一栈的pointer 《== L = new IntList(10, L);
         *  递归中的栈底的返回值（整个方法的return）                      《== L = new IntList(5, L);
         */
        IntList C;
        if (B.rest == null) {
            C = new IntList(B.first, null);
            return C;
        }
        if (A == null) {
            C = new IntList(B.first, catenate(A, B.rest));
            return C;
        }
        C = new IntList(A.first, catenate(A.rest, B));
        return C;


        /** iteration
         IntList C = new IntList(A.first, null);
         IntList ptr = C;
         while (A.rest != null){
         A = A.rest;
         ptr.rest = new IntList(A.first, null);
         ptr = ptr.rest;
         }
         while (B != null){
         ptr.rest = new IntList(B.first , null);
         ptr = ptr.rest;
         B = B.rest;
         }
         return C ;
         */
    }
    public static IntList Reverse(IntList A){
        IntList front = null;
        IntList current = A;
        IntList next;
        while(current.rest != null ){
            next = current.rest;
            current.rest = front;
            front = current;
            current = next;
        }
        current.rest = front;
        A = current;        //使IntList 的reference指向倒转后Linked List的第一个节点
        return A;
        }

















    /**
     * DO NOT MODIFY ANYTHING BELOW THIS LINE! Many of the concepts below here
     * will be introduced later in the course or feature some form of advanced
     * trickery which we implemented to help make your life a little easier for
     * the lab.
     */

    @Override
    public int hashCode() {
        return first;
    }

    /**
     * Returns a new IntList containing the ints in ARGS. You are not
     * expected to read or understand this method.
     */
    public static IntList of(Integer... args) {
        IntList result, p;

        if (args.length > 0) {
            result = new IntList(args[0], null);
        } else {
            return null;
        }

        int k;
        for (k = 1, p = result; k < args.length; k += 1, p = p.rest) {
            p.rest = new IntList(args[k], null);
        }
        return result;
    }

    /**
     * Returns true iff X is an IntList containing the same sequence of ints
     * as THIS. Cannot handle IntLists with cycles. You are not expected to
     * read or understand this method.
     */
    public boolean equals(Object x) {
        if (!(x instanceof IntList)) {
            return false;
        }
        IntList L = (IntList) x;
        IntList p;

        for (p = this; p != null && L != null; p = p.rest, L = L.rest) {
            if (p.first != L.first) {
                return false;
            }
        }
        if (p != null || L != null) {
            return false;
        }
        return true;
    }

    /**
     * If a cycle exists in the IntList, this method
     * returns an integer equal to the item number of the location where the
     * cycle is detected.
     * <p>
     * If there is no cycle, the number 0 is returned instead. This is a
     * utility method for lab2. You are not expected to read, understand, or
     * even use this method. The point of this method is so that if you convert
     * an IntList into a String and that IntList has a loop, your computer
     * doesn't get stuck in an infinite loop.
     */

    private int detectCycles(IntList A) {
        IntList tortoise = A;
        IntList hare = A;

        if (A == null) {
            return 0;
        }

        int cnt = 0;


        while (true) {
            cnt++;
            if (hare.rest != null) {
                hare = hare.rest.rest;
            } else {
                return 0;
            }

            tortoise = tortoise.rest;

            if (tortoise == null || hare == null) {
                return 0;
            }

            if (hare == tortoise) {
                return cnt;
            }
        }
    }

    @Override
    /** Outputs the IntList as a String. You are not expected to read
     * or understand this method. */
    public String toString() {
        Formatter out = new Formatter();
        String sep;
        sep = "(";
        int cycleLocation = detectCycles(this);
        int cnt = 0;

        for (IntList p = this; p != null; p = p.rest) {
            out.format("%s%d", sep, p.first);
            sep = ", ";

            cnt++;
            if ((cnt > cycleLocation) && (cycleLocation > 0)) {
                out.format("... (cycle exists) ...");
                break;
            }
        }
        out.format(")");
        return out.toString();
    }
}

