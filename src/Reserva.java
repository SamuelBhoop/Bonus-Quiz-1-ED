public class Reserva {
    private int idReserva;
    private static int globalIdReserva = 0;
    private String fecha;
    private int horaInicio;
    private int horaFin;
    private int asistentes;
    private Salon salon;
    private Profesor profesor;

    public Reserva(String fecha,int horaInicio, int horaFin, int asistentes, Salon salon, Profesor profesor) {
        this.idReserva = ++globalIdReserva;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.asistentes = asistentes;
        this.salon = salon;
        this.profesor = profesor;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(int horaFin) {
        this.horaFin = horaFin;
    }

    public int getAsistentes() {
        return asistentes;
    }

    public void setAsistentes(int asistentes) {
        this.asistentes = asistentes;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public boolean seCruzaCon(Reserva reserva){
    if(this.fecha.equals(reserva.getFecha())&&this.horaInicio>=reserva.getHoraInicio()&&this.horaFin<reserva.getHoraInicio()){
        return true;
    }
    return false;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "idReserva=" + idReserva +
                ", fecha='" + fecha + '\'' +
                ", horaInicio=" + horaInicio +
                ", horaFin=" + horaFin +
                ", asistentes=" + asistentes +
                ", salon=" + salon +
                ", profesor=" + profesor +
                '}';
    }
}
