package br.edu.ifpb.pweb1.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value="loginValidator")
public class LoginValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		String login = (String)value;
		
		if (login.length() > 10) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login excede o número de caracters permitido", "Login excede o número de caracters permitido");
			throw new ValidatorException(message);
		}
		
	}

}
