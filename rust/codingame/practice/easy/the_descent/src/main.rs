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
 * The while loop represents the game.
 * Each iteration represents a turn of the game
 * where you are given inputs (the heights of the mountains)
 * and where you have to print an output (the index of the mountain to fire on)
 * The inputs you are given are automatically updated according to your last actions.
 **/
fn main() {

    let mut mountains_height: Vec<i32> = Vec::new();
    // game loop
    loop {

        for i in 0..8 as usize {
            let mut input_line = String::new();
            io::stdin().read_line(&mut input_line).unwrap();
            mountains_height.push(parse_input!(input_line, i32)); // represents the height of one mountain.
        }
        let mut max_height = mountains_height[0];
        let mut max_height_index = 0;
        for i in 1..8 as usize {
            if(mountains_height[i] > max_height) {
                max_height = mountains_height[i];
                max_height_index = i;
            }
        }
        // Write an action using println!("message...");
        // To debug: print_err!("Debug message...");
        mountains_height.clear();
        println!("{}", max_height_index); // The index of the mountain to fire on.
    }
}
