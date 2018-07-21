package br.com.buscape.subnav.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MoveSubService {

	@RequestMapping(value="/move/{actions}", method=RequestMethod.POST)
	public String moveSub(@PathVariable String actions) {
		return "";
	}

	@RequestMapping(value="/position", method=RequestMethod.GET)
	public String subPos() {
		return "";
	}
}
