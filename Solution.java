
public class Solution {

    private static final int ALL_ITEMS_IN_SHOP_PURCHASED = -1;

    public long maxSpending(int[][] values) {
        int[] indexesAccessibleItemsPerShop = createIndexesAccessibleItemsPerShop(values);
        return calculateMaxSpending(values, indexesAccessibleItemsPerShop);
    }

    private int[] createIndexesAccessibleItemsPerShop(int[][] values) {
        int[] indexesAccessibleItemsPerShop = new int[values.length];
        for (int i = 0; i < indexesAccessibleItemsPerShop.length; ++i) {
            indexesAccessibleItemsPerShop[i] = values[0].length - 1;
        }
        return indexesAccessibleItemsPerShop;
    }

    private long calculateMaxSpending(int[][] values, int[] indexesAccessibleItemsPerShop) {
        long maxSpending = 0;
        long currentDay = 1;
        int totalDays = values.length * values[0].length;

        while (currentDay <= totalDays) {
            maxSpending += currentDay * findMinValueAmongAccessibleItemsPerShop(values, indexesAccessibleItemsPerShop);
            ++currentDay;
        }

        return maxSpending;
    }

    private int findMinValueAmongAccessibleItemsPerShop(int[][] values, int[] indexesAccessibleItemsPerShop) {
        int minValue = Integer.MAX_VALUE;
        int indexForMinValue = 0;

        for (int i = 0; i < indexesAccessibleItemsPerShop.length; ++i) {
            if (indexesAccessibleItemsPerShop[i] != ALL_ITEMS_IN_SHOP_PURCHASED
                    && minValue > values[i][indexesAccessibleItemsPerShop[i]]) {

                minValue = values[i][indexesAccessibleItemsPerShop[i]];
                indexForMinValue = i;
            }
        }
        --indexesAccessibleItemsPerShop[indexForMinValue];
        return minValue;
    }
}
