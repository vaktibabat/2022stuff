use std::fs::File;
use std::io::{self, BufRead};
use std::path::Path;
use error_chain::error_chain;
use text_colorizer::*;

error_chain! {
    foreign_links {
        Io(std::io::Error);
        HttpRequest(reqwest::Error);
    }
}

#[derive(Debug)]
struct Arguments {
    wordlist: String,
    website: String,
}

fn bruteforce(wordlist: &str, website: &str) -> std::io::Result<()> {
    if let Ok(lines) = read_lines(wordlist) {
        for line in lines {
            if let Ok(curr_path) = line {
                let curr_url = format!("https://{}/{}", website, curr_path);
                let res = reqwest::blocking::get(&curr_url);
        
                match res {
                    Ok(o) => {
                        if o.status() != 404 {
                            println!("[*] Found path: {}", curr_path);
                        }
                    }

                    Err(e) => {
                        println!("An error occurred: {}", e);
                    }
                }
                }
            }
        }
    
    Ok(())
}

fn usage() {
    eprintln!("{} - Bruteforce dirs/files on website", "rustbuster".green());
    eprintln!("Usage: rustbuster <wordlist> <website (Like this: if website is http://example.com, enter example.com)>");
}

fn parse_arguments() -> Arguments {
    let args: Vec<String> = std::env::args().skip(1).collect();

    if args.len() != 2 {
        usage();
        eprintln!("{} Wrong number of arguments: expected {} got {}", "Error: ".red().bold(), "2".blue(), format!("{}", args.len()).blue());
        std::process::exit(1);
    }

    Arguments {
        wordlist: args[0].clone(),
        website: args[1].clone(),
    }
}

fn main() -> std::io::Result<()> {
    let args = parse_arguments();

    bruteforce(&args.wordlist, &args.website).expect("An error occurred while bruteforcing.");

    Ok(())
} 

fn read_lines<P>(filename: P) -> io::Result<io::Lines<io::BufReader<File>>>
where P: AsRef<Path>, {
    let file = File::open(filename)?;
    Ok(io::BufReader::new(file).lines())
} 
