const std = @import("std");

const Vec2 = struct { x: f64, y: f64 };

pub fn main() void {
    const v = Vec2{ .y = 1.0, .x = 2.0 };
    std.debug.print("v: {}\n", .{v});
}
