# Aplicación Web Dockerizada

Este proyecto contiene una aplicación web completa ejecutándose en contenedores Docker.

La arquitectura está compuesta por:

* **Frontend:** Aplicación SPA servida con **Nginx**
* **Backend:** API REST desarrollada con **Spring Boot**
* **Base de datos:** **MySQL**

Los contenedores se comunican mediante redes Docker separadas para mejorar la seguridad y organización.

---

# Arquitectura

La aplicación utiliza **dos redes Docker**:

* **web:** comunicación entre frontend y backend
* **server:** comunicación entre backend y base de datos

```
            web network
     frontend  <---->  backend
                           |
                           |
                     server network
                           |
                        mysql-db
```

Esto asegura que:

* El **frontend no tenga acceso directo a la base de datos**
* Solo el **backend gestione la comunicación con MySQL**

---

# Estructura del proyecto

```
.
├── back
│   ├── Dockerfile
│   ├── .dockerignore
│   └── código backend (Spring Boot)
│
├── front
│   ├── Dockerfile
│   ├── .dockerignore
│   ├── nginx.conf
│   └── código frontend (SPA)
│
└── README.md
```

---

# Precondiciones

Se debe tener instalado:

* Docker
* Git

Verificar instalación:

```bash
docker --version
git --version
```

---

# 1. Crear redes Docker

Red para comunicación entre **frontend y backend**:

```bash
docker network create web
```

Red para comunicación entre **backend y base de datos**:

```bash
docker network create server
```

---

# 2. Crear volumen para MySQL

Se crea un volumen para persistir los datos de la base de datos.

```bash
docker volume create backend
```

---

# 3. Construir imágenes Docker

Construir imagen del backend:

```bash
docker build -t backend .
```

Construir imagen del frontend:

```bash
docker build -t frontend .
```

---

# 4. Ejecutar contenedor de MySQL

La base de datos se conecta únicamente a la red **server**.

```bash
docker run -d \
--name mysql-db \
--network server \
-e MYSQL_ROOT_PASSWORD=root \
-e MYSQL_DATABASE=todolist \
-v mysql-data:/var/lib/mysql \
-p 3306:3306 \
mysql:8
```

---

# 5. Ejecutar contenedor del Backend

El backend se conecta primero a la red **server** para comunicarse con MySQL.

```bash
docker run -d \
--name backend \
--network server \
-p 8080:8080 \
-e DB_HOST=mysql-db \
-e DB_PORT=3306 \
-e DB_USER=root \
-e DB_PASSWORD=root \
-e DB_NAME=todolist \
backend
```

Luego se conecta también a la red **web** para comunicarse con el frontend.

```bash
docker network connect web backend
```

---

# 6. Ejecutar contenedor del Frontend

El frontend se conecta únicamente a la red **web**.

```bash
docker run -d \
--name frontend \
--network web \
-p 3000:80 \
frontend
```

---

# Acceso a la aplicación

Una vez ejecutados los contenedores, la aplicación estará disponible en:

```
http://localhost:3000
```

---

# Flujo de la aplicación

```
Usuario
   │
   ▼
Frontend (Nginx)
   │
   ▼
Backend (Spring Boot API)
   │
   ▼
MySQL Database
```

---

# Verificar contenedores en ejecución

```bash
docker ps
```

Deberían aparecer los siguientes contenedores:

* `frontend`
* `backend`
* `mysql-db`

---

# Verificar redes Docker

Red frontend-backend:

```bash
docker network inspect web
```

Red backend-database:

```bash
docker network inspect server
```

---

# Detener contenedores

```bash
docker stop frontend backend mysql-db
```

---

# Eliminar contenedores

```bash
docker rm frontend backend mysql-db
```

---

# Eliminar redes

```bash
docker network rm web
docker network rm server
```

---

# Eliminar volumen de base de datos

```bash
docker volume rm backend
```


