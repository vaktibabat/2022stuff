#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define CHUNK_SIZE 50

const char *pass = "ada";

char *ascanf()
{
    char *res = NULL;
    char tmp[CHUNK_SIZE];

    size_t len;
    size_t tmpLen;

    do
    {
        fgets(tmp, CHUNK_SIZE, stdin);
        tmpLen = strlen(tmp);
        len += tmpLen;

        res = (char *)realloc(res, len + tmpLen + 1);

        strcat(res, tmp);
    } while (tmpLen == CHUNK_SIZE - 1 && tmp[CHUNK_SIZE - 2] != '\n');
    
    return res;
}


int main(int argc, char **argv)
{
    printf("Enter the correct password: ");
    
    if (strstr(ascanf(), pass))
    {
        printf("Correct!\n");
    }
    else
    {
        printf("Wrong...\n");
        
    }

    return 0;
}   
