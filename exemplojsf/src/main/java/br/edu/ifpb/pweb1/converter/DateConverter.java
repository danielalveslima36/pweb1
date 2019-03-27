package br.edu.ifpb.pweb1.converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("dateConverter")
public class DateConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String data) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime localDateTime = LocalDateTime.parse(data, formatter);
		
		Timestamp timestamp = Timestamp.valueOf(localDateTime);
		return timestamp;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
			
		Timestamp timestamp = (Timestamp)value;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String data = LocalDateTime.from(timestamp.toLocalDateTime()).format(formatter);

		return data;
	}

}
