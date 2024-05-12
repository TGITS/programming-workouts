const std = @import("std");

pub fn main() void {
    var array = [_]i32{ 47, 48, 49 };

    for (array) |value| {
        std.debug.print("array {}\n", .{value});
    }
    for (array, 0..) |value, index| {
        std.debug.print("array {}:{}\n", .{ index, value });
    }

    const slice = array[0..2];

    for (slice) |value| {
        std.debug.print("slice {}\n", .{value});
    }
    for (slice, 0..) |value, index| {
        std.debug.print("slice {}:{}\n", .{ index, value });
    }
}
