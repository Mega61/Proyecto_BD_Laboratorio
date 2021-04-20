# Proyecto_BD_Laboratorio
Proyecto de base de datos Universidad de la sabana

Lista de funciones y caracteristicas:

2 Tipos de sesiones:
    -> Usuario
    -> Médido/empleado laboratorio
    -> Admin (Usuario y contraseña especificos)


2 CRUD:
    -> Medico
    -> Usuario

Examenes Create - Read - Delete (despues de 10 años)

Funciones:


1. Login/Register (Solo paciente) 
2. Login (Solo médico)
3. Auto asignación de paciente a médico con lista de espera por fechas.

Sesión Paciente:
1. Register -> Login (Usar cédula para username)
2. Pedir una cita
3. Solicitar examen con la orden del médico
4. Consulta los resultados de las pruebas
    -> Consulta de historial de pruebas de laboratorio 
5. Modificar Datos (menos cédula)
6. Solicitar eliminación del perfil

Sesión médico:
1. Login -> (Usar ID médico para username)
2. Revise sus citas pendientes
3. Finalizar cita (Cambio de estado en el paciente)
    -> Generar orden de laboratorio
4. Solicitar resultados de laboratorio de los pacientes que ya solicitaron el examen médico con la orden de laboratorio.
5. Modificar datos menos ID
6. Solicitar eliminacion del perfil

Sesión Admin: 
1. Create Médico
2. Eliminación del paciente
3. Eliminación del médico
4. Consulta de tablas de BD
5. Login -> (Usando ID y contraseñas especificos)


Front End:

Landing Page con login/register arriba a la derecha -> boton de logout
Login/Register igual para todas las sesiones

Sesión Paciente: 
    - Landing Page
    - Perfil -> (Modificación de datos, eliminación)
    - Solicitar cita -> Escoger motivo -> Escoger fecha
    - Solicitar examen de laboratorio -> fecha autogeneradad -> Generar documento de cita
    - Consultar resultados (Lista de fechas)


Sesión Médico:
    - Landing page (Menu de barra superior, Calendario para el cuerpo)
    - Boton barra superior citas -> Lista de citas pendientes o en curso (Mostrar estados) -> Cada cita Boton para finalizar cita y para solicitar resultados de laboratorio
    - Boton de historial de paciente
    - Boton perfil (modificar datos, solicitar retiro)


Sesión Admin:
    - Busqueda
    - Boton de lista de pacientes
    - Boton de lista de médicos
    - Boton de lista de citas