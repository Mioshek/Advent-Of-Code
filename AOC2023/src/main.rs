pub mod days;

use std::io;
use std::io::{BufRead, Write};
use std::time::{SystemTime};
use days::*;

pub fn content_to_lines(content: String) -> Vec<String> {
    let mut lines = Vec::new();
    for line in content.lines() {
        lines.push(line.to_string())
    }
    lines
}

fn read_content(filename: String) -> String {
    let path = format!("src/inputs/{filename}");
    std::fs::read_to_string(path).unwrap()
}

fn main() {
    let mut user_input = String::new();
    println!("Enter day number to start!");
    io::stdin().read_line(&mut user_input).expect("Failed to read line");
    let input = content_to_lines(read_content(format!("Day{}.txt", user_input.trim())));

    let start = SystemTime::now();

    match user_input.trim() {
        "1" => {
            println!("Score: {:?}", day1::day1launcher::day_one(input))
        }
        _ => {
            println!("else")
        }
    }

    match start.elapsed() {
        Ok(elapsed) => {
            println!("{}/1000000μs", elapsed.as_micros());
        }
        Err(e) => {
            println!("Error: {e:?}");
        }
    }
}
