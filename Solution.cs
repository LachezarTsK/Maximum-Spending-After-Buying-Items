
using System;

public class Solution
{
    private static readonly int ALL_ITEMS_IN_SHOP_PURCHASED = -1;

    public long MaxSpending(int[][] values)
    {
        int[] indexesAccessibleItemsPerShop = CreateIndexesAccessibleItemsPerShop(values);
        return CalculateMaxSpending(values, indexesAccessibleItemsPerShop);
    }

    private int[] CreateIndexesAccessibleItemsPerShop(int[][] values)
    {
        int[] indexesAccessibleItemsPerShop = new int[values.Length];
        for (int i = 0; i < indexesAccessibleItemsPerShop.Length; ++i)
        {
            indexesAccessibleItemsPerShop[i] = values[0].Length - 1;
        }
        return indexesAccessibleItemsPerShop;
    }

    private long CalculateMaxSpending(int[][] values, int[] indexesAccessibleItemsPerShop)
    {
        long maxSpending = 0;
        long currentDay = 1;
        int totalDays = values.Length * values[0].Length;

        while (currentDay <= totalDays)
        {
            maxSpending += currentDay * FindMinValueAmongAccessibleItemsPerShop(values, indexesAccessibleItemsPerShop);
            ++currentDay;
        }

        return maxSpending;
    }

    private int FindMinValueAmongAccessibleItemsPerShop(int[][] values, int[] indexesAccessibleItemsPerShop)
    {
        int minValue = int.MaxValue;
        int indexForMinValue = 0;

        for (int i = 0; i < indexesAccessibleItemsPerShop.Length; ++i)
        {
            if (indexesAccessibleItemsPerShop[i] != ALL_ITEMS_IN_SHOP_PURCHASED
                    && minValue > values[i][indexesAccessibleItemsPerShop[i]])
            {
                minValue = values[i][indexesAccessibleItemsPerShop[i]];
                indexForMinValue = i;
            }
        }
        --indexesAccessibleItemsPerShop[indexForMinValue];
        return minValue;
    }
}
