package br.com.buscape.subnav.web;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MoveSubService {

	private Object[] position = null;

	@RequestMapping(value="/move", method=RequestMethod.POST)
	public String moveSub(@RequestParam String actions) {

		String returns = validateAction(actions);
		if (!StringUtils.isEmpty(returns)) {
			return returns;
		}
		return subPos();
	}

	@RequestMapping(value="/reset", method=RequestMethod.POST)
	public String resetPos() {

		position = new Object[]{0, 0, 0, "NORTE"};
		return subPos();
	}

	@RequestMapping(value="/position", method=RequestMethod.GET)
	public String subPos() {
		
		if (position == null) {
			position = new Object[]{0, 0, 0, "NORTE"};
		}

		return "" + position[0] + " " + position[1] + " " + position[2] + " " + position[3];
	}

	private String validateAction (String actions) {

		if (position == null) {
			position = new Object[]{0, 0, 0, "NORTE"};
		}

		if (actions != null) {
			for (char action : actions.toUpperCase().toCharArray()) {
				switch (action) {
					case 'L':
						turnLeft();
						break;
					case 'R':
						turnRight();
						break;
					case 'U':
						moveUp();
						break;
					case 'D':
						moveDown();
						break;
					case 'M':
						moveForward();
						break;
					default:
						return "Invalid Command";
				}
			}
		}
		return "";
	}

	private void turnLeft() {

		switch ((String) position[3]) {
			case "NORTE":
				position[3] = "OESTE";
				break;
			case "SUL":
				position[3] = "LESTE";
				break;
			case "LESTE":
				position[3] = "NORTE";
				break;
			case "OESTE":
				position[3] = "SUL";
				break;
		}
	}
	
	private void turnRight() {

		switch ((String) position[3]) {
			case "NORTE":
				position[3] = "LESTE";
				break;
			case "SUL":
				position[3] = "OESTE";
				break;
			case "LESTE":
				position[3] = "SUL";
				break;
			case "OESTE":
				position[3] = "NORTE";
				break;
		}
	}

	private void moveUp() {
		if ((Integer) position[2] < 0) {
			position[2] = (Integer) position[2] + 1;
		}
	}
	
	private void moveDown() {
		position[2] = (Integer) position[2] - 1;
	}

	private void moveForward() {

		switch ((String) position[3]) {
			case "NORTE":
				position[1] = (Integer) position[1] + 1;
				break;
			case "SUL":
				position[1] = (Integer) position[1] - 1;
				break;
			case "LESTE":
				position[0] = (Integer) position[0] + 1;
				break;
			case "OESTE":
				position[0] = (Integer) position[0] - 1;
				break;
		}
	}

}
