// use crate::days::day1::literal_numbers::{LiteralNumbers, Options};
const STRING_NUMBERS: [&str; 10] = ["zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"];

pub fn day_one(lines: Vec<String>) -> (u16, u16) {
    let mut score1:u16 = 0;
    let mut score2:u16 = 0;


    for line in lines{
        let mut first_left_number_p1:i8 = -1;
        let mut first_right_number_p1:i8 = -1;
        let mut first_left_number_p2 :i8 = -1;
        let mut first_right_number_p2 :i8 = -1;

        let chars = line.chars();
        let mut current_string = String::new();

        for char in chars{
            if !char.is_ascii_digit(){
                current_string.push(char);
            }

            if char.is_ascii_digit() && first_left_number_p1 == -1{
                first_left_number_p1 = char.to_digit(10).unwrap() as i8;
            }
            if char.is_ascii_digit() {
                first_right_number_p1 = char.to_digit(10).unwrap() as i8;
                first_right_number_p2 = char.to_digit(10).unwrap() as i8;
            }

            if char.is_ascii_digit() && first_left_number_p2 == -1{
                first_left_number_p2 = char.to_digit(10).unwrap() as i8;
            }

            if !check_substring(current_string.as_str()){
                current_string.clear();
            }

            let number = check_complete(current_string.as_str());

            if number != -1{
                first_right_number_p2 = number;
                if first_left_number_p2 == -1 {
                    first_left_number_p2 = number;
                }
                let last_char = current_string.chars().last().unwrap();
                current_string.clear();
                current_string.push(last_char);

                if !check_substring(current_string.as_str()){
                    current_string.clear();
                }
            }
        }
        convert_to_zero(&mut first_left_number_p1);
        convert_to_zero(&mut first_right_number_p1);
        convert_to_zero(&mut first_left_number_p2);
        convert_to_zero(&mut first_right_number_p2);
        score1 += first_left_number_p1 as u16 * 10 + first_right_number_p1 as u16;
        score2 += first_left_number_p2 as u16 * 10 + first_right_number_p2 as u16;
        println!("Line: {} Number: {}", line, first_left_number_p2 as u16 * 10 + first_right_number_p2 as u16)
    }

    (score1, score2)
}

// checks if character is one of starting letters of numbers from 0 to 9 eg. 0 -> (z)ero is z

fn check_substring(sub: &str) -> bool {
    for number in STRING_NUMBERS{
        println!("Number {}  Sub {}",number, sub);
        if number.starts_with(sub){
            return true
        }
    }
    false
}

fn check_complete(content: &str) -> i8 {
    for (i, number) in STRING_NUMBERS.iter().enumerate(){
        if *number == content{
            return i as i8
        }
    }
    -1
}

fn convert_to_zero(number: &mut i8){
    if *number == -1{
        *number = 0
    }
}

//Left for some use in the future
// trait CharExternal{
//     fn matches_first_number_letter(&self) -> bool;
// }
//
// impl CharExternal for char{
//     fn matches_first_number_letter(&self) -> bool {
//         let mut number = LiteralNumbers::Zero;
//         while !number.is_null(){
//             if number.get_first_letter() == *self{
//                 return true
//             }
//             // println!("{:?}", number);
//             number = number.next().unwrap()
//         }
//         false
//     }
// }

