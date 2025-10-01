import java.util.LinkedList;

public class BracketMatch {
    public boolean isValid(String s) {
        LinkedList<Character> buffer = new LinkedList<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                buffer.push(c);
            } else {
                if (buffer.isEmpty() || !isMatch(buffer.pop(), c)) {
                    return false;
                }
            }
        }

        return buffer.isEmpty();
    }

    public static boolean isMatch(char previousChar, char newChar) {
        return switch (previousChar) {
            case '(' -> newChar == ')';
            case '[' -> newChar == ']';
            case '{' -> newChar == '}';
            default -> false;
        };
    }
}
