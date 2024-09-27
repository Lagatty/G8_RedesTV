# Sistema de Gestión de Suscripciones de TV

Este proyecto implementa un sistema avanzado para gestionar suscripciones de TV por sectores, con paquetes de canales y suscriptores, utilizando una base de datos SQLite para la persistencia de datos y una interfaz gráfica de usuario (GUI) basada en Swing.

## Estructura del Proyecto

### Paquetes Principales:
1. **model**: Contiene las clases de entidades.
2. **view**: Incluye las clases relacionadas con la interfaz de usuario.
3. **controller**: Alberga los controladores que manejan la lógica de negocio.
4. **util**: Contiene clases utilitarias, como el gestor de base de datos.

### Clases Principales:

1. **Suscriptor (model)**: Representa a un suscriptor individual.
   - Atributos: id, nombre, edad, sector.

2. **Sector (model)**: Define un sector geográfico.
   - Atributos: id, nombre, lista de paquetes disponibles.

3. **PaqueteCanales (model)**: Define un paquete de canales.
   - Atributos: id, nombre, lista de canales, precioBase, precioOferta, enOferta.

4. **DBManager (util)**: Gestiona la conexión y operaciones con la base de datos SQLite.
   - Métodos principales: getConnection, initDB.

5. **SuscriptorController (controller)**: Maneja la lógica de negocio para suscriptores.
   - Métodos clave: agregarSuscriptor, listarSuscriptores, modificarSuscriptor, eliminarSuscriptor.

6. **SectorController (controller)**: Gestiona operaciones relacionadas con sectores.
   - Métodos principales: agregarSector, listarSectores, identificarSectoresDebiles.

7. **PaqueteCanalesController (controller)**: Controla operaciones de paquetes de canales.
   - Métodos clave: agregarPaquete, listarPaquetes, asignarPaqueteASector.

8. **MainWindow (view)**: Ventana principal de la interfaz gráfica.
   - Implementa todas las funcionalidades del sistema mediante botones y diálogos.

## Funcionalidades Principales

- Agregar, listar, modificar y eliminar suscriptores.
- Crear y gestionar sectores.
- Crear y asignar paquetes de canales a sectores.
- Identificar sectores con baja captación de clientes (sectores débiles).
- Ver estadísticas generales del sistema.

## Mejoras Implementadas

- Persistencia de datos utilizando SQLite.
- Interfaz gráfica de usuario para una mejor experiencia de uso.
- Identificación mejorada de sectores débiles basada en múltiples criterios.
- Sistema de gestión de paquetes de canales más robusto.

## Uso

1. Ejecute la clase `Main` para iniciar la aplicación.
2. Utilice la interfaz gráfica para navegar por las diferentes funcionalidades del sistema.
3. Los datos se almacenan automáticamente en la base de datos SQLite.

## Requisitos

- Java JDK 11 o superior.
- SQLite JDBC Driver (incluido en las dependencias de Maven).

## Configuración del Proyecto

El proyecto utiliza Maven para la gestión de dependencias. Asegúrese de tener Maven instalado y configurado en su entorno de desarrollo.

## Para construir el proyecto:
mvn clean install
java -jar target/SistemaTV-1.0-SNAPSHOT.jar

## Notas Adicionales

- La base de datos se inicializa automáticamente con algunos sectores de ejemplo al ejecutar la aplicación por primera vez.
- Se recomienda utilizar un IDE compatible con Maven para una mejor experiencia de desarrollo.