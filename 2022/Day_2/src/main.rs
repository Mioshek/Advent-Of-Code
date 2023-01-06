use std::env;
use std::fs::File;
use std::io::{BufRead, BufReader};
use std::time::{SystemTime};

use self::Hand::*;
use self::Result::*;

#[derive(PartialEq)]
enum Result {
    Win,
    Lose,
    Draw,
}

#[derive(Copy, Clone, PartialEq)]
enum Hand {
    Rock,
    Paper,
    Scissors,
}
trait Move {
    fn get_winning(&self) -> Self;
}

impl Move for Hand {
    fn get_winning(&self) -> Self {
        match *self{
            Rock => Scissors,
            Paper => Rock,
            Scissors => Paper,
        }
    }
}

fn play(own_hand: Hand, other_hand: Hand) -> Result {
    let (own_beats, other_beats) = (own_hand.get_winning(), other_hand.get_winning());

    match (own_beats, other_beats) {
        _ if own_beats == other_hand => Win,
        _ if other_beats == own_hand => Lose,
        _                            => Draw,
    }
}

fn read_lines(filename: &str) -> File {
    let path = env::current_dir().unwrap();
    let parent_path = path.parent().unwrap().join("inputs").join(filename);
    let file = File::open(parent_path).expect("Error");
    return file;
}

fn return_hand(mov:char) -> Hand{
    match mov {
        'A' => Rock,
        'X' => Rock,
        'B' => Paper,
        'Y' => Paper,
        'C' => Scissors,
        'Z' => Scissors,
        _ => Paper,
    }
}

fn main() {
    let start = SystemTime::now();
    let lines = BufReader::new(read_lines("Day2.txt")).lines();
    let mut score : u16 = 0;

    for line in lines{
        let input: Vec<char> = line.unwrap().chars().collect();
        let elf = return_hand(input[0]);
        let me = return_hand(input[2]);
        let result = play(me, elf);

        match result {
            Win => score+=6,
            Draw => score += 3,
            _    => score += 0,
        }

        match me {
            Rock    => score+=1,
            Paper   => score+=2,
            _       => score+=3,
        }

    }
    println!("{:?}", score);
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
