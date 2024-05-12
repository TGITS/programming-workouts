const std = @import("std");

fn foo(x: i32) void {
    std.debug.print("foo param: {}\n", .{x});
}

pub fn main() void {
    foo(47);
}
