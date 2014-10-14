Paso 1.
mvn clean install
Paso 2. 
Te pedira una dependencia que se encuentra en:
	https://github.com/rugi/alicante
	[OJO: ES UN PROYECTO APARTE, ASI QUE DEBES DARLE: mvn clean install 
         Con esto ese artefacto se instala y puedes proseguir.]

Para Ejecutar.
MODO GRAFICO:
             mvn exec:java -Dexec.mainClass="org.xhubacubi.jarhalla.App"
MODO CLI:
             mvn exec:java -Dexec.mainClass="org.xhubacubi.jarhalla.App" -Dexec.args="-cli"

