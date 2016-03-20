package business.api.exceptions;

public class InvalidCourtTrainingException extends ApiException{
	
	private static final long serialVersionUID = -4831087080119638007L;
	
	public static final String DESCRIPTION = "Entrenamiento en pista inválida";
	
	public static final int CODE = 1;
	
	public InvalidCourtTrainingException(){
		this("");
	}
	
	public InvalidCourtTrainingException(String detail) {
		super(DESCRIPTION + ". " + detail, CODE);
	}
}
