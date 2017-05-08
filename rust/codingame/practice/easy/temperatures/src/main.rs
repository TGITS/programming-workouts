use std::io;

macro_rules! print_err {
    ($($arg:tt)*) => (
        {
            use std::io::Write;
            writeln!(&mut ::std::io::stderr(), $($arg)*).ok();
        }
    )
}

macro_rules! parse_input {
    ($x:expr, $t:ident) => ($x.trim().parse::<$t>().unwrap())
}


fn to_temperature_with_absolute_value(t: i32) -> (i32, i32) {
    (t, t.abs())
}

fn main() {
    print_err!("Enter the numer of temperatures that will be input: ");
    let mut input_line = String::new();
    io::stdin().read_line(&mut input_line).unwrap();
    let n = parse_input!(input_line, i32); // the number of temperatures to analyse
    print_err!("The number of temperatures to input : {}", n);
    let mut input_line = String::new();
    print_err!("Enter the list of temperatures : ");
    io::stdin().read_line(&mut input_line).unwrap();
    let temps = input_line.trim_right().to_string(); // the n temperatures expressed as integers ranging from -273 to 5526
    print_err!("The line of temperatures input : {}", temps);

    let result:i32 = if n == 0 {
            0
        }
        else {
            let temperatures: Vec<(i32,i32)> = temps.split_whitespace().map(|s| to_temperature_with_absolute_value(s.parse().unwrap())).collect();
            print_err!("(Temperature,Temperature in absolute value)");
            let mut min_abs:(i32,i32) = temperatures[0];
            for t in temperatures {
                print_err!("({},{})", t.0,t.1);
                if t.1 < min_abs.1 || (t.1 == min_abs.1 && t.0 >= 0) {
                    min_abs = t;
                }
            }

            min_abs.0
        };
    println!("{}",result);
}
