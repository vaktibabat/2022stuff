#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <string.h>

void sendToProc(char *data)
{
    int fdin[2];
    int fdout[2];

    pipe(fdin);
    pipe(fdout);
    
    pid_t pid = fork();

    if (pid == -1) exit(1);

    if (!pid)
    {
        dup2(fdin[0], STDIN_FILENO);
        dup2(fdout[1], STDOUT_FILENO);

        close(fdin[1]);
        close(fdout[0]);
        
        char *exec_argv[] = {"./door", 0};

        printf("Execing\n");

        execv(exec_argv[0], exec_argv);
    }
    else
    {
        close(fdin[0]);
        close(fdout[1]);

        write(fdin[1], data, sizeof(data));
        wait(NULL);        

        char buf[256];
    
        read(fdout[0], buf, sizeof(buf));
        
        printf("Got %s\n", buf);

        if (strstr(buf, "Correct!"))
        {
            printf("Found the correct password: %s\n", buf);
        }
    }
}

