package com.rebotic.services;

import java.util.function.Supplier;

public interface SayService {

	void teach(String question, String answer);

	void sysTeach(String question, Supplier<Object> supplier);

	void sysTeach(String question, String answer);

	Object answer(String question);

	void changeAnswer(String question, String answer);

	void remove(String question);

	void saveName(String name);

	String getName();

}
