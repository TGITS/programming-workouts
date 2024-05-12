const std = @import("std");

fn foo(v: i32) []const u8 {
    switch (v) {
        0 => return "zero",
        else => return "nonzero",
    }
}

pub fn main() void {
    std.debug.print("47 {s}\n", .{foo(47)});
    std.debug.print("0 {s}\n", .{foo(0)});
}
