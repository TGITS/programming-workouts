const std = @import("std");

fn foo(x: anytype) @TypeOf(x) {
    // note that this if statement happens at compile-time, not runtime.
    if (@TypeOf(x) == i64) {
        return x + 2;
    } else {
        return 2 * x;
    }
}

pub fn main() void {
    const x: i64 = 47;
    const y: i32 = 47;

    std.debug.print("i64-foo: {}\n", .{foo(x)});
    std.debug.print("i32-foo: {}\n", .{foo(y)});
}
