package com.rebotic.repo;

import java.util.function.Supplier;

public interface SayRepository {

	void sysTeach(String question, Supplier<Object> supplier);
	
	void sysTeach(String question, String answer);

	Object answer(String question);

	Object changeAnswer(String question, String answer);

	Object remove(String question);

	void saveName(String name);

	String getName();

}
