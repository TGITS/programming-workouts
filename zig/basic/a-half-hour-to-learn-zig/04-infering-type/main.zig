const std = @import("std");

pub fn main() void {
    const x: i32 = 47;
    const y: i32 = 47;
    const z = x + y;
    std.debug.print("z: {}\n", .{z});
}
