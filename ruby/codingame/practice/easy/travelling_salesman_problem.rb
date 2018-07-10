# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

# The travelling salesman problem (TSP) asks the following question: 
# "Given a list of cities and the distances between each pair of cities, 
# what is the shortest possible route that visits each city exactly once and returns to the origin city?"
# In this puzzle not necessarily the shortest route is the answer but an approximation using the greedy algorithm 
# (which in fact could be the shortest route as well).
# The greedy algorithm starts at the first input given and always chooses the nearest point next to it. 
# This continues until no points are left and the last point is connected to the first point.
# Use the euclidian distance, i.e. sqrt(deltaX^2 + deltaY^2), as the distance between two cities. 
# If there are points with the same distance, always pick the one occurring first in the list.
# In general, the greedy algorithm does not find the optimal solution, but nonetheless a greedy heuristic may 
# yield locally optimal solutions that approximate a global optimal solution in a reasonable time.

class City

    attr_reader :x, :y

    def initialize(x,y)
        @x = x
        @y = y
    end

    def distance(other_city)
        Math.sqrt((other_city.x - @x)**2 + (other_city.y - @y)**2)
    end

    def to_s()
        "City(#{@x},#{@y})"
    end

end

cities = []
@n = gets.to_i
@n.times do
    x, y = gets.split(" ").collect {|x| x.to_i}
    cities << City.new(x,y)
end

cumulative_distance = 0
previous_city = nil
current_city = cities.shift
first_city = current_city
STDERR.puts "cities : #{cities.map {|city| city.to_s}}"

until cities.empty? do
    cities_sorted_by_distance = cities.sort_by {|city| city.distance(current_city)}
    nearest_city = cities_sorted_by_distance.first
    previous_city = current_city
    current_city = cities.delete(nearest_city)
    distance = previous_city.distance(current_city)
    cumulative_distance += distance
end

cumulative_distance += current_city.distance(first_city)
STDERR.puts "cumulative distance : #{cumulative_distance}"

# Write an action using puts
# To debug: STDERR.puts "Debug messages..."

puts cumulative_distance.round.to_s