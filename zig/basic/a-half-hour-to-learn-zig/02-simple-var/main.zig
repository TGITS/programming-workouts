const std = @import("std");

pub fn main() void {
    var x: i32 = 47; //declares "x" of type i32 to be 47
    x += 3;
    std.debug.print("x: {}\n", .{x});
    const y: i32 = 65;
    std.debug.print("y: {}\n", .{y});
}
