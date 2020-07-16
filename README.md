# GetJob
<p align="center">
  <img src="https://firebasestorage.googleapis.com/v0/b/getjob-46015.appspot.com/o/logo.png?alt=media&token=bfac590d-1ac2-4207-bf42-9f1731b85652" width="350" title="hover text">
</p>


GetJob es una Aplicación para plataforma Android que facilita la búsqueda de empleos, de igual manera facilita la postulación a diferentes ofertas laborales, facilitando el proceso a las empresas y al usuario, todo ello en un proceso totalmente transparente. 
##### Minimum SDK: API 19: Android 4,4 (KitKat)

## Tipos de usuario que maneja la aplicación
GetJob maneja solamente 2 tipos de usuario:

**Usuario** : Perfil basico de un usuario normal, donde podra realizar:
- [x] Consultar las ofertas laborales de las empresas que las han publicado
- [x] Guardar esa oferta laboral como favorita
- [x] Aplicar para dicha oferta
- [X] Subir su Curriculum vitae en formato **PDF**
- [X] Descargar su Curriculum vitae en formato **PDF**
- [x] Actualizar sus datos personales: Nombre completo, ciudad de nacimiento, breve descripcion de su persona, foto de perfil. **Estos datos se mostrarán en la proxima vez que inicie sesion**

**Empresa** : Perfil de una empresa, donde podra realiza:
- [x] Publicar ofertas laborales
- [x] Editar la oferta laboral
- [x] Eliminar la oferta laboral
- [X] Ver las solicitudes de usuarios que desean aplicar a dicha oferta.
- [x] Actualizar sus datos personales: Nombre de la empresa, ciudad de locación, breve descripcion de la empresa, foto de perfil. **Estos datos se mostrarán en la proxima vez que inicie sesion**
- [ ] Ver el perfil del solicitante, aprovar o rechazar al postulante. **Actualmente no esta diponible**
## Base de datos
Para guardar todos los datos utilizamos Firebase, ocupando las tecnologias de:
- [x] Authentication
- [x] Databse con Cloud Firestore
- [x] Storage

## Esquema de la base de datos
<p align="center">
  <img src="https://firebasestorage.googleapis.com/v0/b/getjob-46015.appspot.com/o/esquema.png?alt=media&token=d0048802-e605-4e90-8934-573ea997a52d" width="600" title="Esquema que se usa para el manejo de los datos">
</p>

<p align="center">
  <img src="https://firebasestorage.googleapis.com/v0/b/getjob-46015.appspot.com/o/storge_esquema.png?alt=media&token=7001eb60-c3a5-480a-b06d-9a91919ad06a" width="600" title="Esquema que se usa para guardar los archivos">
</p>

# Usuarios de prueba y inicio de seión
Para inicio de seción utilizamos el metodo de autenticacion por medio del proveedor correo electrónico/contraseña que utiliza Firebase
| Tipo de usuario | Correo | contraseña |
| ------------- | ------------- | ------------- |
| Usuario   | usuario@getjob.com  | 123456789 |
| Empresa  | empresa@getjob.com  | 123456789  
## Enlace para descargar la App en la Google Play Store
Sigue en espera de aprobación :(
Posible link, de la app: https://play.google.com/store/apps/details?id=com.edenilson.get_job
<p align="center">
  <img src="https://firebasestorage.googleapis.com/v0/b/getjob-46015.appspot.com/o/captura.png?alt=media&token=1dbe37e0-59e9-4685-bf7f-78a62e5eb289" width="800"  title="hicimos el mayor esfuerzo posible :(">
</p>
