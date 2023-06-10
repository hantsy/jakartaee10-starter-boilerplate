package com.example.web;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import java.util.UUID;

@FacesConverter(value = "uuidConverter")
public class UUIDConverter implements Converter<UUID>{

    @Override
    public UUID getAsObject(FacesContext context, UIComponent component, String value) {
        if(value!=null && !value.isBlank()) {
            return UUID.fromString(value);
        }else{
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, UUID value) {
         if(value!=null) {
            return value.toString();
        }else{
            return null;
        }
    }
    
}
