const std = @import("std");

const MyError = error{
    GenericError,
    OtherError,
}; // just a list of identifiers, like an enum.

fn foo() MyError!void {
    return MyError.GenericError;
}

pub fn main() !void {
    return MyError.GenericError;
}
