import java.util.*;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String expression = in.next();

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");
        boolean test = Solution.openCloseSentenceRecognizer(expression);
        if (test) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }

    /*
     * @param s String with only {}[]()
     *
     * @return boolean if the sentence are correct {}[]() is correct {[()]} is
     * correct ([)] is not correct
     */
    private static boolean openCloseSentenceRecognizer(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '{':
                case '(':
                case '[':
                    stack.push(s.charAt(i));
                    break;
                case '}':
                case ')':
                case ']':
                    if (stack.isEmpty() || !correspondingChar(stack.pop(), s.charAt(i))) {
                        return false;
                    }
                    break;
                default:
                    break;
            }
        }
        return stack.isEmpty();
    }

    private static boolean correspondingChar(char c1, char c2) {
        switch (c1) {
            case '{':
                return c2 == '}';
            case '(':
                return c2 == ')';
            case '[':
                return c2 == ']';
            default:
                return false;
        }
    }
}