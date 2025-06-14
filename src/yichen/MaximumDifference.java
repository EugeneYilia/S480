package yichen;

public class MaximumDifference {
    public static void main(String[] args) {
        var testData = 11891;
        System.out.println(testData);
        System.out.println(new MaximumDifference().minMaxDifference(testData));
    }

    public int minMaxDifference(int num) {
        var maxValue = 0;
        var minValue = 0;
        var numString = String.valueOf(num);

        var isNewMaxValueExist = false;
        var maxNewValue = new StringBuilder();
        var minNewValue = new StringBuilder();
        for (int i = 0; i < numString.length(); i++) {
            var currentNumber = numString.charAt(i) - '0';
            if(i == 0){
                minValue = currentNumber;
            }

            if(currentNumber == minValue){
                minNewValue.append("0");
            } else {
                minNewValue.append(currentNumber);
            }

            if(!isNewMaxValueExist){
                if(currentNumber != 9){
                    maxValue = currentNumber;
                    isNewMaxValueExist = true;
                }
            }

            if(!isNewMaxValueExist){
                maxNewValue.append(currentNumber);
            } else {
                if(currentNumber == maxValue){
                    maxNewValue.append("9");
                } else {
                    maxNewValue.append(currentNumber);
                }
            }
        }

        return  Integer.parseInt(maxNewValue.toString()) - Integer.parseInt(minNewValue.toString());
    }
}