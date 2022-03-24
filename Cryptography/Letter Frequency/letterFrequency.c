#include <stdio.h>

#define ALPHABET_SIZE 26

void letterFrequency(char *filename)
{
    FILE *fp = fopen(filename, "r");
    int freq[ALPHABET_SIZE] = {0};
    
    if (fp == NULL) return;

    char c;
    
    do
    {
        c = getc(fp);
        
        if ('A' <= c && c <= 'Z') freq[c - 'A']++;
        else if ('a' <= c && c <= 'z') freq[c - 'a']++;
    } while (!feof(fp));

    for (int i = 0; i < ALPHABET_SIZE; ++i)
    {
        printf("Frequency of %c is %d\n", 'a' + i, freq[i]);
    }
}

int main(int argc, char **argv)
{
    letterFrequency(argv[1]);

    return 0;
}    
