use std::str::FromStr;
use std::env;

fn gcd(mut n: u64, mut m: u64) -> u64 {
    assert!(n != 0 && m != 0);

    while m != 0 {
        if m < n {
            let t = m;
            m = n;
            n = t;
        }
        
        m = m % n;
    }

    n
}

#[test]
fn test_gcd() {
    assert_eq!(gcd(14, 15), 1);

    assert_eq!(gcd(2 * 3 * 5 * 11 * 17, 3 * 7 * 11 * 13 * 19), 3 * 11);
}

fn eulers_phi(n: u64) -> u64 {
    let mut cnt: u64 = 1;

    for curr in 1..n-1 {
        if gcd(n, curr) == 1 {
            cnt = cnt + 1;
        }
    }

    cnt
}

fn main() {
    let mut numbers = Vec::new();

    for arg in env::args().skip(1) {
        numbers.push(u64::from_str(&arg)
                     .expect("Error parsing argument"));
    }

    if numbers.len() == 0 {
        eprintln!("Usage: phi NUMBER ...");
        std::process::exit(1);
    }

    let mut d: u64;

    for m in &numbers[0..] {
        d = eulers_phi(*m);
        println!("Euler's phi of {}  is {}", m, d);
    }
}
