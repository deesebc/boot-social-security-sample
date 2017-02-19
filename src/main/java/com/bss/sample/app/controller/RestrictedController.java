package com.bss.sample.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RestrictedController {

	@GetMapping( value = "/restricted" )
	public String home( final Model model ) {
		return "site.restricted";
	}
}
