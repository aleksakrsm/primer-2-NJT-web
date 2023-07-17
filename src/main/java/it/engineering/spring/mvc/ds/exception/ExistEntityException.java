package it.engineering.spring.mvc.ds.exception;

public class ExistEntityException extends MyApplicationException{
	private static final long serialVersionUID = 1883073188255718711L;
	private final Object entity;
	
	public ExistEntityException(Object entity, String message) {
		super(message);
		this.entity = entity;
	}

	public Object getEntity() {
		return entity;
	}
}
