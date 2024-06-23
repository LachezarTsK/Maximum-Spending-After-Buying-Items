
package main

import (
    "fmt"
    "math"
)

const ALL_ITEMS_IN_SHOP_PURCHASED = -1

func maxSpending(values [][]int) int64 {
    var indexesAccessibleItemsPerShop []int = createIndexesAccessibleItemsPerShop(values)
    return calculateMaxSpending(values, indexesAccessibleItemsPerShop)
}

func createIndexesAccessibleItemsPerShop(values [][]int) []int {
    indexesAccessibleItemsPerShop := make([]int, len(values))
    for i := range indexesAccessibleItemsPerShop {
        indexesAccessibleItemsPerShop[i] = len(values[0]) - 1
    }
    return indexesAccessibleItemsPerShop
}

func calculateMaxSpending(values [][]int, indexesAccessibleItemsPerShop []int) int64 {
    var maxSpending int64 = 0
    var currentDay int64 = 1
    var totalDays int64 = int64(len(values) * len(values[0]))

    for currentDay <= totalDays {
        maxSpending += currentDay * int64(findMinValueAmongAccessibleItemsPerShop(values, indexesAccessibleItemsPerShop))
        currentDay++
    }

    return maxSpending
}

func findMinValueAmongAccessibleItemsPerShop(values [][]int, indexesAccessibleItemsPerShop []int) int {
    minValue := math.MaxInt
    indexForMinValue := 0

    for i := range indexesAccessibleItemsPerShop {
        if indexesAccessibleItemsPerShop[i] != ALL_ITEMS_IN_SHOP_PURCHASED &&
            minValue > values[i][indexesAccessibleItemsPerShop[i]] {

            minValue = values[i][indexesAccessibleItemsPerShop[i]]
            indexForMinValue = i
        }
    }
    indexesAccessibleItemsPerShop[indexForMinValue]--
    return minValue
}
