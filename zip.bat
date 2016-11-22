set input=%1%
set output=%2%
set prefix=%3%

mkdir %input%
mkdir %output%

rem for %%i in (%input%/*.%prefix%) do (
rem 7za a %output%/assets.zip %input%/%%~ni.%prefix%
rem )

7za a %output%/assets.zip %input%/*.%prefix%

rem exit