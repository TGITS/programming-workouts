/** 
 * Original proposed input parsing solution
 * var speed = parseInt(readline());
 * var lightCount = parseInt(readline());
 * for (var i = 0; i < lightCount; i++) {
 *      var inputs = readline().split(' ');
 *       var distance = parseInt(inputs[0]);
 *       var duration = parseInt(inputs[1]);
 *}
 */
class Light {
    constructor(number, distance, duration) {
        this._number = number;
        this._distance = distance;
        this._duration = duration;
    }
    get number() { return this._number; }
    get distance() { return this._distance; }
    get duration() { return this._duration; }
    
    toString() {
        return `Ligth #${this.number}, distance from the start in meters : ${this.distance}, cycle duration in seconds : ${this.duration}`;
    }
}

inputs = parseInputs();
// Write an action using print()
// To debug: printErr('Debug messages...');
displayInputs(inputs);
print('answer');

function parseInputs() {
    let inputs = {
        lights : []
    };
    inputs.speed = parseInt(readline());
    inputs.lightCount = parseInt(readline());
    for (var i = 0; i < inputs.lightCount; i++) {
        var lines = readline().split(' ');
        inputs.lights[i] = new Light(i+1, parseInt(lines[0]), parseInt(lines[1]));
    }
    return inputs;
}

function displayInputs(inputs) {
    printErr(`Maximum speed limit in km/h: ${inputs.speed}`);
    printErr(`Number of light(s) : ${inputs.lightCount}`);
    for(light of inputs.lights) {
        printErr(`${light}`);
    }
}
