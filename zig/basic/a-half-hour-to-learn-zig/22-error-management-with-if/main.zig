const std = @import("std");
const MyError = error{GenericError};

fn foo(v: i32) !i32 {
    if (v == 42) return MyError.GenericError;
    return v;
}

// note that it is safe for wrap_foo to not have an error ! because
// we handle ALL cases and don't return errors.
fn wrap_foo(v: i32) void {
    if (foo(v)) |value| {
        std.debug.print("value: {}\n", .{value});
    } else |err| {
        std.debug.print("error: {}\n", .{err});
    }
}

pub fn main() void {
    wrap_foo(42);
    wrap_foo(47);
}
