
class Solution {

    private companion object {
        const val ALL_ITEMS_IN_SHOP_PURCHASED = -1
    }

    fun maxSpending(values: Array<IntArray>): Long {
        val indexesAccessibleItemsPerShop = createIndexesAccessibleItemsPerShop(values)
        return calculateMaxSpending(values, indexesAccessibleItemsPerShop)
    }

    private fun createIndexesAccessibleItemsPerShop(values: Array<IntArray>): IntArray {
        val indexesAccessibleItemsPerShop = IntArray(values.size)
        for (i in indexesAccessibleItemsPerShop.indices) {
            indexesAccessibleItemsPerShop[i] = values[0].size - 1
        }
        return indexesAccessibleItemsPerShop
    }

    private fun calculateMaxSpending(values: Array<IntArray>, indexesAccessibleItemsPerShop: IntArray): Long {
        var maxSpending: Long = 0
        var currentDay: Long = 1
        val totalDays: Int = values.size * values[0].size

        while (currentDay <= totalDays) {
            maxSpending += currentDay * findMinValueAmongAccessibleItemsPerShop(values, indexesAccessibleItemsPerShop)
            ++currentDay
        }

        return maxSpending
    }

    private fun findMinValueAmongAccessibleItemsPerShop(values: Array<IntArray>, indexesAccessibleItemsPerShop: IntArray): Int {
        var minValue = Int.MAX_VALUE
        var indexForMinValue = 0

        for (i in indexesAccessibleItemsPerShop.indices) {
            if (indexesAccessibleItemsPerShop[i] != ALL_ITEMS_IN_SHOP_PURCHASED
                && minValue > values[i][indexesAccessibleItemsPerShop[i]]
            ) {

                minValue = values[i][indexesAccessibleItemsPerShop[i]]
                indexForMinValue = i
            }
        }
        --indexesAccessibleItemsPerShop[indexForMinValue]
        return minValue
    }
}
