#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <netinet/in.h>

#define BWAH_MAGIC "BWAH"

/*
 * BWAH protocol
 * Blue Wasp Aardvark Hornet
 *
 * Protocol as follows:
 *
 * BWAH
 * port: <port here>
 * ip: <port here>
 * name: <your name here>
 * message: <your message here>
 */

typedef struct _bwahRequest
{
    char *magic = BWAH_MAGIC;
    int port;
    char *ip;
    char *name;
    char *message;
} bwahRequest;

char *stringGets(char *buf, int len, char **s)
{
    char *next = *s;

    int numread = 0;

    while (numread + 1 < len && *next)
    {
        bool isNewLine = (*next == '\n');

        *buf++ = *next++;

        numread++;

        if (isNewLine) break;
    }

    if (numread == 0) return NULL;

    *buf = '\0';

    *s = *next;

    return buf;
}

void striCpy(char *s, char *o, int i)
{
    while (s[i] != '\0')
    {
        o[i] = s[i];
        ++i;
    }
}


int getPort(char *portLine)
{
    char portBuf[strlen(portLine - 5)];

    striCpy(portline, portBuf, 6);

    int port = atoi(portBuf);

    return port;
}

bwahRequest parseRequest(char *s)
{
    bwahRequest ret = (bwahRequest)malloc(strlen(s) + 1);

    char buf[strlen(s)];

    while ((stringGets(buf, strlen(s) - 1, &s)) != NULL)
    {
        if (!strstr(buf, BWAH_MAGIC)) continue;
        if (!strstr(buf, "port: "))
        {
            int portVal = getPort(buf);
            ret.port = portVal;
        }

        if (!strstr(buf, "ip: "))
        {
            char *ipStr = getIp(buf);
            ret.ip = Str;
        }

