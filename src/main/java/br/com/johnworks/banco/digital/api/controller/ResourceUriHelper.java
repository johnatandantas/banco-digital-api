package br.com.johnworks.banco.digital.api.controller;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class ResourceUriHelper {

	public static void addUriInResponseHeader(Object resourceId, String path) {
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
			.path(path)
			.buildAndExpand(resourceId).toUri();
		
		HttpServletResponse response = ((ServletRequestAttributes) 
				RequestContextHolder.getRequestAttributes()).getResponse();
		
		response.setHeader(HttpHeaders.LOCATION, uri.toString());
	}
	
}
