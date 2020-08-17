for /f "tokens=5" %%a in ('netstat -aon ^| find ":8080" ^| find "LISTENING"') do taskkill /f /pid %%a ;
for /f "tokens=5" %%a in ('netstat -aon ^| find ":3306" ^| find "LISTENING"') do taskkill /f /pid %%a ;
for /f "tokens=5" %%a in ('netstat -aon ^| find ":3300" ^| find "LISTENING"') do taskkill /f /pid %%a ;
for /f "tokens=5" %%a in ('netstat -aon ^| find ":4200" ^| find "LISTENING"') do taskkill /f /pid %%a ;
for /f "tokens=5" %%a in ('netstat -aon ^| find ":3600" ^| find "LISTENING"') do taskkill /f /pid %%a ;
exit;