# Mi Agenda - Gestión de Contactos

Actividad Obligatoria Integradora 1 (AOI1) correspondiente a la cátedra de **Programación de Dispositivos Móviles I** (Tecnicatura Universitaria en Desarrollo de Aplicaciones Informáticas - TUDAI).

## 📱 Descripción del Proyecto

**Mi Agenda** es una aplicación móvil nativa para Android desarrollada en **Java**. La aplicación permite gestionar una libreta de contactos personal, ofreciendo funcionalidades de autenticación de usuarios, listado, búsqueda y creación de contactos.

## 🛠️ Tecnologías y Stack Tecnológico

- **Lenguaje:** Java
- **Plataforma:** Android (SDK Mínimo: 24 / Android 7.0)
- **UI / Vistas:** XML Layouts, ConstraintLayout, Material Components
- **Arquitectura y Patrones:** View Binding, Activity Lifecycle
- **Build System:** Gradle (Kotlin DSL)

## 📂 Estructura Principal del Proyecto

El código fuente se encuentra organizado bajo el paquete `com.alarcon063.gestioncontactos`:

- **`view/`**
  - `MainActivity`: Pantalla de inicio de sesión (Login).
  - `ContactsActivity`: Pantalla principal de listado y búsqueda de contactos.
  - `FormActivity`: Formulario para registrar un nuevo contacto.
  - `ContactManager`: Lógica de gestión y navegación de contactos.
- **`domain/`**
  - `Contact`: Entidad que representa la información de un contacto (nombre, género, etc.).
  - `Gender`: Enum con los géneros disponibles.
- **`data/`**
  - `ContactsRepository`: Repositorio para la administración de datos de contactos.
- **`interactor/`**
  - `LoginInteractor`: Lógica de autenticación.

## 🔑 Credenciales de Acceso (Login)

Para iniciar sesión e ingresar a la aplicación, utilice los siguientes datos predeterminados en la pantalla de bienvenida (MainActivity):

- **Usuario:** usuario
- **Contraseña:** valido
