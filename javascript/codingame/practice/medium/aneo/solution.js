class Light {
    constructor(number, distance, duration) {
        this._number = number;
        this._distance = distance;
        this._duration = duration;
    }

    get number() {return this._number;}
    get distance() {return this._distance;}
    get duration() {return this._duration;}
    
    durationToReach(speed) {
        return  this.distance/speed ;
    }

    isReachedOnGreenLight(speed) {
        let durationToReach = this.durationToReach(speed);
        let rawNumberOfCycles = durationToReach / this.duration;
        let numberOfCycles = parseInt(Math.round(rawNumberOfCycles), 10);
        let minimumNumberOfCycles = parseInt(Math.floor(rawNumberOfCycles), 10);
        let maximumNumberOfCycles = parseInt(Math.ceil(rawNumberOfCycles), 10);
        printErr(`Raw number of cycles in float ${rawNumberOfCycles}`);
        printErr(`minimum number of cycles : ${minimumNumberOfCycles}`);
        printErr(`maximum number of cycles : ${maximumNumberOfCycles}`);
        printErr(`rounded number of cycles : ${numberOfCycles}`);
        return (minimumNumberOfCycles == maximumNumberOfCycles && minimumNumberOfCycles % 2 == 0) || minimumNumberOfCycles % 2 == 0;
    }

    toString() {
        return `Ligth #${this.number}\n\tdistance from the start in meters : ${this.distance}\n\tcycle duration in seconds : ${this.duration}\n`;
    }
}

class SpeedLimit {
    constructor(value, unit='km/h') {
        this._value = value;
        this._unit = unit;
    }

    get value() {return this._value;}
    get unit() {return this._unit;}

    toString() {
        return `Maximum speed limit : ${this.value} ${this.unit}\n`;
    }

    static speedInMeterBySecond(speed) {
        const NUMBER_OF_SECONDS_IN_AN_HOUR = 3600;
        const KILO = 1000;
        return speed * KILO / NUMBER_OF_SECONDS_IN_AN_HOUR;
    }
}

class GameData {
    constructor(speedLimit, numberOfLights, lights) {
        this._speedLimit = speedLimit;
        this._numberOfLights = numberOfLights;
        this._lights = lights;
    }

    get speedLimit() {return this._speedLimit;}
    get numberOfLights() {return this._numberOfLights;}
    get lights() {return this._lights;}

    computeMaxSpeedToReachAllLights() {
        let currentSpeed = this.speedLimit.value;
        let currentSpeedInMeterBySecond = SpeedLimit.speedInMeterBySecond(currentSpeed);
        let maxSpeed = 0;
        let maxSpeedFound = true;
        while (currentSpeed > 0) {
            printErr(`Speed in km/s : ${currentSpeed}`);
            printErr(`Speed in m/s : ${currentSpeedInMeterBySecond}`)
            maxSpeedFound = true;
            for(let light of this.lights) {
                printErr(`${light.number} reached on green light ? ${light.isReachedOnGreenLight(currentSpeedInMeterBySecond)}`)
                maxSpeedFound = maxSpeedFound && light.isReachedOnGreenLight(currentSpeedInMeterBySecond);
                if (maxSpeedFound == false ) { continue; }
            }
            if (maxSpeedFound) { 
                maxSpeed = currentSpeed;
                break;
            }
            currentSpeed --;
            currentSpeedInMeterBySecond = SpeedLimit.speedInMeterBySecond(currentSpeed);
            
        }
        return maxSpeed;
    }

    toString() {
        let contentToString = `${this.speedLimit}`
        contentToString += `Number of lights : ${this._numberOfLights}\n`
        for(let light of this._lights) {
            contentToString += `${light}`;
        }
        return contentToString;
    }
}

function parseInputs() {
    let speedLimit = parseInt(readline());
    let lightCount = parseInt(readline());
    let lights = [];
    for (let i = 0; i < lightCount; i++) {
        let line = readline().split(' ');
        lights.push(new Light(i+1, parseInt(line[0]), parseInt(line[1])));
    }
    return new GameData(new SpeedLimit(speedLimit), lightCount, lights);
}

let gameData = parseInputs();
// Write an action using print()
// To debug: printErr('Debug messages...');
printErr(`Game Data :\n${gameData}\n-----------\n`);
print(`${gameData.computeMaxSpeedToReachAllLights()}`);