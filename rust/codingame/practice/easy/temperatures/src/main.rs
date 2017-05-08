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

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
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

    //let vec: Vec<&str> = temps.split_whitespace().collect();

    //for s in vec {
    //    print_err!("Read temperature : {}", s);
    //}

    //let numbers: Vec<i32> = reader.read_line().unwrap().as_slice().split_whitespace().map(|s| s.parse().unwrap()).collect()
    //let my_int: i32 = my_string.parse().unwrap();
    let temperatures: Vec<i32> = temps.split_whitespace().map(|s| s.parse().unwrap()).collect();
    for t in temperatures {
        print_err!("Read temperature : {}", t);
    }
    // Write an action using println!("message...");
    // To debug: print_err!("Debug message...");

    println!("result");
}
