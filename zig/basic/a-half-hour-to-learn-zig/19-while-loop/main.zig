const std = @import("std");

pub fn main() void {
    const array = [_]i32{ 47, 48, 49 };
    var index: u32 = 0;

    while (index < 2) {
        std.debug.print("value: {}\n", .{array[index]});
        index += 1;
    }
}
