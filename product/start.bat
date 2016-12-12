set CONFIG_URL=./xml/config.xml
set JAVA_TEMPLATE_URL=./template/templateJava.txt
set AS_TEMPLATE_URL=./template/templateAS.txt
set EXCEL_URL=./excelFile
set DATA_URL=./out
set PO_VAR_NAME=config
set BYTES_VAR_NAME=data

rem make java and as codes, tbl files.
.\jre7\bin\java -Dfile.encoding=utf-8 -jar .\randioo-excel-0.0.1-SNAPSHOT.jar config_url=%CONFIG_URL% java_template_url=%JAVA_TEMPLATE_URL% as_template_url=%AS_TEMPLATE_URL% excel_url=%EXCEL_URL% data_url=%DATA_URL% po_var_name=%PO_VAR_NAME% bytes_var_name=%BYTES_VAR_NAME%

set ZIP_SOURCE_DIR=.\out
set ZIP_OUT_DIR=.\out
set ZIP_FILE_PREFIX=tbl

echo off
rem pack tbl files.
./zip.bat %ZIP_SOURCE_DIR% %ZIP_OUT_DIR% %ZIP_FILE_PREFIX%