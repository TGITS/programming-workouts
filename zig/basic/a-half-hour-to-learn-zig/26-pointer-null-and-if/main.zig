const std = @import("std");

fn nullChoice(value: ?*i32) void {
    if (value) |v| {
        std.debug.print("value: {}\n", .{v.*});
    } else {
        std.debug.print("null!\n", .{});
    }
}

pub fn main() void {
    var value: i32 = 47;
    const vptr1: ?*i32 = &value;
    const vptr2: ?*i32 = null;

    nullChoice(vptr1);
    nullChoice(vptr2);
}
