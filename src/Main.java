import java.util.Scanner;
import excepciones.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SistemaReservas sistema = new SistemaReservas();

        int opcion = 0;

        while (opcion != 7) {

            System.out.println("\n===== SISTEMA DE RESERVAS =====");
            System.out.println("1. Registrar salon");
            System.out.println("2. Registrar profesor");
            System.out.println("3. Crear reserva");
            System.out.println("4. Cancelar reserva");
            System.out.println("5. Listar reservas por fecha");
            System.out.println("6. Mostrar salones disponibles");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = sc.nextInt();
            sc.nextLine();

            if (opcion == 1) {

                try {

                    System.out.print("Codigo del salon: ");
                    String codigo = sc.nextLine();

                    System.out.print("Capacidad: ");
                    int capacidad = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Ubicacion: ");
                    String ubicacion = sc.nextLine();

                    sistema.registrarSalon(codigo, capacidad, ubicacion);

                    System.out.println("Salon registrado correctamente");

                } catch (Exception e) {

                    System.out.println("Error: " + e.getMessage());
                }

            }
            else if (opcion == 2) {

                try {

                    System.out.print("ID del profesor: ");
                    String id = sc.nextLine();

                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Correo: ");
                    String correo = sc.nextLine();

                    sistema.registrarProfesor(id, nombre, correo);

                    System.out.println("Profesor registrado correctamente");

                } catch (Exception e) {

                    System.out.println("Error: " + e.getMessage());
                }

            }
            else if (opcion == 3) {

                try {

                    System.out.print("Codigo salon: ");
                    String codigoSalon = sc.nextLine();

                    Salon salon = sistema.buscarSalonPorCodigo(codigoSalon);

                    System.out.print("ID profesor: ");
                    String idProfesor = sc.nextLine();

                    Profesor profesor = sistema.buscarProfesorPorId(idProfesor);

                    System.out.print("Fecha (YYYY-MM-DD): ");
                    String fecha = sc.nextLine();

                    System.out.print("Hora inicio: ");
                    int horaInicio = sc.nextInt();

                    System.out.print("Hora fin: ");
                    int horaFin = sc.nextInt();

                    System.out.print("Cantidad asistentes: ");
                    int asistentes = sc.nextInt();
                    sc.nextLine();

                    int idReserva = sistema.crearReserva(
                            fecha,
                            horaInicio,
                            horaFin,
                            asistentes,
                            codigoSalon,
                            idProfesor
                    );

                    System.out.println("Reserva creada con ID: " + idReserva);

                }
                catch (SalonNoExisteException e) {

                    System.out.println("El salon no existe");

                }
                catch (ProfesorNoExisteException e) {

                    System.out.println("El profesor no existe");

                }
                catch (ReservaDuplicadaException |
                       ReservaSolapadaException |
                       HorarioInvalidoException |
                       CapacidadInvalidaException |
                       CupoMaximoException |
                       FechaInvalidaException e) {

                    System.out.println("Error: " + e.getMessage());
                }

            }
            else if (opcion == 4) {

                try {

                    System.out.print("ID reserva: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    sistema.cancelarReserva(id);

                    System.out.println("Reserva cancelada");

                }
                catch (ReservaNoExisteException e) {

                    System.out.println("Error: " + e.getMessage());
                }

            }
            else if (opcion == 5) {

                try {

                    System.out.print("Fecha: ");
                    String fecha = sc.nextLine();

                    sistema.listarReservasPorFecha(fecha);

                }
                catch (FechaInvalidaException e) {

                    System.out.println("Error: " + e.getMessage());
                }

            }
            else if (opcion == 6) {

                try {

                    System.out.print("Fecha: ");
                    String fecha = sc.nextLine();

                    System.out.print("Hora inicio: ");
                    int hi = sc.nextInt();

                    System.out.print("Hora fin: ");
                    int hf = sc.nextInt();
                    sc.nextLine();

                    sistema.mostrarSalonesDisponibles(fecha, hi, hf);

                }
                catch (Exception e) {

                    System.out.println("Error: " + e.getMessage());
                }

            }
            else if (opcion == 7) {

                System.out.println("Programa finalizado");

            }
            else {

                System.out.println("Opcion invalida");
            }
        }

        sc.close();
    }
}
