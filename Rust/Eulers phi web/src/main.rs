use actix_web::{web, App, HttpResponse, HttpServer};
use serde::Deserialize;

#[derive(Deserialize)]
struct eulersPhiParameters {
    n: u64,
}

fn main() {
    let server = HttpServer::new(|| {
        App::new()
            .route("/", web::get().to(get_index))
            .route("/eulers_phi", web::post().to(post_phi))
    });

    println!("Serving on http://localhost:3000...");
    
    server
        .bind("127.0.0.1:3000").expect("Error binding server to address. (Check if port is already used)")
        .run().expect("Error running server");
}

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

fn eulers_phi(n: u64) -> u64 {
    let mut cnt: u64 = 1;

    for curr in 1..n-1 {
        if gcd(n, curr) == 1 {
            cnt = cnt + 1;
        }
    }

    cnt
}


fn get_index() -> HttpResponse {
    HttpResponse::Ok()
        .content_type("text/html")
        .body(
            r#"
            <title>Euler's phi function calculator</title>
            <form action="/eulers_phi" method="post">
            <input type="text" name="n"/>
            <Button type="submit">Compute euler's phi</button>
            </form>
            "#,
        )
}

fn post_phi(form: web::Form<eulersPhiParameters>) -> HttpResponse {
    if form.n == 0 {
        return HttpResponse::BadRequest()
            .content_type("text/html")
            .body("Computing euler's phi of 0 is boring.");
    }

    let response =
        format!("eulersPhi({}) = <b>{}</b>\n", form.n, eulers_phi(form.n));

    HttpResponse::Ok()
        .content_type("text/html")
        .body(response)
}
