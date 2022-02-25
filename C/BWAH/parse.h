#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <stdbool.h>
#include <arpa/inet.h>

#define BWAH_MAGIC "BWAH"

typedef struct _bwahRequest
{
    char *magic;
    int port;
    char *ip;
    char *name;
    char *message;
} bwahRequest;

bool checkIfBwah(FILE *fp)
{
    char buf[6];

    fgets(buf, 5, fp);

    return strstr(buf, BWAH_MAGIC);
}

//(checks ipv4)
bool checkIfValidIP(char *s)
{
    struct sockaddr_in sa;
    int res = inet_pton(AF_INET, s, &(sa.sin_addr));

    return !(res > 0);
}

bool checkIfValidPort(int p)
{
    return p > 0 && p < 65536;
}

bwahRequest parseFile(char *filename)
{
    FILE *fp = fopen(filename, "r");

    if (fp == NULL)
    {
        printf("Invalid file: %s. Exiting with status 1\n", filename);
        exit(1);
    }

//    if (!checkIfBwah(fp))
  //  {
   //     printf("File %s in not a BWAH file. Exiting with status 2\n", filename);
    //    fclose(fp);
     //   exit(2);
    //}

    bwahRequest b;
    b.magic = BWAH_MAGIC;

    int port = -1;

    char buf[7];

    fseek(fp, 5, SEEK_SET);

    fgets(buf, 6, fp);

    fseek(fp, SEEK_SET, SEEK_CUR);

    port = atoi(buf);

 //   if (!checkIfValidPort(port))
   // {
    //    printf("Port %d is not a valid port. Exiting with status 3\n");
     //   exit(3);
    //}

    b.port = port;

    char ipBuf[16];//15

    fseek(fp, 11, SEEK_SET);

    fgets(ipBuf, 15, fp);

    //if (!checkIfValidIP(ipBuf))
    //{
     //   printf("IP %s is not a valid IPv4 address. Exiting with status 4\n", ipBuf);
      //  exit(4);
    //}

    b.ip = ipBuf;

    char nameBuf[100];

    fseek(fp, 2 * strlen(ipBuf) + 1, SEEK_SET);

    fgets(nameBuf, 99, fp);

    b.name = nameBuf;

    char messageBuf[1024] = "";

    fseek(fp, SEEK_SET, SEEK_SET);

    char *line = NULL;

    int cnt = 0;
    size_t len = 0;

    while ((getline(&line, &len, fp)) != -1)
    {
        if (cnt >= 4 && strlen(messageBuf) + strlen(line) < 1024)
        {
            strcat(messageBuf, line);
        }

        cnt++;
    }

    b.message = messageBuf;

    return b;
}
