# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

class Light

    attr_reader :number, :distance, :duration

    def initialize(number, distance, duration)
        @number = number
        @distance = distance
        @duration = duration
    end

    def durationToReach(speed)
        STDERR.puts "Distance : #{@distance}"
        STDERR.puts "Speed : #{speed}"
        Rational(@distance,speed)
    end

    def isReachedOnGreenLight?(speed)
        durationToReach = durationToReach(speed)
        STDERR.puts "Duration to reach : #{durationToReach}"
        stepsToReach = Rational(durationToReach,@duration)
        minimumStepsToReach = stepsToReach.floor
        maximumStepsToReach = stepsToReach.ceil
        STDERR.puts "minimum number of steps : #{minimumStepsToReach} - #{minimumStepsToReach*@duration*speed}"
        STDERR.puts "maximum number of steps : #{maximumStepsToReach} - #{maximumStepsToReach*@duration*speed}"
        STDERR.puts "number of steps : #{stepsToReach} ~ #{stepsToReach.to_f} - #{stepsToReach*@duration*speed} ~ #{stepsToReach.to_f*@duration*speed}"
        (minimumStepsToReach == maximumStepsToReach && minimumStepsToReach % 2 == 0) or minimumStepsToReach % 2 == 0
    end

    def to_s()
        "Light(##{@number},#{@distance},#{@duration})" 
    end
end

class SpeedLimit

    attr_reader :value, :unit

    def self.speedInMeterBySecond(speed)
        Rational(speed,1) * Rational(1000,3600);
    end

    def initialize(value, unit="km/h")
        @value = value
        @unit = unit
    end

    def to_s()
        "SpeedLimit(#{@value},#{@unit})"
    end
end

class GameData

    attr_reader :speedLimit, :numberOfLights, :lights

    def self.buildFromInputs()
        speed = gets.to_i
        light_count = gets.to_i
        lights = []
        count = 1
        light_count.times do
            distance, duration = gets.split(" ").collect {|x| x.to_i}
            lights << Light.new(count, distance, duration)
            count += 1
        end
        GameData.new(SpeedLimit.new(speed), light_count, lights)
    end

    def initialize(speedLimit, numberOfLights, lights)
        @speedLimit = speedLimit
        @numberOfLights = numberOfLights
        @lights = lights
    end

    def maxSpeedToReachAllLights()
        maxSpeed = 0
        maxSpeedFound = true
        @speedLimit.value.downto(1) do |currentSpeed| 
            STDERR.puts "Speed in km/s : #{currentSpeed}"
            currentSpeedInMeterBySecond = SpeedLimit.speedInMeterBySecond(currentSpeed)
            STDERR.puts "Speed in m/s : #{currentSpeedInMeterBySecond} ~ #{currentSpeedInMeterBySecond.to_f}"
            maxSpeedFound = true
            for light in @lights 
                #STDERR.puts "#{light.number} reached on green light ? #{light.isReachedOnGreenLight?(currentSpeedInMeterBySecond)}"
                maxSpeedFound = maxSpeedFound && light.isReachedOnGreenLight?(currentSpeedInMeterBySecond)
            end
            if (maxSpeedFound) 
                return currentSpeed
            end
        end
        maxSpeed
    end

    def to_s()
        "GameData(#{@speedLimit},#{@numberOfLights},#{@lights})"
    end
end

# Write an action using puts
# To debug: STDERR.puts "Debug messages..."
gameData = GameData.buildFromInputs
STDERR.puts "#{gameData}"
puts "#{gameData.maxSpeedToReachAllLights}"