package excepciones;

public class ReservaDuplicadaException extends RuntimeException {
  public ReservaDuplicadaException(String message) {
    super(message);
  }
}
