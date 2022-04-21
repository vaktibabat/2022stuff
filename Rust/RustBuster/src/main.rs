use std::fs::File;
use std::io::{self, BufRead};
use std::path::Path;
use error_chain::error_chain;
use text_colorizer::*;
use clap::{Arg, App};
use dns_lookup::lookup_host;

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

fn subdomain_bruteforce(wordlist: &str, website: &str) -> std::io::Result<()> {
    if let Ok(lines) = read_lines(wordlist) {
        for line in lines {
            if let Ok(curr_subdomain) = line {
                let curr_host = format!("{}.{}", curr_subdomain, website);
                let res = lookup_host(&curr_host);

                match res {
                    Ok(o) => {
                        println!("[*] Found subdomain: {}", curr_host);
                    }
                    Err(_) => {
                        print!("");
                    }
                }
            }
        }
    }

    Ok(())
}

fn main() -> std::io::Result<()> {
    let matches = App::new("RustBuster")
        .version("0.2")
        .author("sag0li")
        .about("Bruteforces files/dirs and subdomains of given website")
        .arg(Arg::with_name("wordlist")
             .short('w')
             .long("wordlist")
             .takes_value(true)
             .help("The wordlist you want to use for bruteforcing"))
        .arg(Arg::with_name("website")
             .short('s')
             .long("website")
             .takes_value(true)
             .help("The website you want to bruteforce"))
        .arg(Arg::with_name("subdomains")
             .short('b')
             .long("brute-subdomains")
             .takes_value(true)
             .help("Run with -b=y if you want to bruteforce subdomains"))
        .get_matches();

    let wordlist = matches.value_of("wordlist").unwrap_or("wordlist.txt");
    let website = matches.value_of("website").unwrap_or("");
    let brute_subdomains = matches.value_of("subdomains");

    if brute_subdomains != Some("y") {
        bruteforce(wordlist, website).expect("An error occurred while bruteforcing.");
    }
    else {
        subdomain_bruteforce(wordlist, website).expect("An error occurred while subdomain bruteforcing.");
    }

    Ok(())
} 

fn read_lines<P>(filename: P) -> io::Result<io::Lines<io::BufReader<File>>>
where P: AsRef<Path>, {
    let file = File::open(filename)?;
    Ok(io::BufReader::new(file).lines())
} 
