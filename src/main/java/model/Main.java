package model;

import gui.Login;
import gui.UserCreate;

public class Main {

	public static void main(String[] args) 	{
		App app = new App();
		new UserCreate(app);
	}
}
