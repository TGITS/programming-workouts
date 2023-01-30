// https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Generator
// https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Iteration_protocols
// https://basarat.gitbook.io/typescript/future-javascript/generators
// https://dev.to/gsarciotto/generators-in-typescript-4b37

function* natural_integer(starting_integer = 0) {
    let current_value = 0;
    while (true) {
        yield current_value;
        current_value++;
    }

}

const gen = natural_integer();

for (let i = 0; i < 10; i++) {
    let { value, done} = gen.next();
    console.log(`Value : ${value}`);
    console.log(`Is the iterator done : ${done}`);
}