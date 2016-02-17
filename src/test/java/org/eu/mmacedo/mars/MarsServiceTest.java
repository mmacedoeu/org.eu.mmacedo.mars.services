package org.eu.mmacedo.mars;

import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.time.Instant;

import org.eu.mmacedo.mars.service.MarsService;
import org.junit.Test;

public class MarsServiceTest {

	@Test
	public void testGetOrientationLNCase() {
		char r = new MarsService().getOrientation(-1, 'N');
		assertTrue(r == 'W');
	}

	@Test
	public void testGetOrientationLWCase() {
		char r = new MarsService().getOrientation(-1, 'W');
		assertTrue(r == 'S');
	}

	@Test
	public void testGetOrientationLSCase() {
		char r = new MarsService().getOrientation(-1, 'S');
		assertTrue(r == 'E');
	}

	@Test
	public void testGetOrientationLECase() {
		char r = new MarsService().getOrientation(-1, 'E');
		assertTrue(r == 'N');
	}

	@Test
	public void testGetOrientationRNCase() {
		char r = new MarsService().getOrientation(1, 'N');
		assertTrue(r == 'E');
	}

	@Test
	public void testGetOrientationRECase() {
		char r = new MarsService().getOrientation(1, 'E');
		assertTrue(r == 'S');
	}

	@Test
	public void testGetOrientationRSCase() {
		char r = new MarsService().getOrientation(1, 'S');
		assertTrue(r == 'W');
	}

	@Test
	public void testGetOrientationRWCase() {
		char r = new MarsService().getOrientation(1, 'W');
		assertTrue(r == 'N');
	}

	@Test(expected = IllegalStateException.class)
	public void testMoveUXCase() {
		Object[] state = { 4, 1, 'E' };
		new MarsService().move(state);
	}

	@Test(expected = IllegalStateException.class)
	public void testMoveUYCase() {
		Object[] state = { 1, 4, 'N' };
		new MarsService().move(state);
	}

	@Test(expected = IllegalStateException.class)
	public void testMoveLXCase() {
		Object[] state = { 0, 1, 'W' };
		new MarsService().move(state);
	}

	@Test(expected = IllegalStateException.class)
	public void testMoveLYCase() {
		Object[] state = { 1, 0, 'S' };
		new MarsService().move(state);
	}

	@Test
	public void testMoveXWCase() {
		Instant start =  Instant.now();
		while (Duration.between(start, Instant.now()).getSeconds() < 2) {
			int x = java.util.concurrent.ThreadLocalRandom.current().nextInt(1, 5);
			int y = java.util.concurrent.ThreadLocalRandom.current().nextInt(0, 5);
			Object[] state = { x, y, 'W' };
			Object[] nstate = new MarsService().move(state);
			int nx = (int) nstate[0];
			assertTrue(nx == x - 1);
			assertTrue((int)nstate[1] == y);
			assertTrue((char)nstate[2] == 'W');			
		}
	}
	
	@Test
	public void testMoveXECase() {
		Instant start =  Instant.now();
		while (Duration.between(start, Instant.now()).getSeconds() < 2) {
			int x = java.util.concurrent.ThreadLocalRandom.current().nextInt(0, 4);
			int y = java.util.concurrent.ThreadLocalRandom.current().nextInt(0, 5);
			Object[] state = { x, y, 'E' };
			Object[] nstate = new MarsService().move(state);
			int nx = (int) nstate[0];
			assertTrue(nx == x + 1);
			assertTrue((int)nstate[1] == y);
			assertTrue((char)nstate[2] == 'E');
		}
	}	
	
	@Test
	public void testMoveYNCase() {
		Instant start =  Instant.now();
		while (Duration.between(start, Instant.now()).getSeconds() < 2) {
			int x = java.util.concurrent.ThreadLocalRandom.current().nextInt(0, 5);
			int y = java.util.concurrent.ThreadLocalRandom.current().nextInt(0, 4);
			Object[] state = { x, y, 'N' };
			Object[] nstate = new MarsService().move(state);
			int ny = (int) nstate[1];
			assertTrue(ny == y + 1);
			assertTrue((int)nstate[0] == x);
			assertTrue((char)nstate[2] == 'N');
		}
	}		
	
	@Test
	public void testMoveYSCase() {
		Instant start =  Instant.now();
		while (Duration.between(start, Instant.now()).getSeconds() < 2) {
			int x = java.util.concurrent.ThreadLocalRandom.current().nextInt(0, 5);
			int y = java.util.concurrent.ThreadLocalRandom.current().nextInt(1, 5);
			Object[] state = { x, y, 'S' };
			Object[] nstate = new MarsService().move(state);
			int ny = (int) nstate[1];
			assertTrue(ny == y - 1);
			assertTrue((int)nstate[0] == x);	
			assertTrue((char)nstate[2] == 'S');
		}
	}		

	@Test
	public void testRunCharObjectArrayMCase() {
		int x = java.util.concurrent.ThreadLocalRandom.current().nextInt(0, 5);
		int y = java.util.concurrent.ThreadLocalRandom.current().nextInt(1, 5);
		Object[] state = { x, y, 'S' };
		Object[] nstate = new MarsService().run('M', state);
		int ny = (int) nstate[1];
		assertTrue(ny == y - 1);
		assertTrue((int)nstate[0] == x);
		assertTrue((char)nstate[2] == 'S');
	}
	
	@Test
	public void testRunCharObjectArrayLCase() {
		int x = java.util.concurrent.ThreadLocalRandom.current().nextInt(0, 5);
		int y = java.util.concurrent.ThreadLocalRandom.current().nextInt(0, 5);
		Object[] state = { x, y, 'S' };
		Object[] nstate = new MarsService().run('L', state);
		assertTrue((int)nstate[1] == y);
		assertTrue((int)nstate[0] == x);	
		assertTrue((char)nstate[2] == 'E');
	}
	
	@Test
	public void testRunCharObjectArrayRCase() {
		int x = java.util.concurrent.ThreadLocalRandom.current().nextInt(0, 5);
		int y = java.util.concurrent.ThreadLocalRandom.current().nextInt(0, 5);
		Object[] state = { x, y, 'S' };
		Object[] nstate = new MarsService().run('R', state);
		assertTrue((int)nstate[1] == y);
		assertTrue((int)nstate[0] == x);	
		assertTrue((char)nstate[2] == 'W');
	}	
	
	@Test(expected = IllegalArgumentException.class)
	public void testRunCharObjectArrayInvalidCase() {
		Object[] state = { 1, 0, 'S' };
		new MarsService().run('J', state);
	}	

	@Test
	public void testRunStringCase1() {
		String nstate = new MarsService().run("MMRMMRMM");
		assertTrue("(2,0,S)".equals(nstate));		
	}
	
	@Test
	public void testRunStringCase2() {
		String nstate = new MarsService().run("MML");
		assertTrue("(0,2,W)".equals(nstate));		
	}	
	
	@Test
	public void testRunStringCase3() {
		String nstate = new MarsService().run("MML");
		assertTrue("(0,2,W)".equals(nstate));		
		nstate = new MarsService().run("MML");
		assertTrue("(0,2,W)".equals(nstate));		
	}		

	@Test(expected = IllegalArgumentException.class)
	public void testRunStringCase4() {
		new MarsService().run("AAA");	
	}		

	@Test(expected = IllegalStateException.class)
	public void testRunStringCase5() {
		new MarsService().run("MMMMMMMMMMMMMMMMMMMMMMMM");	
	}		
			
}
