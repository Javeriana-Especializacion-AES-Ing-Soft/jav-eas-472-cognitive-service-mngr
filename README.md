# jav-eas-472-cognitive-service-mngr

### Proceso de instalación

#### Requisitos.

* MySql instalado y configurado. [En caso de uso local]
* Restaurar ddl y dml de link.
* Instalación y configuración de AWS CLI. Solicitar secrets y access key por IAM a su administrador.
* Instalación de docker. [En caso de trabajar con imagenes, de lo contrario iniciar JavEas472CognitiveServiceMngrApplication]
 
#### Instalación.

* Descargue el repositorio `git clone https://github.com/Javeriana-Especializacion-AES-Ing-Soft/jav-eas-472-cognitive-service-mngr.git`
* Reemplace en el archivo .properties `COGNITIVE_DATABASE_ENDPOINT`, `COGNITIVE_DATABASE_PORT`, `COGNITIVE_DATABASE_SCHEMA`, `COGNITIVE_DATABASE_USER`, `COGNITIVE_DATABASE_PASSWORD` con las credenciales correspondientes a la conexión a MySql.
* Ejecute `mvn clean install` para descargar dependencias y compilar el proyecto [Genera el .jar para poder hacer uso del dockerfile].
* Si usa docker, ejecute:
    * `docker build -t cognitive-mngr:0.1 .` Para generar la imagen.
    * `docker run -ti --name cognitive-mngr_01 -p 9095:9095 cognitive-mngr:0.1` Para ejecutar la imagen en el contenedor.
* Si no usa docker, simplemente `Run` sobre JavEas472CognitiveServiceMngrApplication.class

### Recurso:

Para poder realizar el consumo del recurso que premite extraer por servicio cognitivo la información de una imagen, tenga en cuenta lo siguiente:

**PATH BASE:** /V1/Utilities/documents
* V1 -> Version Uno.
* Utilities -> Api de Utilidad
* Dominio -> documentos (OCR)

<table>
    <tr>
        <td>PATH</td>
        <td>DESCRIPCIÓN</td>
        <td>TIPO</td>
        <td>VERBO</td>
        <td>HTTP CODE OK</td>
        <td>HTTP CODES FAILED</td>
    </tr>
    <tr>
        <td>/read</td>
        <td>Recibe una imagen en un arreglo de bytes, la almacena en un bucket de S3 y la procesa por textract para obtener el contenido de la misma.</td>
        <td>SINCRONA</td>
        <td>POST</td>
        <td>200 - OK -</td>
        <td>406 - NOT_ACCEPTABLE - Extensión invalida <br>
            409 - CONFLICT - El arreglo de bytes entrante no puede ser transformado en `ByteArrayInputStream` <br>
            500 - INTERNAL_SERVER_ERROR - Error interno (Servicios AWS o almacenamiento en base de datos)</td>
    </tr>
    <tr>
            <td>/{document-uuid}</td>
            <td>Descarga la imagen en un arreglo de bytes.</td>
            <td>SINCRONA</td>
            <td>GET</td>
            <td>200 - OK -</td>
            <td>404 - NOT_FOUND - Id invalido, no existe el documento <br>
                409 - CONFLICT - El arreglo de bytes entrante no puede ser transformado en `ByteArrayInputStream` <br>
                500 - INTERNAL_SERVER_ERROR - Error interno (Servicios AWS o almacenamiento en base de datos)</td>
        </tr>
</table>
