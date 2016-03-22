package business.api.exceptions;

public class NotFoundTrainingIdException extends ApiException{
	private static final long serialVersionUID = -8036084634755698882L;

	public static final String DESCRIPTION = "No se encuentra el entrenamiento";

	public static final int CODE = 1;

	public NotFoundTrainingIdException() {
		this("");
	}

	public NotFoundTrainingIdException(String detail) {
		super(DESCRIPTION + ". " + detail, CODE);
	}
}
