import excepciones.*;

public class SistemaReservas {

    private Salon[] salones;
    private Profesor[] profesores;
    private Reserva[] reservas;

    private int totalSalones;
    private int totalProfesores;
    private int totalReservas;

    private static final int MAX = 100;
    private int contadorId = 1;

    public SistemaReservas() {
        salones = new Salon[MAX];
        profesores = new Profesor[MAX];
        reservas = new Reserva[MAX];

        totalSalones = 0;
        totalProfesores = 0;
        totalReservas = 0;
    }

    // =============================
    // REGISTRAR SALON
    // =============================
    public void registrarSalon(String codigo, int capacidad, String ubicacion)
            throws SalonDuplicadoException, CapacidadInvalidaException, CupoMaximoException {

        if (capacidad <= 0)
            throw new CapacidadInvalidaException("Capacidad invalida");

        if (totalSalones >= MAX)
            throw new CupoMaximoException("Maximo de salones alcanzado");

        if (buscarSalonPorCodigoInterno(codigo) != null)
            throw new SalonDuplicadoException("Salon ya existe");

        salones[totalSalones++] = new Salon(codigo, capacidad, ubicacion);
    }

    // =============================
    // REGISTRAR PROFESOR
    // =============================
    public void registrarProfesor(String id, String nombre, String correo)
            throws ProfesorDuplicadoException, CupoMaximoException {

        if (totalProfesores >= MAX)
            throw new CupoMaximoException("Maximo de profesores alcanzado");

        if (buscarProfesorPorIdInterno(id) != null)
            throw new ProfesorDuplicadoException("Profesor ya existe");

        profesores[totalProfesores++] = new Profesor(id, nombre, correo);
    }

    // =============================
    // CREAR RESERVA
    // =============================
    public int crearReserva(String fecha, int horaInicio, int horaFin,
                            int asistentes, String codigoSalon, String idProfesor)
            throws ReservaSolapadaException, HorarioInvalidoException,
            CapacidadInvalidaException, CupoMaximoException,
            FechaInvalidaException, SalonNoExisteException,
            ProfesorNoExisteException {

        if (totalReservas >= MAX)
            throw new CupoMaximoException("Maximo de reservas alcanzado");

        if (horaInicio >= horaFin)
            throw new HorarioInvalidoException("Horario invalido");

        if (fecha == null || fecha.isEmpty())
            throw new FechaInvalidaException("Fecha invalida");

        Salon salon = buscarSalonPorCodigo(codigoSalon);
        Profesor profesor = buscarProfesorPorId(idProfesor);

        if (asistentes > salon.getCapacidad())
            throw new CapacidadInvalidaException("Excede capacidad del salon");

        // Verificar solapamiento
        for (int i = 0; i < totalReservas; i++) {

            Reserva r = reservas[i];

            if (r.getSalon().getCodigo().equals(codigoSalon)
                    && r.getFecha().equals(fecha)) {

                if (!(horaFin <= r.getHoraInicio()
                        || horaInicio >= r.getHoraFin())) {

                    throw new ReservaSolapadaException("Reserva solapada");
                }
            }
        }

        int idReserva = contadorId++;

        reservas[totalReservas++] =
                new Reserva(fecha, horaInicio,
                        horaFin, asistentes, salon, profesor);

        return idReserva;
    }

    // =============================
    // CANCELAR RESERVA (con corrimiento)
    // =============================
    public void cancelarReserva(int id)
            throws ReservaNoExisteException {

        int pos = -1;

        for (int i = 0; i < totalReservas; i++) {
            if (reservas[i].getIdReserva() == id) {
                pos = i;
                break;
            }
        }

        if (pos == -1)
            throw new ReservaNoExisteException("Reserva no encontrada");

        for (int i = pos; i < totalReservas - 1; i++) {
            reservas[i] = reservas[i + 1];
        }

        reservas[--totalReservas] = null;
    }

    // =============================
    // LISTAR RESERVAS POR FECHA
    // =============================
    public void listarReservasPorFecha(String fecha)
            throws FechaInvalidaException {

        if (fecha == null || fecha.isEmpty())
            throw new FechaInvalidaException("Fecha invalida");

        for (int i = 0; i < totalReservas; i++) {
            if (reservas[i].getFecha().equals(fecha)) {
                System.out.println(reservas[i]);
            }
        }
    }

    // =============================
    // MOSTRAR SALONES DISPONIBLES
    // =============================
    public void mostrarSalonesDisponibles(String fecha, int hi, int hf)
            throws HorarioInvalidoException {

        if (hi >= hf)
            throw new HorarioInvalidoException("Horario invalido");

        for (int i = 0; i < totalSalones; i++) {

            boolean ocupado = false;

            for (int j = 0; j < totalReservas; j++) {

                Reserva r = reservas[j];

                if (r.getSalon().getCodigo().equals(salones[i].getCodigo())
                        && r.getFecha().equals(fecha)) {

                    if (!(hf <= r.getHoraInicio()
                            || hi >= r.getHoraFin())) {

                        ocupado = true;
                        break;
                    }
                }
            }

            if (!ocupado) {
                System.out.println(salones[i]);
            }
        }
    }

    // =============================
    // BUSQUEDAS SECUENCIALES
    // =============================

    public Salon buscarSalonPorCodigo(String codigo)
            throws SalonNoExisteException {

        Salon s = buscarSalonPorCodigoInterno(codigo);

        if (s == null)
            throw new SalonNoExisteException("Salon no existe");

        return s;
    }

    private Salon buscarSalonPorCodigoInterno(String codigo) {

        for (int i = 0; i < totalSalones; i++) {
            if (salones[i].getCodigo().equals(codigo)) {
                return salones[i];
            }
        }
        return null;
    }

    public Profesor buscarProfesorPorId(String id)
            throws ProfesorNoExisteException {

        Profesor p = buscarProfesorPorIdInterno(id);

        if (p == null)
            throw new ProfesorNoExisteException("Profesor no existe");

        return p;
    }

    private Profesor buscarProfesorPorIdInterno(String id) {

        for (int i = 0; i < totalProfesores; i++) {
            if (profesores[i].getId().equals(id)) {
                return profesores[i];
            }
        }
        return null;
    }
}