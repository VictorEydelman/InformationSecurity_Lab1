chcp 65001 >nul
set "output_file=C:\Users\veyde\OneDrive - ITMO UNIVERSITY\Работы ИТМО\InformationSecurity_Lab1\InfoSec\src\main\resources\test\out.txt"
curl -X POST http://localhost:3434/api/user/login -H "Content-Type: application/json" -d "{\"username\":\"2\",\"password\":\"2\"}"> "%output_file%"
echo.>>"%output_file%"
curl -X POST http://localhost:3434/api/user/login -H "Content-Type: application/json" -d "{\"username\":\"2\",\"password\":\"2\"}">> "%output_file%"
echo.>>"%output_file%"
curl -X POST http://localhost:3434/api/user/login -H "Content-Type: application/json" -d "{\"username\":\"2\",\"password\":\"3\"}">> "%output_file%"
echo.>>"%output_file%"
curl -X POST http://localhost:3434/api/user/login -H "Content-Type: application/json" -d "{\"username\":\"3\",\"password\":\"3\"}">> "%output_file%"
echo.>>"%output_file%"
curl -X POST http://localhost:3434/api/user/login -H "Content-Type: application/json" -d "{\"username\":\"3\",\"password\":\"2\"}">> "%output_file%"

timeout 10
exit