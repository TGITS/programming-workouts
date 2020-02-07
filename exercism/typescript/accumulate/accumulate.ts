export function accumulate<T,S>(array: Array<T>, accumulator: (x: T) => S) {
    let mappedArray: Array<S> = new Array<S>(array.length);
    for (let i = 0; i < array.length; i++) {
        mappedArray[i] = accumulator(array[i])
    }
    return mappedArray;
}