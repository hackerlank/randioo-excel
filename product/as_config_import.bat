set code_target_dir=..\as
set assets_zip_target_dir=..\as\resources
set assets_target_dir=..\as\resources

mkdir %code_target_dir%
mkdir %assets_target_dir%
mkdir %assets_zip_target_dir%

set source_dir=.\out

copy /y %source_dir%\*.as %code_target_dir%
copy /y %source_dir%\*.zip %assets_zip_target_dir%