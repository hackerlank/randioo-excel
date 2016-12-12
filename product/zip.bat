set input=%1%
set output=%2%
set prefix=%3%

mkdir %input%
mkdir %output%

7za a %output%/config.cfg %input%/*.%prefix%

rem exit