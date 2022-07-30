pub fn reverse(input: &str) -> String {
    let s = String::from(input);
    let mut output = String::new();
    for c in s.chars() {
        output.insert(0, c);
    }
    output
}
