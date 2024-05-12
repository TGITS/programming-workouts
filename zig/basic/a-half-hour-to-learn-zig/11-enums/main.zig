const std = @import("std");

const EnumType = enum { EnumOne, EnumTwo, EnumThree };

pub fn main() void {
    std.debug.print("One: {}\n", .{EnumType.EnumOne});
    std.debug.print("Two?: {}\n", .{EnumType.EnumTwo == .EnumTwo});
    std.debug.print("Three?: {}\n", .{@intFromEnum(EnumType.EnumThree) == 3});
}
