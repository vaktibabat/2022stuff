#include <iostream>
#include <cstring>

int main(int argc, char **argv)
{
    char *pass;

    std::cout << "Enter password: ";

    std::cin >> pass;

    if (!strcmp(pass, "p@ssw0rd"))
    {
        std::cout << "Correct!\n";
    }
    else
    {
        std::cout << "Incorrect...\n";
    }

    return 0;
}
