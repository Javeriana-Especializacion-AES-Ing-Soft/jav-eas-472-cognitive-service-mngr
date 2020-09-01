# jav-eas-472-cognitive-service-mngr

### Proceso de instalación

#### Pre-requisitos.

* MySql instalado y configurado.
* Restaurar ddl y dml de link.
* Instalación y configuración de AWS CLI. Solicitar secrets y access key por IAM a su administrador.
 
#### Instalación.

* Descargue el repositorio `git clone https://github.com/Javeriana-Especializacion-AES-Ing-Soft/jav-eas-472-cognitive-service-mngr.git`
* Cree las variables de entorno `COGNITIVE_DATABASE_ENDPOINT`, `COGNITIVE_DATABASE_PORT`, `COGNITIVE_DATABASE_SCHEMA`, `COGNITIVE_DATABASE_USER`, `COGNITIVE_DATABASE_PASSWORD` con las credenciales correspondientes a la configuración de MySql realizada en pre-requisitos.
* Ejecute `mvn clean install` para descargar dependencias y compilar el proyecto.

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
</table>
