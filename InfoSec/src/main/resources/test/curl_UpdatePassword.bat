chcp 65001 >nul
set "output_file=C:\Users\veyde\OneDrive - ITMO UNIVERSITY\Работы ИТМО\InformationSecurity_Lab1\InfoSec\src\main\resources\test\out_updatePassword.txt"
echo.> "%output_file%"

curl -X PUT http://localhost:3434/api/data/updatePassword -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNzU4MDA5NTU4LCJleHAiOjE3NTgxNTM1NTh9.DGX26YgaHgwNLTXEhtAZtGmdepzj4bMSA5eZ6Kwyp3A" -H "Content-Type: application/json" -d "{\"password\":\"3\"}" >> "%output_file%"
curl -X POST http://localhost:3434/api/user/login -H "Content-Type: application/json" -d "{\"username\":\"2\",\"password\":\"2\"}">> "%output_file%"
echo.>>"%output_file%"
curl -X PUT http://localhost:3434/api/data/updatePassword -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNzU4MDA5NTU4LCJleHAiOjE3NTgxNTM1NTh9.DGX26YgaHgwNLTXEhtAZtGmdepzj4bMSA5eZ6Kwyp3A" -H "Content-Type: application/json" -d "{\"password\":\"3\"}" >> "%output_file%"
curl -X POST http://localhost:3434/api/user/login -H "Content-Type: application/json" -d "{\"username\":\"2\",\"password\":\"3\"}">> "%output_file%"
echo.>>"%output_file%"
curl -X PUT http://localhost:3434/api/data/updatePassword -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzIiwiaWF0IjoxNzU4MDA5NTU5LCJleHAiOjE3NTgxNTM1NTl9.HKWAswnhv4unB1BW8_QHKsFzgv97tAvCQU4lh-Ncq_Q" -H "Content-Type: application/json" -d "{\"password\":\"4\"}" >> "%output_file%"
curl -X POST http://localhost:3434/api/user/login -H "Content-Type: application/json" -d "{\"username\":\"3\",\"password\":\"2\"}">> "%output_file%"

timeout 10
exit