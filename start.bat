.\jre7\bin\java -Dfile.encoding=utf-8 -jar .\randioo-excel-0.0.1-SNAPSHOT.jar

echo off
set source_dir=out
for %%i in (%source_dir%\*.rand) do (
	echo on
	echo %%i
	echo off
	7za a assets.zip .\%source_dir%\%%~ni.rand
)