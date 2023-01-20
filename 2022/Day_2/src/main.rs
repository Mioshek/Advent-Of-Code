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

#[derive(Copy, Clone, PartialEq, Debug)]
enum Hand {
    Rock,
    Paper,
    Scissors,
}
trait Move {
    fn get_winning(&self) -> Self;
    fn get_loosing(&self) -> Self;
}

impl Move for Hand {
    fn get_winning(&self) -> Self {
        match *self{
            Rock => Scissors,
            Paper => Rock,
            Scissors => Paper,
        }
    }

    fn get_loosing(&self) -> Self {
        match *self{
            Rock => Paper,
            Paper => Scissors,
            Scissors => Rock,
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
    let mut score1 : u16 = 0;
    let mut score2: u16 = 0;

    for line in lines{
        let input: Vec<char> = line.unwrap().chars().collect();
        let elf = return_hand(input[0]);
        let me1 = return_hand(input[2]);
        let result2: char = input[2];
        let result1 = play(me1, elf);
        let me2: Hand;

        match result1 {
            Win => score1+=6,
            Draw => score1 += 3,
            _    => score1 += 0,
        }

        match me1 {
            Rock    => score1+=1,
            Paper   => score1+=2,
            _       => score1+=3,
        }
        
        match result2{
            'X' => me2 = elf.get_winning(), // todo cuz the order is inverted
            'Y' => me2 = elf,
            _ => me2 = elf.get_loosing(),
        }
        match result2{
            'X' => score2 +=0,
            'Y' => score2 +=3,
            _ => score2 +=6,
        }
        match me2{
            Rock    => score2+=1,
            Paper   => score2+=2,
            _       => score2+=3,
        }
    }
    println!("{:?}", score1);
    println!("{:?}", score2);


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
