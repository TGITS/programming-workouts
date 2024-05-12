const std = @import("std");

const MyStruct = struct { value: i32 };

pub fn printer(s: *MyStruct) void {
    std.debug.print("value: {}\n", .{s.value});
}

pub fn main() void {
    var value = MyStruct{ .value = 47 };
    printer(&value);
}
