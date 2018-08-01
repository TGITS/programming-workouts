# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

class Person
    attr_reader :name, :parent, :birth, :death, :religion, :gender
    attr_accessor :children

    def initialize(name, parent, birth, death, religion, gender)
        @name = name
        @parent = parent
        @birth = birth
        @death = death
        @religion = religion
        @gender = gender
        @children = nil
    end

    def sortChildren()
        if not @children.nil?
            @children.sort!
        end
    end

    def alive?()
        @death.nil?
    end

    def anglican?()
        @religion == "Anglican"
    end

    def children?()
        if @children.nil? || @children.empty?
            false
        else
            true
        end
    end

    def to_s()
        "Person(#{@name} #{@parent.nil? ? "-" : @parent} #{@birth} #{@death.nil? ? "-" : @death} #{@religion} #{@gender})"
    end

    def children_to_s()
        if @children.nil? || @children.empty?
            return ""
        else 
            s = "[ "
            for c in @children do
                s << c.name << " "
            end
            s << "]"
            return s
        end
    end

    def <=>(other_person)
        if @gender == other_person.gender 
            return @birth <=> other_person.birth
        else
            if @gender == "M"
                return -1
            else
                return 1
            end
        end
    end
end

def compute_order_of_succession(root, order_of_succession)
    if root.alive?  && root.anglican?
        order_of_succession << root.name 
    end

    if root.children?
        for child in root.children do
            compute_order_of_succession(child, order_of_succession)
        end
    end
end

n = gets.to_i

persons = []

n.times do
    name, parent, birth, death, religion, gender = gets.split(" ")
    birth = birth.to_i
    death = death == "-" ? nil : death.to_i
    parent = parent == "-" ? nil : parent
    persons << Person.new(name, parent, birth, death, religion, gender)
end

root = nil
persons_by_parent = Hash.new()
persons.each do |p| 
    if p.parent.nil?
        root = p
    else 
        if persons_by_parent[p.parent].nil?
            persons_by_parent[p.parent] = [p]
        else
            persons_by_parent[p.parent] << p
        end
    end
end

STDERR.puts "Person at the root : #{root}"

persons.each do |p|
    p.children = persons_by_parent[p.name]
    p.sortChildren
    STDERR.puts "Person : #{p}"
    STDERR.puts "Children for #{p.name} : #{p.children_to_s}"
end

order_of_succession = []
compute_order_of_succession(root, order_of_succession)

order_of_succession.each do |name|
    puts "#{name}"
end