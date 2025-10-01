public class MaxProfitInStock {
    public int maxProfit(int[] prices) {

        int bestBuyPrice = Integer.MAX_VALUE;
        int bestProfit = 0;

        for(int price: prices){
            if(price > bestBuyPrice){
                bestProfit = Math.max(bestProfit, price - bestBuyPrice);
            }

            if(price < bestBuyPrice){
                bestBuyPrice = price;
            }
        }

        return bestProfit;
    }
}
