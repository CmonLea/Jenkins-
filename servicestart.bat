start cmd /c  java -jar selenium-server-standalone-2.48.2.jar -role hub http://127.0.0.1:4444/grid/console
start cmd /c appium -a 127.0.0.1 -p 4723  --bootstrap-port 4730 -U 7N2RDQ1496009372 --nodeconfig  %~dp0jsonFiles\nodeconfig_1.json
start cmd /c appium -a 127.0.0.1 -p 4725 --bootstrap-port 4732 -U Coolpad8750-0xa36065 --nodeconfig  %~dp0jsonFiles\nodeconfig_2.json