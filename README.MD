Sistema de Manejo de Redes de Televisión por Cable
(Se que el readme no se usa par esto jaja pero ya lo hice uwu)
//felipeb

04-09
hice un par de mods a tu código original

Cambios Recientes:
1. Nueva clase PaqueteCanales: La añadí para gestionar los diferentes paquetes de canales que ofrecemos. Esto nos permite tener una estructura clara para cada oferta.

2. Actualización de Suscripcion: Modifiqué esta clase para incluir una lista de PaqueteCanales. Ahora podemos asociar paquetes específicos a cada suscripción.

3. Cambios en AdminSuscripciones: Agregué métodos para manejar los paquetes de canales por sector. Esto nos da más flexibilidad en la gestión de ofertas por zona (no se si es tan necesario).

4. Expansión del menú principal: Incluí opciones para gestionar los paquetes de canales.

5. Identificación de sectores débiles: Implementé una función para detectar áreas con baja captación de clientes (se puede modificar, ahora solo hace suscripcion.getMapSuscriptores().size() < 5)
