use unicode_segmentation::UnicodeSegmentation;

pub fn reverse(input: &str) -> String {
    let s = String::from(input);
    let mut output = String::new();
    for grapheme in s.graphemes(true) {
        output.insert_str(0, grapheme);
    }
    output
}
