package com.gks.to;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude
public class EmailTO {
	private List<String> receipients;
	private List<String> ccList;
	private List<String> bccList;
	private String sender;
	private String mailSubject;
	private String mailBody;
	private boolean isHtml;
	private String attachementPath;

}
