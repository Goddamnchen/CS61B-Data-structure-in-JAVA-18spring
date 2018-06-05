public class Palindrome {
    /**nest class of comparator testing off-by-1 palindrome type */
    private static class OffByOne implements CharacterComparator{
        @Override
        public boolean equalChars(char x, char y){
            if(Math.abs((int)x - (int)y) == 1) {
                return true;
            }
            return false;
        }

    }
    /**nest class of comparator testing off-by-n palindrome type */
    private static class OffByN implements CharacterComparator{
        int n;

        private OffByN(int n){
            this.n = n;
        }
        @Override
        public boolean equalChars(char x, char y){
            if(Math.abs((int)x - (int)y) == n) {
                return true;
            }
            return false;
        }
    }
    /* call to instantiate a off-by-1 comparator */
    public static OffByOne choseOffByOne(){
        return new OffByOne();
    }
    /* call to instantiate a off-by-n comparator */
    public static OffByN choseOffByN(int n){
        return new OffByN(n);
    }

    /** split the String to a character Deque with sequence
     * @param word
     * @return
     */
    public Deque<Character> wordToDeque(String word){       /* what is Deque? */
        Deque<Character> d = new LinkedListDeque<>();      /* why can Deque initialize a LinkedListDeque object? */
        for (int i = 0; i < word.length(); i++){
            d.addLast(word.charAt(i));
        }
        return d;
    }

    /** identify whether the String parameter is Palindrome
     * resorting to wordToDeque to make use of data structure
     * @param word
     * @return boolean
     */
    public boolean isPalindrome(String word){
        Deque<Character> d = wordToDeque(word);
        if(!isNodeSymmetrical(d)){
            return false;
        }
        else{
            return true;
        }
    }
    /* helper recursive methods for isPalindrome */
    private boolean isNodeSymmetrical(Deque<Character> shrinking_list){
        if(shrinking_list.size() <= 1){
            return true;
        }
        else if(shrinking_list.removeFirst() != shrinking_list.removeLast()){
            return false;
        }
        return isNodeSymmetrical(shrinking_list);
    }

    /**
     * identifying whether a String is a Symmetrical String with every pair of chars are off-by-1 or off-by-n;
     * @param word String like "flake"
     * @param cc is a comparator object reference, which will be initialized for a specific comparator and used as a argument of conditional judging
     *           In other words, cc could have eligibility to implement different equalChars(),
     *           which is declare in CharacterComparator interface and defined in OffByOne and OffByN class
     */
    public boolean isPalindrome(String word, CharacterComparator cc){
        //initialize equalChars method
        //covert to deque;
        //scan each pair of chars to decide, resorting to helper methods
        //conditional branch: are the string word both true?
        Deque<Character> d = wordToDeque(word);
        boolean is_ofo_palindrome = isNodeSymmetricalOFO(d, cc);
        if(is_ofo_palindrome){
            return true;
        }
        else{
            return false;
        }

    }
    private boolean isNodeSymmetricalOFO(Deque<Character> shrinking_list, CharacterComparator cc){
        if(shrinking_list.size() <= 1){
            return true;
        }
        else if(!cc.equalChars(shrinking_list.removeFirst(),shrinking_list.removeLast())){
            return false;
        }
        return isNodeSymmetricalOFO(shrinking_list,cc);

    }
}
