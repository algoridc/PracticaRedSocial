# PracticaRedSocial

Ejecución:




Para https:
1. Ir a la cmd de windows y ir a la ruta  c\archivos de programa\java\jre(que tengas)\bin
2. Desde esa ruta ejecutamos por terminal el comando(mirar si teneis keytool):
	keytool -genkey -alias tomcat -keyalg RSA -keystore "ruta donde se guardara lo que necesitaremos\tomcat o C\key\keys(nombrequetendra)(esta carpeta debe existir)"
3. Te pediran datos por consola importante poner en tu nombre y apellido : localhost
4. en la contraseña de clave final no en la incial (que la inventamos) la final hay que elegir pulsar intro
5. vamos a disco local a nuestro tomcat en la carpeta conf tenemos un archivo server.xml en el buscamos donde ponga >conector port 8443...blabla, está comentado asique vamos debajo de el y escribimos lo siguiente

<Connector protocol="org.apache.coyote.http11.Http11Procotol"
 port="8443" SSLEnabled="true" maxThreads="200"
sheme="https" secure="true" 
keystoreFile="ruta de donde esta el key"
keystorePass="la pass que pusimos en cmd" clientAuth="false"
sslProtocol="TLS" />
