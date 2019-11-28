package br.gov.df.se.pdaf.utils;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Value;

@ManagedBean
public class RestTemplateUtils {
	
	@Value("${url.endpoint}")
	private String urlEndPoint;
	
}
