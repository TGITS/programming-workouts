function range(min,max)
    local index = min
    return function()
        old_index = index
        index = index + 1
        if old_index <= max then
           return old_index
        end
        return nil
    end
end

for i in range(0,10) do
    print(i)
end

print ""

for i in range(5,10) do
    print(i)
end