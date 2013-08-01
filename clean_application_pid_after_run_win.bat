@echo off
echo ****************************
echo Go to your browser for http://localhost:9000 to run the program.
echo ****************************
rmdir rop\tmp /s /q
del rop\server.pid /q
play-1.2.4\play stop rop
