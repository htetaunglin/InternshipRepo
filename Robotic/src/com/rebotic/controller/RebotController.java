package com.rebotic.controller;

import com.rebotic.services.SayService;
import com.rebotic.services.SayServiceImpl;
import com.rebotic.util.NoAnswerFoundException;
import com.rebotic.util.TheUtils;

public class RebotController {

	private static SayService service;

	private static final String END_TEXT = "Bye";

	private static final String HELP_TXT = "HI I'M ROBOT ENGINEER, THIS IS GUIDE 4 U"
			+ "\n          | Use <<name>> to teach, your name will be printed."
			+ "\n          | Use keywords ('Change my name', 'change name', 'want to change my name') to change your name."
			+ "\n          | Use 'r-(question)' to remove question"
			+ "\n          | Use 'e-(question),(answer)' to edit question" + "\n          | Use '(Bye)' to end of play"
			+ "\n          | Use 'help' to help" + "\n          | Nice to meet you ;)";

	public RebotController() {
		service = SayServiceImpl.getInstance();
	}

	public void run() {
		System.out.println("======== WELCOME ========");

		requestName();
		programmicallyTeach();
		programmicallySupplierTeach();

		while (!chat(TheUtils.readString("You\t>> ")).equalsIgnoreCase(END_TEXT)) {
		}

		System.out.println("======= THANK YOU =======");
	}

	private String chat(String question) {
		try {
			// Answer
			System.out.println(service.answer(question).toString().trim());
		} catch (NoAnswerFoundException e) {
			// Teach
			service.teach(question, TheUtils.teachString(e.getMessage()));
		}
		return question;
	}

	private void requestName() {
		service.saveName(TheUtils.readString("Engineer>> What is Your Name? : "));
		System.out.println("Engineer>> Welcome " + service.getName() + ". Start chat to be happy :)");
	}

	private void programmicallyTeach() {
		service.teach("Hello", "Hi <<name>>");
		service.teach("What is your name?", "I'm Cuty robot ;)");
		service.teach("What is your name", "I'm Cuty robot ;)");
		service.teach("Who are you?", "I'm Cuty robot ;)");
		service.teach("Who are you", "I'm Cuty robot ;)");
		service.teach("<<name>>", "Your name is <<name>>");
		service.teach("Hi", "Hi <<name>>");
		service.teach("How are you?", "I'm fine, you?");
		service.teach("How are you", "I'm fine, you?");
		service.teach("I'm fine", "yeah");
		service.teach("I'm also fine", "yeah");
		service.teach("fine", "yeah");
		service.teach("I Love U", "I Love You too");
		service.teach("I Love you", "I Love You too");
		service.teach("Fuck You", "Hmm ;(");
		service.teach(END_TEXT, "Bye :3 ");

		service.sysTeach("help", HELP_TXT);
	}

	private void programmicallySupplierTeach() {
		service.sysTeach("Change My Name", RebotController::changeName);
		service.sysTeach("Change Name", RebotController::changeName);
		service.sysTeach("want to change my name", RebotController::changeName);
		service.sysTeach("change " + service.getName(), RebotController::changeName);
		service.sysTeach("to change my name", RebotController::changeName);
	}

	private static String changeName() {
		service.saveName(TheUtils.readString("Engineer>> Change your name : "));
		return "Successful change your name to '" + service.getName() + "'";
	}

}
