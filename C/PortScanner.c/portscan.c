#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <pthread.h>

#define MAX_PORT 65535

typedef struct _conn
{
    char *ip;
    int port;
} conn;

void usage(char *progName)
{
    printf("Usage: %s <ip>", progName);
}

void *portOpen(void *argVoid)
{
        conn *arg = (conn*)argVoid;
    
        int sock;
        struct sockaddr_in addr;
        
        if ((sock = socket(AF_INET, SOCK_STREAM, 0)) < 0)
        {
            perror("Socket failed\n");
        }

        addr.sin_family = AF_INET;
        addr.sin_port = htons(arg->port);

        if (inet_pton(AF_INET, arg->ip, &addr.sin_addr) <= 0)
        {
            perror("inet_pton Failed\n");
        }
        

        if (connect(sock, (struct sockaddr *)&addr, sizeof(addr)) >= 0)
        {
            return (void *)1;
        }

        close(sock);

        return (void *)0;
}

void portscan(char *ip)
{
    int currPort = 1;

    for (currPort; currPort <= MAX_PORT; ++currPort)
    {
        pthread_t tid;
        void *status;
        
        conn arg;
        arg.port = currPort;
        arg.ip = ip;

        pthread_create(&tid, NULL, portOpen, (void *)&arg);
        pthread_join(tid, &status);

        if ((int *)status == 1)
        {
            printf("[+] Port %d on host %s is open\n", currPort, ip);
        }
    }
}

int main(int argc, char **argv)
{
    if (argc != 2) usage(argv[0]);

    portscan(argv[1]);

    return 0;
}


