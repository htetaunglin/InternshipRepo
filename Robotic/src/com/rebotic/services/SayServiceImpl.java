package com.rebotic.services;

import java.util.function.Supplier;

import com.rebotic.repo.SayRepository;
import com.rebotic.repo.SayRepositoryImpl;

public class SayServiceImpl implements SayService {

	private static SayService service;
	
	private static SayRepository repo;

	private SayServiceImpl() {
		repo = SayRepositoryImpl.getInstance();
	}

	public static SayService getInstance() {
		if (service == null)
			service = new SayServiceImpl();
		return service;
	}


	@Override
	public Object answer(String question) {
		return repo.answer(question);
	}

	@Override
	public void teach(String question, String answer) {
		repo.changeAnswer(question, answer);
	}

	@Override
	public void sysTeach(String question, Supplier<Object> supplier) {
		repo.sysTeach(question, supplier);
	}

	@Override
	public void sysTeach(String question, String answer) {
		repo.sysTeach(question, answer);
	}

	@Override
	public void changeAnswer(String question, String answer) {
		repo.changeAnswer(question, answer);
	}

	@Override
	public void remove(String question) {
		repo.remove(question);
	}

	@Override
	public void saveName(String name) {
		repo.saveName(name);
	}

	@Override
	public String getName() {
		return repo.getName();
	}

}
