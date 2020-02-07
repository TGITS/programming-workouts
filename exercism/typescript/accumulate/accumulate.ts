export function accumulate<T,S>(array: Array<T>, accumulator: (x: T) => S) {
    let mappedArray: Array<S> = new Array<S>();
    for (let i of array) {
        mappedArray.push(accumulator(i))
    }
    return mappedArray;
}