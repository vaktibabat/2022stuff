use std::io::{stdin};

fn main()
{
    let mut pass = String::new();

    stdin().read_line(&mut pass);

    if pass.trim() == "p@ssw0rd"
    {
        println!("Correct!");
    }
    else
    {
        println!("Incorrect...");
    }
}
