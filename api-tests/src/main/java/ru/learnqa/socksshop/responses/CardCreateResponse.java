package ru.learnqa.socksshop.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardCreateResponse{

	@JsonProperty("id")
	private String id;

	public String getId(){
		return id;
	}
}