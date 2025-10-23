public class CalcExcel {
    /// / A
    /// / AB
    /// / ABC
    /// / ABCD
    //
    //fun calcExcelValue(src: String){
    //    for(i)
    //}
    public static void main(String[] args) {
        System.out.println(calcExcelValue("A"));
        System.out.println(calcExcelValue("AB"));
        System.out.println(calcExcelValue("ABC"));
        System.out.println(calcExcelValue("ABCD"));
    }

    public static int calcExcelValue(String input) {
        int result = 0;
        for (int i = input.length() -1 ; i >= 0; i--) {
            int position = input.length() - i - 1;
            result += (input.charAt(i) - 'A' + 1) * (int)(Math.pow(26, position));
        }

        return result;
    }

    public int titleToNumber(String columnTitle) {
        int result = 0;
        for (int i = columnTitle.length() -1 ; i >= 0; i--) {
            int position = columnTitle.length() - i - 1;
            result += (columnTitle.charAt(i) - 'A' + 1) * (int)(Math.pow(26, position));
        }

        return result;
    }
}
