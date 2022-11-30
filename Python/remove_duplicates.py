#For file operations (hash, deletion, folder)
import os
import hashlib

def md5_hash(filename):
    """Returns the MD5 hash of a given file"""
    
    h = hashlib.md5()

    #Calculate MD5 hash of file
    with open(filename, "rb") as file:
        chunk = 0

        while chunk != b'':
            chunk = file.read(2 ** 10)
            h.update(chunk)
        
        return h.hexdigest()

def main():
    print("---Remove Duplicates Ver 0.1---")
    print("Removes duplicate files based on their hashes")
    print("(*) Getting current directory...")
    
    curr_dir = os.getcwd()

    print("(+) Current directory found: {}".format(curr_dir))

    hash_list = []

    #Iterate over all files in directory
    for filename in os.listdir(curr_dir):
        f = os.path.join(curr_dir, filename)
        f_hash = md5_hash(f)

        #If the file's hash hasn't been encountered already, add it to the list
        if f_hash not in hash_list:
            hash_list.append(f_hash)
        #Otherwise, delete the file
        else:
            print("(+) Found duplicate with name {}".format(f))
            
            os.remove(f)

            print("(+) File {} removed.".format(f))

if __name__ == "__main__":
    main()
