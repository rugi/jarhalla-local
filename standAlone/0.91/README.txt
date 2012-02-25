
Descomprime el .zip, 
El zip contiene 3 jar's

jline-0.9.9.jar		56961
util-1.0-SNAPSHOT.jar	6580
client-1.0.jar		65093

Dentro del directorio que contiene esos 3 jars; ejecuta lo siguiente:

Para version CLI
	java -cp "client-1.0.jar:jline-0.9.9.jar:util-1.0-SNAPSHOT.jar"   org.xhubacubi.jarhalla.App -cli

Para version SWING
	java -cp "client-1.0.jar:jline-0.9.9.jar:util-1.0-SNAPSHOT.jar"   org.xhubacubi.jarhalla.App  

Cualquier duda o comentario:
	https://github.com/rugi/jarhalla-local