# Sistema de Gestión de Suscripciones de TV

Este proyecto implementa un sistema para gestionar suscripciones de TV por sectores, con paquetes de canales y suscriptores.

## Estructura del Proyecto

### Clases Principales:

1. **AdminSuscripciones**: Gestiona suscripciones, sectores y paquetes globales.
   - Utiliza ArrayList para almacenar suscripciones, sectores y paquetes globales.
   - Métodos principales: agregarSuscriptor, agregarPaqueteGlobal, agregarPaqueteCanalesASector.

2. **Suscripcion**: Representa una suscripción en un sector específico.
   - Usa HashMap para almacenar suscriptores y ArrayList para paquetes disponibles.
   - Métodos clave: agregarPaqueteCanales, mostrarPaquetesCanales.

3. **PaqueteCanales**: Define un paquete de canales.
   - Contiene lista de canales (ArrayList) y detalles de precios.

4. **Suscriptor**: Representa a un suscriptor individual.
   - Almacena información como nombre, edad y ID único.

5. **Menu**: Maneja la interacción con el usuario.
   - Utiliza BufferedReader para entrada de usuario.
   - Implementa un bucle principal para mostrar opciones y procesar selecciones.

## Funcionalidades Principales

- Agregar y listar suscriptores por sector.
- Crear y gestionar paquetes de canales globales.
- Asignar paquetes de canales a sectores específicos.
- Identificar sectores con baja captación de clientes.

## Interacciones Clave

- **Menu** interactúa con **AdminSuscripciones** para realizar operaciones.
- **AdminSuscripciones** gestiona **Suscripcion**, **PaqueteCanales** y **Suscriptor**.
- **Suscripcion** contiene múltiples **Suscriptor** y **PaqueteCanales**.

## Uso

Ejecute la clase Menu para iniciar la aplicación. Siga las instrucciones en pantalla para navegar por las opciones del sistema.