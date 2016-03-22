package business.api.exceptions;

public class AlreadyExistsTrainingException extends ApiException{

	private static final long serialVersionUID = -2931634726004859488L;
	
	public static final String DESCRIPTION = "Ya existe en entrenamiento";
	
	public static final int CODE = 1;

	public AlreadyExistsTrainingException() {
		this("");
	}

	public AlreadyExistsTrainingException(String detail) {
		super(DESCRIPTION + ". " + detail, CODE);
	}
}

