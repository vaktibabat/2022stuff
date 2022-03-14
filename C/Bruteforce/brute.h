#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "ipc.h"

#define FILENAME "brutelist"

static const char alphabet[] = 
"abcdefghijklmnopqrstuvwxyz"
"ABCDEFGHIJKLMNOPQRSTUVWXYZ"
"0123456789/\\|~";

static const int alphabetSize = sizeof(alphabet) - 1;

void bruteImpl(char *s, int index, int maxDepth, char *filename)
{
    for (int i = 0; i < alphabetSize; ++i)
    {
        s[index] = alphabet[i];
        if (index == maxDepth - 1) sendToProc(s);
        else bruteImpl(s, index + 1, maxDepth, filename);
    }
}

void bruteSeq(int maxLen)
{
    char *buf = (char *)malloc(maxLen + 1);

    for (int i = 1; i <= maxLen; ++i)
    {
        memset(buf, 0, maxLen + 1);
        bruteImpl(buf, 0, i, FILENAME);
    }

    free(buf);
}
