const std = @import("std");

pub fn main() void {
    var value: i32 = 47;
    const vptr: ?*i32 = &value;
    const throwaway1: ?*i32 = null;
    //const throwaway2: *i32 = null; // error: expected type '*i32', found '(null)'

    //std.debug.print("value: {}\n", .{vptr.*}); // error: attempt to dereference non-pointer type
    std.debug.print("value: {?}\n", .{throwaway1});
    std.debug.print("value: {?}\n", .{vptr.?.*});
}
