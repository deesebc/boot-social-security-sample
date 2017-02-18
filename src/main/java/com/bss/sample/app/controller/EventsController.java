package com.bss.sample.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventsController {

	@GetMapping( value = "/events" )
	public String home( final Model model ) {
		return "site.events";
	}
}
