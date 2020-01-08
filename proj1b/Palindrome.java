public class Palindrome
{
    public Deque<Character> wordToDeque(String word)
    {
        LinkedListDeque<Character> lld = new LinkedListDeque<>();
        for(int i = 0; i < word.length(); i++)
        {
            lld.addLast(word.charAt(i));
        }
        return lld;
    }

    /**
     * 判断给定字符串是否是回文串
     * @param word
     * @return
     */
    public boolean isPalindrome(String word)
    {
        LinkedListDeque<Character> lld = (LinkedListDeque<Character>) wordToDeque(word);
        if(lld.size() <= 1)
            return true;
        while(lld.size() > 1)
        {
            Character firstC = lld.removeFirst();
            Character lastC = lld.removeLast();
            if(firstC != lastC)
                return false;
        }
/*      if(word.length() <= 1)
            return true;
        int index = 0;
        while(index < (word.length() + 1) / 2)
        {
            if(word.charAt(index) != word.charAt(word.length() - 1 - index))
                return false;
            index++;
        }*/
        return true;
    }

    public boolean isPalindromeRecursive(String word)
    {
        LinkedListDeque<Character> lld = (LinkedListDeque<Character>) wordToDeque(word);
        return helper(lld);
    }

    private boolean helper(Deque d)
    {
        if(d.size() <= 1) {
            return true;
        }
        else {
            if(d.removeFirst() == d.removeLast())
                return helper(d);
            return false;
        }
    }
}
