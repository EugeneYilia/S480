public class CompareString2 {
    public boolean isIsomorphic(String s, String t) {
        if(s.length() != t.length()) {
            return false;
        }

        int[] mapST = new int[26];
        int[] mapTS = new int[26];

        for(int i=0;i<s.length();i++){
            int indexS = s.charAt(i) - 'a';
            int indexT = t.charAt(i) - 'a';
            mapST[indexT]++;
            mapTS[indexS]++;

            if(mapST[indexT] != mapTS[indexS]){
                return false;
            }
        }

        return true;
    }
}
