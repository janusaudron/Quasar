# Quasar

Es una aplicacion que consiste en dar la posicion y mensaje enviado por una nave de acuerdo a la información recibida por 3 satelites.

Consta de 3 operaciones rest:

## Top Secret
#### POST
http://150.136.53.84:9090/QuasarOperation/rest/topsecret

Calcula la posición y mensaje recibido en los 3 satelites

## Top Secret Split
#### POST
http://150.136.53.84:9090/QuasarOperation/rest/topsecret_split/{server_name}

Almancena en memoria la distancia y el mensaje para un satelite

## Get Top Secret Split
#### GET
http://150.136.53.84:9090/QuasarOperation/rest/topsecret_split

Retorna la posición y mensaje recibido por los 3 satelites


*Las operaciones retornan 404 en caso de error o no poder realizar el calculo de la posición o el mensaje.*

## Instalación

El proyecto esta construido con maven por lo que para generar el archivo war solo es necesario realizar la instalación con esta herramienta.




