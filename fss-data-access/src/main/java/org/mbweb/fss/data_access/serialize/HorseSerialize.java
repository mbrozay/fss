package org.mbweb.fss.data_access.serialize;

import java.util.ArrayList;

import org.hibernate.Query;
import org.mbweb.fss.data_access.model.Horse;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class HorseSerialize {
	@JsonCreator
public HorseSerialize (Query query) throws JsonProcessingException{
	
	ArrayList<Horse> allHorses = (ArrayList<Horse>) query.list();
	ObjectMapper mapper = new ObjectMapper();
	mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
	String rootName = Horse.class.getAnnotation(JsonRootName.class).value();
	String jacksonJson = mapper.writer().withRootName(rootName).withDefaultPrettyPrinter().writeValueAsString(allHorses);
	System.out.println(jacksonJson);
	
	}
}
