
#include <span>
#include <limits>
#include <vector>
using namespace std;

class Solution {

    static const int  ALL_ITEMS_IN_SHOP_PURCHASED = -1;

public:
    long long maxSpending(const vector<vector<int>>& values) const {
        vector<int> indexesAccessibleItemsPerShop = createIndexesAccessibleItemsPerShop(values);
        return calculateMaxSpending(values, indexesAccessibleItemsPerShop);
    }

private:
    vector<int> createIndexesAccessibleItemsPerShop(span<const vector<int>> values) const {
        vector<int> indexesAccessibleItemsPerShop(values.size());
        for (size_t i = 0; i < indexesAccessibleItemsPerShop.size(); ++i) {
            indexesAccessibleItemsPerShop[i] = values[0].size() - 1;
        }
        return indexesAccessibleItemsPerShop;
    }

    long long calculateMaxSpending(span<const vector<int>> values, span<int> indexesAccessibleItemsPerShop) const {
        long long maxSpending = 0;
        long long currentDay = 1;
        int totalDays = values.size() * values[0].size();

        while (currentDay <= totalDays) {
            maxSpending += currentDay * findMinValueAmongAccessibleItemsPerShop(values, indexesAccessibleItemsPerShop);
            ++currentDay;
        }

        return maxSpending;
    }

    int findMinValueAmongAccessibleItemsPerShop(span<const vector<int>> values, span<int> indexesAccessibleItemsPerShop) const {
        int minValue = numeric_limits<int>::max();
        int indexForMinValue = 0;

        for (size_t i = 0; i < indexesAccessibleItemsPerShop.size(); ++i) {
            if (indexesAccessibleItemsPerShop[i] != ALL_ITEMS_IN_SHOP_PURCHASED
                && minValue > values[i][indexesAccessibleItemsPerShop[i]]) {

                minValue = values[i][indexesAccessibleItemsPerShop[i]];
                indexForMinValue = i;
            }
        }
        --indexesAccessibleItemsPerShop[indexForMinValue];
        return minValue;
    }
};
