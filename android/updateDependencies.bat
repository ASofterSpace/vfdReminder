cd app\src\main\java\com\asofterspace

rd /s /q toolbox

md toolbox
cd toolbox

copy ..\..\..\..\..\..\..\..\..\Toolbox-Java\src\com\asofterspace\toolbox\Utils.java Utils.java
echo d | xcopy /s /i ..\..\..\..\..\..\..\..\..\Toolbox-Java\src\com\asofterspace\toolbox\web web
echo d | xcopy /s /i ..\..\..\..\..\..\..\..\..\Toolbox-Java\src\com\asofterspace\toolbox\notification notification

pause
