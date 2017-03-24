-- Auto-generated code below aims at helping you parse
-- the standard input according to the problem statement.
-- Write an action using print()
-- To debug: io.stderr:write("Debug message\n")

n = tonumber(io.read()) -- the number of temperatures to analyse
temps = io.read() -- the n temperatures expressed as integers ranging from -273 to 5526

io.stderr:write(string.format("number to read : %d \n", n))
io.stderr:write(string.format("temperatures : %q \n", temps))

local result = 0

if n>0 then
    local hash = {}
    local t = {}
    local t2 = {}
    for i in string.gmatch(temps, "%S+") do
        io.stderr:write(string.format("read temperature : %d\n",i))
        table.insert(t,tonumber(i))
        table.insert(t2,math.abs(tonumber(i)))
    end
    table.sort(t)
    for k,v in pairs(t) do
        io.stderr:write(string.format("t[%d]=%d\n",k,v))
        hash[string.format("%d",math.abs(v))] = v
    end
    table.sort(t2)
    for k,v in pairs(t2) do
        io.stderr:write(string.format("t2[%d]=%d\n",k,v))
    end
    result = hash[string.format("%d",t2[1])]
end



print(string.format("%d",result))
