use std::io;  

fn main() {
    println!("A first string to split");
    let string_to_split = "1 2 3 4 5 6 7    10 11 12  ABC CDF GHI   ";
    println!("{}", string_to_split);
    let strings = string_to_split.split_whitespace();
    for s in strings {
        println!("{}", s);
    }

    println!("\nA string composed of i32 to split and transform");
    let numbers_in_string = "1 2 3 4 5 6 7    10 11 12  ";
    println!("{}", numbers_in_string);
    let numbers: Vec<i32> = numbers_in_string
        .split_whitespace()
        .map(|s| s.parse().unwrap())
        .collect();
    
    for n in numbers {
        println!("{}", n);
    }

    println!("\nInput a string composed of i32 to split and transform");       
    let mut buffer = String::new();
    io::stdin().read_line(&mut buffer).ok().expect("erreur à la lecture");
    let numbers_2: Vec<i32> = buffer.split_whitespace()
        .map(|s| s.parse().unwrap())
        .collect();

     for n in numbers_2 {
        println!("{}", n);
    }
}
