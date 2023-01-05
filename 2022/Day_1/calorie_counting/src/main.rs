use std::fs::File;
use std::io::{self, BufRead};
use std::path::Path;
use std::time::{SystemTime};

fn read_lines<P>(filename: P) -> io::Result<io::Lines<io::BufReader<File>>>
where P: AsRef<Path>, {
    let file = File::open(filename)?;
    Ok(io::BufReader::new(file).lines())
}


fn main() {
    let start = SystemTime::now();
    if let Ok(lines) = read_lines("/home/mioshek/Programming_Stuff/Programming/Rust/Advent_of_code/Day_1/calorie_counting/input.txt") {
        let mut counted_calories_vec :Vec<u32> =  Vec::new();
        let mut current_counting_calories: u32 = 0;
        for line in lines{
            if let Ok(number) = line {
                if number !="" {
                    current_counting_calories += number.parse::<u32>().unwrap();
                }
                else {
                    counted_calories_vec.push(current_counting_calories);
                    current_counting_calories = 0; 
                }
            }
        
        }
        println!("{:?}", counted_calories_vec.iter().max());
    }
    match start.elapsed() {
        Ok(elapsed) => {
            // it prints '2'
            println!("{}/1000000μs", elapsed.as_micros());
        }
        Err(e) => {
            // an error occurred!
            println!("Error: {e:?}");
        }
    }
}
