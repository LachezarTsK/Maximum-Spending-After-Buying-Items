
function maxSpending(values: number[][]): number {
    this.ALL_ITEMS_IN_SHOP_PURCHASED = -1;
    const indexesAccessibleItemsPerShop = createIndexesAccessibleItemsPerShop(values);
    return calculateMaxSpending(values, indexesAccessibleItemsPerShop);
};

function createIndexesAccessibleItemsPerShop(values: number[][]): number[] {
    const indexesAccessibleItemsPerShop: number[] = new Array(values.length);
    for (let i = 0; i < indexesAccessibleItemsPerShop.length; ++i) {
        indexesAccessibleItemsPerShop[i] = values[0].length - 1;
    }
    return indexesAccessibleItemsPerShop;
}

function calculateMaxSpending(values: number[][], indexesAccessibleItemsPerShop: number[]): number {
    let maxSpending = 0;
    let currentDay = 1;
    const totalDays = values.length * values[0].length;

    while (currentDay <= totalDays) {
        maxSpending += currentDay * findMinValueAmongAccessibleItemsPerShop(values, indexesAccessibleItemsPerShop);
        ++currentDay;
    }

    return maxSpending;
}

function findMinValueAmongAccessibleItemsPerShop(values: number[][], indexesAccessibleItemsPerShop: number[]): number {
    let minValue = Number.MAX_SAFE_INTEGER;
    let indexForMinValue = 0;

    for (let i = 0; i < indexesAccessibleItemsPerShop.length; ++i) {
        if (indexesAccessibleItemsPerShop[i] !== this.ALL_ITEMS_IN_SHOP_PURCHASED
            && minValue > values[i][indexesAccessibleItemsPerShop[i]]) {

            minValue = values[i][indexesAccessibleItemsPerShop[i]];
            indexForMinValue = i;
        }
    }
    --indexesAccessibleItemsPerShop[indexForMinValue];
    return minValue;
}
