@echo off
echo ****************************
echo Go to your browser for http://localhost:9000 to run the program.
echo Close this window to exit the program and please run 
echo clean_application_pid_after_run_win.bat
echo ****************************
rmdir rop\tmp /s /q
play-1.2.4\play start rop
echo "go to your browser for http://localhost:9000 Press any key to exit."
pause > nul