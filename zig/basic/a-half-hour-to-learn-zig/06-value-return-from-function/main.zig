const std = @import("std");

fn foo() i32 {
    return 47;
}

pub fn main() void {
    const result = foo();
    std.debug.print("foo: {}\n", .{result});
}
