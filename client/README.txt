Paso 1.
mvn clean install
Paso 2. 
Te pedira una dependencia que se encuentra en:

https://github.com/rugi/alicante

Para Ejecutar.
MODO GRAFICO:
             mvn exec:java -Dexec.mainClass="org.xhubacubi.jarhalla.App"
MODO CLI:
             mvn exec:java -Dexec.mainClass="org.xhubacubi.jarhalla.App" -Dexec.args="-cli"

