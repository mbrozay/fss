package org.mbweb.fss.restfuljson.controller;

import java.io.IOException;
import java.util.List;

import org.mbweb.fss.data_access.model.HorseSelectorJson;
import org.mbweb.fss.data_access.model.Player;
import org.mbweb.fss.data_access.model.PlayerJson;
import org.mbweb.fss.data_access.model.Player_Horse_noId;
import org.mbweb.fss.restfuljson.dao.HorsePicker;
import org.mbweb.fss.restfuljson.dao.ListHorseById;
import org.mbweb.fss.restfuljson.dao.LoginValidate;
import org.mbweb.fss.restfuljson.dao.NewPlayer;
import org.mbweb.fss.restfuljson.dao.returnHorses;
import org.mbweb.fss.restfuljson.dao.returnHorsesPlayer;
import org.mbweb.fss.restfuljson.model.Player_pojo;
import org.mbweb.fss.restfuljson.model.HorsePicker_pojo;
import org.mbweb.fss.restfuljson.model.Login_pojo;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

@CrossOrigin(origins = "*")
@RestController
public class Controller {
	@CrossOrigin(origins = "*")
	@RequestMapping("/greeting1")
    public String thl() throws JsonProcessingException{
		returnHorses rh1 = new returnHorses();
    	String horse = rh1.HorseList();
		return horse;
}
	
	@CrossOrigin(origins = "*")
	@RequestMapping("/horseSelector")
    public String thl1() throws JsonProcessingException{
		returnHorsesPlayer rh1 = new returnHorsesPlayer();
    	String horse = rh1.HorePlayerList();
		return horse;
}
	
	@RequestMapping("/greeting2")
	public String stringtest(@RequestParam(value="horseId", defaultValue="1") String horseId){
		
		ListHorseById lhbi = new ListHorseById();
		String horseName = lhbi.HorseList(horseId);
		return horseName;
	}
	
	//	Use this url
	//http://localhost:8080/fss-rest-json/greeting3?horseDetail= [{"playerId" : "1", "horseId" : "1"},{"playerId" : "1", "horseId" : "17"}]
	@RequestMapping("/greeting3")
	public String jsontest(@RequestParam(value="horseDetail", defaultValue="null") String horseDetailJson) throws JsonParseException, JsonMappingException, IOException{
		
		ListHorseById lhbi = new ListHorseById();
		String horseName = lhbi.HorseListJson(horseDetailJson);
		return horseName;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/login", method = RequestMethod.POST)	
	public  @ResponseBody String login_JSON( @RequestBody Login_pojo login ) throws JsonProcessingException   {		
		LoginValidate loginValidate = new LoginValidate();
		String result = loginValidate.LoginCheckCreds(login);
		login.setResponse(result);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(login);
		
		return jsonInString;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/signup", method = RequestMethod.POST)	
	public  @ResponseBody String signup_JSON( @RequestBody Player player ) throws JsonProcessingException   {		
		NewPlayer newplayer = new NewPlayer();
		String result = newplayer.PlayerSignup(player);
		Player_pojo playerpojo = new Player_pojo();
		playerpojo.setMessage(result + " " + player.getFirstName());
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(playerpojo);
		
		return jsonInString;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/horsePicker", method = RequestMethod.POST)	
	public  @ResponseBody String horsePicker_JSON( @RequestBody HorsePicker_pojo horsePicker_pojo ) throws JsonProcessingException   {		
		HorsePicker horsePicker = new HorsePicker();
		String result = horsePicker.SelectHorses(horsePicker_pojo);
		
		
		horsePicker_pojo.setResponse(result);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(horsePicker_pojo);
		
		return jsonInString;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/horseSelector", method = RequestMethod.POST)	
	public  @ResponseBody String horseSelecto_JSON( @RequestBody HorseSelectorJson horseSelectorJson) throws JsonProcessingException   {		
		
		ObjectMapper mapper = new ObjectMapper();
		horseSelectorJson.setMessage("Success from " + horseSelectorJson.getHorsename());
		String jsonInString = mapper.writeValueAsString(horseSelectorJson);
		
		return jsonInString;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping("/signup1")
	public String signup1(@RequestParam(value="horseDetail", defaultValue="null") String signup1DetailJson) throws JsonParseException, JsonMappingException, IOException{
		

		return signup1DetailJson;
	}
	
}
