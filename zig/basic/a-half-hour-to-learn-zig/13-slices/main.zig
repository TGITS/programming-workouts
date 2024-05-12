const std = @import("std");

pub fn main() void {
    var array: [3]u32 = [_]u32{ 47, 47, 47 };
    const slice: []u32 = array[0..2];

    // also valid:
    // var slice = array[0..2];

    //var invalid = slice[3]; // panic: index out of bounds

    std.debug.print("slice[0]: {}\n", .{slice[0]});
    std.debug.print("length: {}\n", .{slice.len});
}
