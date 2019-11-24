// Package twofer should have a package comment that summarizes what it's about.
// https://golang.org/doc/effective_go.html#commentary
// The package twofer is an implementation of "Two-fer"
// "Two-fer" or "2-fer" is short for two for one. One for you and one for me.
package twofer

/*
ShareWith given a string representing a name, return a string with the message:

"One for X, one for me.""

Where X is the given name.

However, if the variable name is missing, this is a null value, an empty string or a blank string, it returns the string:

"One for you, one for me.""

*/
func ShareWith(name string) string {
	return ""
}
