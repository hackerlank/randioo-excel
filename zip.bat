echo off
set input=%1%
set output=%2%
set prefix=rand

for %%i in (%input%/*.%prefix%) do (
7za a %output%/assets.zip %input%/%%~ni.%prefix%
)

exit