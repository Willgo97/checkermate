@echo off
:CheckOS
IF EXIST "%PROGRAMFILES(X86)%" (GOTO 64BIT) ELSE (GOTO 32BIT)

:64BIT
echo # Installing CheckerMate...


echo #
echo # For this program to install and work Java is needed. 
echo # If the registry keys are not found please install Java from www.java.com.

REM ##########################  Check Java: ###########################

FOR /F "skip=2 tokens=2*" %%A IN ('REG QUERY "HKLM\Software\JavaSoft\Java Runtime Environment" /v CurrentVersion') DO set CurVer=%%B
FOR /F "skip=2 tokens=2*" %%A IN ('REG QUERY "HKLM\Software\JavaSoft\Java Runtime Environment\%CurVer%" /v JavaHome') DO set JAVA_HOME=%%B
IF [%JAVA_HOME] == [] GOTO NOJAVA

echo # 
echo # Java Version:	 %CurVer%
echo # Java Path:	 %JAVA_HOME%


echo #
echo #

  
REM  ##########################  Copy files: ###########################

echo # Copying files...
if not exist "%PROGRAMFILES%\CheckerMate" mkdir "%PROGRAMFILES%\CheckerMate"
copy "%~dp0files\CheckerMate.jar" "%PROGRAMFILES%\CheckerMate\CheckerMate.jar"
copy "%~dp0files\logo.ico" "%PROGRAMFILES%\CheckerMate\logo.ico"

copy "%~dp0files\jna-4.0.0.jar" "%JAVA_HOME%\lib\ext\jna-4.0.0.jar"
copy "%~dp0files\project56.jar" "%JAVA_HOME%\lib\ext\project56.jar"
copy "%~dp0files\purejavacomm.jar" "%JAVA_HOME%\lib\ext\purejavacomm.jar"
echo #
echo #


REM ##########################  Create shortcut: ###########################


set SCRIPT="%TEMP%\%RANDOM%-%RANDOM%-%RANDOM%-%RANDOM%.vbs"
echo # Creating shortcut...
echo Set oWS = WScript.CreateObject("WScript.Shell") >> %SCRIPT%
echo sLinkFile = "%USERPROFILE%\Desktop\CheckerMate.lnk" >> %SCRIPT%
echo Set oLink = oWS.CreateShortcut(sLinkFile) >> %SCRIPT%
echo oLink.TargetPath = "%PROGRAMFILES%\CheckerMate\CheckerMate.jar" >> %SCRIPT%
REM echo oLink.IconLocation = "%PROGRAMFILES%\CheckerMate\logo.ico" >> %SCRIPT%
echo oLink.Save >> %SCRIPT%
cscript /nologo %SCRIPT%
echo %SCRIPT%
del %SCRIPT%


echo # Done installing CheckerMate...
GOTO END


:32BIT
echo # The system is not running 64-bit windows. Please use the correct installer...
GOTO END


:END
pause > nul