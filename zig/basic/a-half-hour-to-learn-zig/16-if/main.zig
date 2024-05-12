const std = @import("std");

fn foo(v: i32) []const u8 {
    if (v < 0) {
        return "negative";
    } else {
        return "non-negative";
    }
}

pub fn main() void {
    std.debug.print("positive {s}\n", .{foo(47)});
    std.debug.print("negative {s}\n", .{foo(-47)});
}
