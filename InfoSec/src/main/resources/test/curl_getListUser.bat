chcp 65001 >nul
set "token_file=C:\Users\veyde\OneDrive - ITMO UNIVERSITY\Работы ИТМО\InformationSecurity_Lab1\InfoSec\src\main\resources\test\input_data.txt"
set "output_file=C:\Users\veyde\OneDrive - ITMO UNIVERSITY\Работы ИТМО\InformationSecurity_Lab1\InfoSec\src\main\resources\test\out.txt"

echo.> "%output_file%"

set counter=0
for /f "usebackq delims=" %%t in ("%token_file%") do (
    curl -X GET http://localhost:3434/api/data -H "Authorization: Bearer %%t" >> "%output_file%"
    echo.>> "%output_file%"
)

timeout 100
exit