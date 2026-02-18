import excepciones.*;

public class SistemaReservas {
    private Salon[] salones;
    private int nSalones;
    private Profesor[]  profesores;
    private int nProfesores;
    private Reserva[] reservas;
    private int nReservas;
    private int consecutivoReserva;

    public SistemaReservas() {
    }
    public void registrarSalon(String codigo,int capacidad, String ubicacion) throws SalonDuplicadoException, CapacidadInvalidaException {
        for(Salon salon:salones){
            if(salon==null) break;

            if(salon.getCodigo().equals(codigo)){
                throw  new SalonDuplicadoException("Este salon ya existe");
            }
        }
        if(capacidad<=0 || capacidad>30 || this.salones[nSalones-1]!=null){
            throw  new CapacidadInvalidaException("Capacidad excedida o negativa");
        }
        for(Salon salon:salones){
            if(salon==null){
                salon = new Salon(codigo,capacidad,ubicacion);
            break;
            }
        }
    }
    public void registrarProfesor(String id, String nombre, String correo)throws ProfesorDuplicadoException, CapacidadInvalidaException {
        for(Profesor profesor:profesores){
            if(profesor==null) break;
            if(profesor.getId().equals(id)){
                throw new ProfesorDuplicadoException("Profesor ya existe");
            }
        }
        if(profesores[nProfesores-1]!=null){
            throw new CapacidadInvalidaException("se ha llegado al limite de profesores");
        }
        for (Profesor profesor:profesores){
            if(profesor==null){
                profesor  = new Profesor(id, nombre, correo);
            break;
            }
        }
    }
    public int crearReserva(String fecha, int horaInicio, int horaFin, int asistentes, String codigoSalon, String idProfesor)throws ReservaSolapadaException, HorarioInvalidoException, CapacidadInvalidaException, SalonNoExisteException, ProfesorNoExisteException {
        boolean existe = false;
        int isalon = 0;
        int iprofesor = 0;
        int ireserva = 0;
        for(int i=0;i<salones.length;i++) {
            if (salones[i] == null) break;
            if (salones[i].getCodigo().equals(codigoSalon)) {
                existe = true;
                isalon = i;
                break;
            }
        }
        if(!existe) throw new SalonNoExisteException("el Salon no existe");
        existe = false;

        for(int i=0;i<profesores.length;i++) {
            if (profesores[i] == null) break;
            if (profesores[i].getId().equals(idProfesor)) {
                existe = true;
                iprofesor = i;
                break;
            }
        }
        if(!existe) throw new ProfesorNoExisteException("el profesor no existe");

        if(reservas[nReservas-1]!=null) throw  new CapacidadInvalidaException("Ya se alcanzo el maximo de reservas");
        if(horaInicio<6||horaFin<7||horaInicio>19||horaFin>20) throw  new HorarioInvalidoException("Los horarios solo estan definidos de 6:00 am a 20:00 pm");

        for(int i=0;i<reservas.length;i++) {
            if(reservas[i]==null) {
                reservas[i] = new Reserva(fecha, horaInicio, horaFin, asistentes, salones[isalon], profesores[iprofesor]);
                ireserva = i;
                break;
            }
        }
        for(Reserva reserva:reservas){
            if(reserva==null) break;
            if(reserva.seCruzaCon(reservas[ireserva])) throw new ReservaSolapadaException("La reserva se cruza con otra reserva");
        }
        return ireserva;

    }

}
