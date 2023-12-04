// #[derive(Debug, PartialEq)]
// pub enum LiteralNumbers {
//     Zero = 0,
//     One = 1,
//     Two = 2,
//     Three = 3,
//     Four = 4,
//     Five = 5,
//     Six = 6,
//     Seven = 7,
//     Eight = 8,
//     Nine = 9
// }
//
// impl Iterator for LiteralNumbers {
//     type Item = LiteralNumbers;
//
//     fn next(&mut self) -> Option<Self::Item> {
//         use LiteralNumbers::*;
//         match self {
//             Zero => Some(One),
//             One => Some(Two),
//             Two => Some(Three),
//             Three => Some(Four),
//             Four => Some(Five),
//             Five => Some(Six),
//             Six => Some(Seven),
//             Seven => Some(Eight),
//             Eight => Some(Nine),
//             Nine => None,
//         }
//     }
// }
//
// pub trait Options {
//     fn get_name(&self) -> String;
//     fn get_first_letter(&self) -> char;
//     fn is_null(&mut self) -> bool;
// }
//
// impl Options for LiteralNumbers {
//     fn get_name(&self) -> String {
//         format!("{:?}", self)
//     }
//
//     fn get_first_letter(&self) -> char{
//         self.get_name().chars().next().unwrap()
//     }
//
//     fn is_null(&mut self) -> bool {
//         self.next().is_none()
//     }
// }