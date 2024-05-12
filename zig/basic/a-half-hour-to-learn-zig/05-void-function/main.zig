const std = @import("std");

fn foo() void {
    std.debug.print("foo!\n", .{});
    //optional
    return;
}

pub fn main() void {
    foo();
}
