const std = @import("std");

pub fn main() void {
    const array: [3]u32 = [3]u32{ 47, 47, 47 };

    // also valid:
    // var array = [_]u32{47, 47, 47};

    //const invalid = array[4]; // error: index 4 outside array of size 3.
    std.debug.print("array[0]: {}\n", .{array[0]});
    std.debug.print("length: {}\n", .{array.len});
}
