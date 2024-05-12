const std = @import("std");
const string = "hello 世界";
const world = "world";

pub fn main() void {
    const slice: []const u8 = string[0..5];

    std.debug.print("string {s}\n", .{string});
    std.debug.print("length {}\n", .{world.len});
    std.debug.print("null {}\n", .{world[5]});
    std.debug.print("slice {s}\n", .{slice});
    std.debug.print("huh? {s}\n", .{string[0..7]});
}
