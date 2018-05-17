public class Checker {
    public boolean isPalindrome(String word){
        boolean result = false;
        if (word == null){
            throw new IllegalArgumentException("Argument should not be null");
        }
        if (word == ""){
            result = true;
        }
        return result;
    }
}
