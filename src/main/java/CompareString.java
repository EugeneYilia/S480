import java.util.ArrayList;
import java.util.HashMap;

public class CompareString {
    public static boolean compareString(String src1, String src2) {
        HashMap<Character, ArrayList<Integer>> charPositions = new HashMap();

        for (int i = 0; i < src1.length(); i++) {
            charPositions.computeIfAbsent(src1.charAt(i), k -> new ArrayList()).add(i);
        }

        for(ArrayList<Integer> positions:charPositions.values()){
            char c = src2.charAt(positions.getFirst());
            for(int index:positions){
                if (src2.charAt(index) != c) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isIsomorphic(String s, String t) {
        return compareString(s,t) && compareString(t,s);
    }
}
