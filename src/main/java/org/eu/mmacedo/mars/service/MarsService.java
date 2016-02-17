package org.eu.mmacedo.mars.service;

public class MarsService {

	private static final Object[] initialState = {0,0,'N'};
	private static final String orientation = "WNES";
	private static final int terrain = 5;
	
	public String formatState(Object[] state) {
		return "(" + state[0] + "," + state[1] + "," + state[2] + ")";
	}
	
	public char getOrientation(int offset, char current) {
		int pos = orientation.indexOf(current);
		if (pos == 0 && offset < 0)
			return orientation.charAt(3);
		if (pos == 3 && offset > 0)
			return orientation.charAt(0);
		
		return orientation.charAt(pos + offset);
	}
	
	public Object[] move(Object[] state) {

		char o = (char) state[2];
		
		switch (o) {
		case 'W': {
			int x = (int) state[0];
			x--;
			if (x < 0)
				throw new IllegalStateException();
			state[0] = x;
			return state;
		}
		
		case 'E': {
			int x = (int) state[0];
			x++;
			if (x > terrain -1)
				throw new IllegalStateException();
			state[0] = x;
			return state;
		}
		
		case 'N': {
			int y = (int) state[1];
			y++;
			if (y > terrain -1)
				throw new IllegalStateException();
			state[1] = y;
			return state;
		}	
		
		case 'S': {
			int y = (int) state[1];
			y--;
			if (y < 0)
				throw new IllegalStateException();
			state[1] = y;
			return state;
		}		
		
		default:
			throw new IllegalStateException();
		}
	}
		
	public Object[] run(char command, Object[] state) {
		switch (command) {
		case 'M':
			return move(state);
		case 'L': {
			char currentOrientation = (char)state[2];
			state[2] = getOrientation(-1, currentOrientation);
			return state;
		}
		case 'R': { 
			char currentOrientation = (char)state[2];
			state[2] = getOrientation(1, currentOrientation);
			return state;
		}
			
		default:
			throw new IllegalArgumentException();
		}
	}
	
	public String run(String command) {
		if (command == null || command.trim().isEmpty()) {
			throw new IllegalArgumentException();
		}
		
		int size = command.trim().length();
		String cmd = command.trim();
		Object[] state = initialState.clone();
		for (int i = 0; i < size; i++) {
			//System.out.println(formatState(state));
			state = run(cmd.charAt(i), state);
		}
	
		return formatState(state);				
	}
}
