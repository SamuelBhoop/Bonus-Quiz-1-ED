package excepciones;

public abstract class ReservaExcepcion extends RuntimeException {
    public ReservaExcepcion(String message) {
        super(message);
    }
}
