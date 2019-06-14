const encode = input => {
    if (input.length <= 1) {
        return input;
    }
    let output = '';
    const length = input.length;
    let i = 0;
    let currentChar = '';
    let counter = 0;
    while (i <= length) {
        if (currentChar === input.charAt(i)) {
            counter++;
        }
        else if (currentChar === '') {
            currentChar = input.charAt(i);
            counter++;
        }
        else if (currentChar !== input.charAt(i)) {
            if (counter === 1) {
                output += currentChar;
            }
            else {
                output += '' + counter + currentChar;
            }
            currentChar = input.charAt(i);
            counter = 1;
        }
        i++;
    }
    return output;
}

const decode = input => {
    if (input.length <= 1) {
        return input;
    }
    let output = '';
    const length = input.length;
    let i = 0;
    while (i < length) {
        if (input.charAt(i).match(/[a-zA-Z]|\s/)) {
            output += input.charAt(i);
            i++;
        } else {
            let timesAsString = '';
            while (input.charAt(i).match(/\d/)) {
                timesAsString += input.charAt(i);
                i++;
            }
            const times = parseInt(timesAsString);
            output += Array(times).fill(input.charAt(i), 0, times - 1).join("")
        }
    }
    return output;
}

export { encode, decode }