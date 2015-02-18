cd "C:\dev\GitHub\french_ventures_security"
Call mvn clean package



REM forfiles -p "C:\dev\apache-tomcat-8.0.18\webapps" -m *.war -c "cmd /c del @PATH"
cd C:\dev\GitHub\french_ventures_security
for /R %%x in (*.war) do copy "%%x" C:\dev\apache-tomcat-8.0.18\webapps

cd "C:\dev\apache-tomcat-8.0.18\webapps"
REN french_ventures_secure-0.1.0.BUILD-SNAPSHOT.war french_ventures_secure.war