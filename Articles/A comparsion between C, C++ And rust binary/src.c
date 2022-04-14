#include <stdio.h>
#include <string.h>

int main(int argc, char **argv)
{
    char buf[100];
    
    printf("Enter password: ");

    scanf("%99s", &buf);

    if (!strcmp(buf, "p@ssw0rd"))
    {
        printf("Correct!\n");
    }
    else
    {
        printf("Incorrect...\n");
    }

    return 0;
}
