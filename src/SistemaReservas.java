import excepciones.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SistemaReservas sistema = new SistemaReservas(50, 50, 50);

        int opcion;

        do {
            System.out.println("\n===== SISTEMA DE RESERVAS =====");
            System.out.println("1. Registrar salón");
            System.out.println("2. Registrar profesor");
            System.out.println("3. Crear reserva");
            System.out.println("4. Cancelar reserva");
            System.out.println("5. Listar reservas por fecha");
            System.out.println("6. Mostrar salones disponibles");
            System.out.println("7. Buscar salón por código");
            System.out.println("8. Buscar profesor por ID");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    try {
                        System.out.print("Código del salón: ");
                        String codigo = sc.nextLine();

                        System.out.print("Capacidad: ");
                        int capacidad = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Ubicación: ");
                        String ubicacion = sc.nextLine();

                        sistema.registrarSalon(codigo, capacidad, ubicacion);
                        System.out.println("Salón registrado correctamente.");

                    } catch (SalonDuplicadoException e) {
                        System.out.println("Ya existe un salón con ese código.");
                    } catch (CupoMaximoException e) {
                        System.out.println("No se pueden registrar más salones.");
                    }
                    break;

                case 2:
                    try {
                        System.out.print("ID del profesor: ");
                        String id = sc.nextLine();

                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();

                        System.out.print("Correo: ");
                        String correo = sc.nextLine();

                        sistema.registrarProfesor(id, nombre, correo);
                        System.out.println("Profesor registrado correctamente.");

                    } catch (ProfesorDuplicadoException e) {
                        System.out.println("Ese profesor ya está registrado.");
                    } catch (CupoMaximoException e) {
                        System.out.println("No se pueden registrar más profesores.");
                    }
                    break;

                case 3:
                    try {
                        System.out.print("Fecha (YYYY-MM-DD): ");
                        String fecha = sc.nextLine();

                        sistema.fechaValida(fecha);
                        
                        System.out.print("Hora inicio: ");
                        int horaInicio = sc.nextInt();

                        System.out.print("Hora fin: ");
                        int horaFin = sc.nextInt();

                        System.out.print("Número de asistentes: ");
                        int asistentes = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Código del salón: ");
                        String codSalon = sc.nextLine();

                        System.out.print("ID del profesor: ");
                        String idProf = sc.nextLine();

                        sistema.buscarSalonPorCodigo(codSalon);
                        sistema.buscarProfesorPorID(idProf);

                        int idReserva = sistema.crearReserva(
                                fecha, horaInicio, horaFin,
                                asistentes, codSalon, idProf
                        );

                        System.out.println("Reserva creada con ID: " + idReserva);

                    } catch (FechaInvalidaException e) {
                        System.out.println("Fecha inválida. Use formato YYYY-MM-DD.");
                    } catch (HorarioInvalidoException e) {
                        System.out.println("Horario inválido.");
                    } catch (ReservaSolapadaException e) {
                        System.out.println("Ya existe una reserva en ese horario.");
                    } catch (CapacidadInvalidaException e) {
                        System.out.println("El salón no tiene capacidad suficiente.");
                    } catch (SalonNoExisteException e) {
                        System.out.println("El salón no existe.");
                    } catch (ProfesorNoExisteException e) {
                        System.out.println("El profesor no existe.");
                    } catch (ReservaDuplicadaException e) {
                        System.out.println("La reserva ya existe.");
                    } catch (CupoMaximoException e) {
                        System.out.println("No se pueden crear más reservas.");
                    }
                    break;

                case 4:
                    try {
                        System.out.print("ID de la reserva: ");
                        int idCancelar = sc.nextInt();

                        sistema.cancelarReserva(idCancelar);
                        System.out.println("Reserva cancelada correctamente.");

                    } catch (ReservaNoExisteException e) {
                        System.out.println("No existe una reserva con ese ID.");
                    }
                    break;

                case 5:
                    System.out.print("Ingrese fecha: ");
                    String fechaConsulta = sc.nextLine();
                    sistema.listarReservasPorFecha(fechaConsulta);
                    break;

                case 6:
                    System.out.print("Fecha: ");
                    String fechaDisp = sc.nextLine();

                    System.out.print("Hora inicio: ");
                    int hi = sc.nextInt();

                    System.out.print("Hora fin: ");
                    int hf = sc.nextInt();

                    sistema.mostrarSalonesDisponibles(fechaDisp, hi, hf);
                    break;

                case 7:
                    try {
                        System.out.print("Código del salón: ");
                        String codigoBuscar = sc.nextLine();

                        System.out.println(
                                sistema.buscarSalonPorCodigo(codigoBuscar)
                        );

                    } catch (SalonNoExisteException e) {
                        System.out.println("El salón no existe.");
                    }
                    break;

                case 8:
                    try {
                        System.out.print("ID del profesor: ");
                        String idBuscar = sc.nextLine();

                        System.out.println(
                                sistema.buscarProfesorPorID(idBuscar)
                        );

                    } catch (ProfesorNoExisteException e) {
                        System.out.println("El profesor no existe.");
                    }
                    break;

                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);

        sc.close();
    }
}
