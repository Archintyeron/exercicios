package br.com.buscape.subnav;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.buscape.subnav.web.MoveSubService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SubnavApplicationTests {

	@Autowired
	private MoveSubService moveSubService;

	@Test
	public void testInvalidCommand() {

		String actions = "InvalidAction";
		String returns = moveSubService.moveSub(actions);

		assertEquals("Invalid Command", returns);
	}

	@Test
	public void testUpperLimit() {

		String actions = "UUU";
		String returns = moveSubService.moveSub(actions);

		String[] coords = returns.split(" ");
		assertEquals(Integer.valueOf(0), Integer.valueOf(coords[2]));
	}

	@Test
	public void testMoveSub() {

		String actions = "RMMLMMMDDLL";
		String returns = moveSubService.moveSub(actions);

		assertEquals("2 3 -2 SUL", returns);
	}

}
