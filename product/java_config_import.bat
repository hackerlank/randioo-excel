set code_target_dir=..\java\main
set assets_zip_target_dir=..\java
set assets_target_dir=..\java\resources

mkdir %code_target_dir%
mkdir %assets_target_dir%
mkdir %assets_zip_target_dir%

set source_dir=.\out

copy /y %source_dir%\*.java %code_target_dir%
copy /y %source_dir%\*.tbl %assets_target_dir%