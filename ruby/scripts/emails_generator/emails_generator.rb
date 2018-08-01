#1st argument is the input file => list_of_names.txt
#2nd argument is the email suffix => westeros.com
# $0 is the name of the script

if ARGV.size != 2
    puts "The script #{$0} should be givent 2 arguments"
    puts "The 1st argument is the file name for example 'list_of_names.txt'"
    puts "The 2nd argument is the email suffix for example 'westeros.com'"
    exit(-1)
else 
    #Open the file, read each line, split the line on white space characters
    #Print email value
    File.open(ARGV[0]).each do |line|
        name, forname = line.split(/\s+/)
        puts "#{forname.downcase}.#{name.downcase}@#{ARGV[1]}"
    end
end