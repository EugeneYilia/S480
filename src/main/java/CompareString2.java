public class CompareString2 {
    public boolean isIsomorphic(String s, String t) {
        if(s.length() != t.length()) {
            return false;
        }

        int[] mapST = new int[256];
        int[] mapTS = new int[256];

        for(int i=0;i<s.length();i++){
            int indexS = s.charAt(i);
            int indexT = t.charAt(i);

            if(mapST[indexS] != 0){
                if(mapST[indexS] != indexT + 1){
                    return false;
                }
            } else {
                // a - a
                // 0 - 1
                mapST[indexS] = indexT + 1;
            }

            if(mapTS[indexT] != 0){
                if(mapTS[indexT] != indexS + 1){
                    return false;
                }
            } else {
                // default 0, so the value should be index + 1
                // a - a
                // 0 - 1
                mapTS[indexT] = indexS + 1;
            }
        }

        return true;
    }
}
