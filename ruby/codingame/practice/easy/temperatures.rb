# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

@n = gets.to_i # the number of temperatures to analyse
@temps = gets.chomp # the n temperatures expressed as integers ranging from -273 to 5526

# Write an action using puts
# To debug: STDERR.puts "Debug messages..."

result = "0"

if @n > 0
    STDERR.puts "Received temperatures : " + @temps
    temperatures = @temps.split(" ").collect { |i| i.to_i }
    positive_temperatures = temperatures.select { |i| i >= 0 }
    min_positive_temperature = nil
    if not positive_temperatures.empty?
        min_positive_temperature = positive_temperatures.min
    end
    STDERR.puts "min_positive_temperature : " + min_positive_temperature.to_s

    negative_temperatures = temperatures.select { |i| i < 0 }
    max_negative_temperature = nil
    if not negative_temperatures.empty?
        max_negative_temperature = negative_temperatures.max
    end
    STDERR.puts "max_negative_temperature : " + max_negative_temperature.to_s

    if min_positive_temperature.nil? and max_negative_temperature.nil?
        result = "0"
    elsif min_positive_temperature.nil?
        result = max_negative_temperature.to_s
    elsif max_negative_temperature.nil?
        result = min_positive_temperature.to_s
    elsif min_positive_temperature <= max_negative_temperature.abs
        result = min_positive_temperature.to_s
    else
        result = max_negative_temperature.to_s
    end
end

puts result
