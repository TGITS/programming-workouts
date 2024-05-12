const std = @import("std");
const MyError = error{GenericError};

fn foo(v: i32) !i32 {
    if (v == 42) return MyError.GenericError;
    return v;
}

pub fn main() !void {
    // catch traps and handles errors bubbling up
    _ = foo(42) catch |err| {
        std.debug.print("error: {}\n", .{err});
    };

    // try won't get activated here.
    std.debug.print("foo: {}\n", .{try foo(47)});

    // this will ultimately cause main to print an error trace and return nonzero
    _ = try foo(42);
}
