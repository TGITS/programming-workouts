pub fn is_armstrong_number(num: u32) -> bool {
    let num_as_string = num.to_string();
    let number_of_digits:u32 = num_as_string.len().try_into().unwrap();
    let mut calculus:u32 = 0;
    for c in num_as_string.chars() {
        if let Some(x) = c.to_digit(10)  {
            calculus = calculus + x.pow(number_of_digits);
        }
    }
    calculus == num
}
