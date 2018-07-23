package cn.mty.specialhelper.service.ex;

public class NikeNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4518885777393437763L;

	public NikeNotFoundException() {
		super();
		
	}

	public NikeNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public NikeNotFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public NikeNotFoundException(String message) {
		super(message);
		
	}

	public NikeNotFoundException(Throwable cause) {
		super(cause);
		
	}
    
}
