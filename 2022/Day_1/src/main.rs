use std::env;
use std::fs::File;
use std::io::{BufRead, BufReader};
use std::time::{SystemTime};

fn read_lines(filename: &str) -> File {
    let path = env::current_dir().unwrap();
    let parent_path = String::from(path.parent().unwrap().as_os_str().to_str().unwrap()) + "/inputs/" + filename;
    let file = File::open(parent_path).expect("Error");
    return file;
}


fn main() {
    let start = SystemTime::now();
    let mut counted_calories_vec :Vec<u32> =  Vec::new();
    let mut current_counting_calories: u32 = 0;
    let lines = BufReader::new(read_lines("Day1.txt")).lines();
    for line in lines{
        let line_str = line.expect("Err");

        if line_str !="" {
            current_counting_calories += line_str.parse::<u32>().unwrap();
        }
        else {
            counted_calories_vec.push(current_counting_calories);
            current_counting_calories = 0; 
        }
    }
    println!("{:?}", counted_calories_vec.iter().max());
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
