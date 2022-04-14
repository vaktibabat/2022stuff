use num::Complex;
use std::env;

#[derive(Debug)]
struct Arguments {
    re: String,
    im: String,
}

fn parse_args() -> Arguments {
    let args: Vec<String> = env::args().skip(1).collect();

    if args.len() != 2 {
        println!("Error. exepcted 2 args got {}", args.len());
        std::process::exit(1);
    }

    Arguments {
        re: args[0].clone(),
        im: args[1].clone(),
    }
}

fn complex_abs(n: Complex<f64>) -> f64 {
    ((n.re).powf(2.0) + (n.im).powf(2.0)).sqrt()
}

fn polar_form(n: Complex<f64>) -> String {
    let r = complex_abs(n);
    let theta = libm::atan(n.im / n.re);
    let res = format!("{}(cos {} + i sin {})", r, theta, theta);

    res
}

fn main() {
    let args = parse_args();

    let re_part: f64 = (&args.re).parse().unwrap();
    let im_part: f64 = (&args.im).parse().unwrap();

    if re_part == 0.0 {
        println!("This will cause a division by zero error :(");
    }
    else {
        println!("You entered the complex number: {} + {}i", re_part, im_part);
        println!("Polar form is: {}", polar_form(Complex::<f64>::new(re_part, im_part)));
    }
}
