package excepciones;

public class SalonNoExisteException extends ReservaExcepcion {
    public SalonNoExisteException(String message) {
        super(message);
    }
}
