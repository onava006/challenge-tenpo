# Spring Boot Challenge - API de Cálculo con Cache

API REST desarrollada con Spring Boot que calcula sumas aplicando un porcentaje obtenido desde un servicio externo, con sistema de caché implementado en Redis.

## 🚀 Características

- Cálculo de suma con porcentaje dinámico
- Caché distribuido con Redis
- Base de datos PostgreSQL
- Arquitectura hexagonal
- Generación de API con OpenAPI Generator
- Containerización con Docker

## 📋 Requisitos Previos

- Docker
- Docker Compose
- Git

## 🔧 Instalación y Ejecución

### 1. Clonar el repositorio

```bash
git clone https://github.com/onava006/challenge-tenpo.git
cd challenge-tenpo
```

### 2. Configuración

El proyecto incluye un archivo `docker-compose.yaml` con toda la configuración necesaria:

- **PostgreSQL**: Base de datos en puerto 5432
- **Redis**: Sistema de caché en puerto 6379
- **Spring Boot App**: API REST en puerto 8080

### 3. Levantar los servicios

```bash
docker compose up -d
```

Esto iniciará automáticamente:
- Base de datos PostgreSQL
- Servidor Redis
- Aplicación Spring Boot

### 4. Verificar que los servicios están corriendo

```bash
docker compose ps
```
### 5. Monitorear log de aplicacion 

```bash
docker logs -f challenge
```


Todos los servicios deberían mostrar estado "healthy" o "running".

## 📡 Endpoints Disponibles

### Calcular Total con Porcentaje

```http
GET http://localhost:8080/calculatetotal?num1=10&num2=20
```

**Parámetros:**
- `num1` (required): Primer número a sumar
- `num2` (required): Segundo número a sumar

**Respuesta:**
```json
{"suma":30.0,"porcentaje":10.0,"total":33.0}
```

El sistema utiliza Redis para cachear el porcentaje obtenido del servicio externo, mejorando el rendimiento en llamadas posteriores.

### Cargar lista de llamadas telefonicas

```http
POST http://localhost:8080/registerphonecalls
```
```bash

curl -X POST http://localhost:8080/registerphonecalls \
  -H "Content-Type: application/json" \
  -d '[
    {
      "date": "2025-10-30T10:00:00",
      "endpoint": "+569",
      "parameters": "911111111",
      "callstatus": "RECEIVED"
    },
    {
      "date": "2025-10-30T10:25:00",
      "endpoint": "+569",
      "parameters": "966666666",
      "callstatus": "REDIRECTED"
    }
  ]'

```
El sistema almacena el registro en un contenedor con base de datos postgres. La operacion es de tipo asincrona, por lo que es no bloqueante

### Muestra los numeros de telefono que han sido cargados


```http
GET http://localhost:8080/getphonecalls
```




## 🛠️ Tecnologías Utilizadas

- **Spring Boot** - Framework principal
- **PostgreSQL** - Base de datos relacional
- **Redis** - Sistema de caché
- **OpenAPI Generator** - Generación de API
- **Docker & Docker Compose** - Containerización
- **Gradle** - Gestión de dependencias