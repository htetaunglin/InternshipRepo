package com.rebotic.repo;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.rebotic.util.NoAnswerFoundException;

public class SayRepositoryImpl implements SayRepository {

	private Map<String, String> systemMap;
	private Map<String, String> robotMap;
	private Map<String, Supplier<Object>> supplierMap;

	private static SayRepository repo;

	private String name;

	private SayRepositoryImpl() {

		supplierMap = new HashMap<>();
		robotMap = new HashMap<>();
		systemMap = new HashMap<>();

	}

	public static SayRepository getInstance() {
		if (repo == null)
			repo = new SayRepositoryImpl();
		return repo;
	}

	@Override
	public Object answer(String question) {
		String key = question.toLowerCase();
		if (key.startsWith("r-"))
			return remove(key.replace("r-", "").trim());

		if (key.startsWith("e-")) {
			question = question.replace("e-", "").replace("E-", "").trim();
			String qus = question.split(",")[0];
			String ans = question.split(",")[1];
			return changeAnswer(qus, ans);
		}

		if (systemMap.containsKey(key))
			return "Engineer>> " + systemMap.get(key);

		if (supplierMap.containsKey(key))
			return "Engineer>> " + supplierMap.get(key).get();

		if (robotMap.containsKey(key))
			return "Robot\t>> " + robotMap.get(key).replace("<<name>>", this.getName());

		throw new NoAnswerFoundException("No Answer for these question. Please teach me (Type 'cancel' to cancel): ");
	}

	@Override
	public void sysTeach(String question, Supplier<Object> supplier) {
		supplierMap.put(question.toLowerCase(), supplier);
	}

	@Override
	public Object changeAnswer(String question, String answer) {
		String q = question.toLowerCase();
		if (!answer.trim().equalsIgnoreCase("cancel"))
			if (robotMap.containsKey(q)) {
				robotMap.replace(q, answer);
			} else {
				robotMap.put(q, answer);
			}

		return "Engineer>> Successful edited question";
	}

	@Override
	public Object remove(String question) {
		if (robotMap.containsKey(question))
			robotMap.remove(question);
		return "Engineer>> Remove Question '" + question + "'";
	}

	@Override
	public void sysTeach(String question, String answer) {
		systemMap.put(question.toLowerCase(), answer);

	}

	@Override
	public void saveName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

}
