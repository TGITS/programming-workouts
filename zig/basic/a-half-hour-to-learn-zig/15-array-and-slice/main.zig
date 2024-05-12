const std = @import("std");

fn foo() []const u8 { // note function returns a slice
    return "foo"; // but this is a const array.
}

pub fn main() void {
    std.debug.print("foo: {s}\n", .{foo()});
}
