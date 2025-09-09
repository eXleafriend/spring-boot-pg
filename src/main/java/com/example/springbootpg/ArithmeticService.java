package com.example.springbootpg;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ArithmeticService {

	public int sum(List<Integer> operands) {

		return operands.stream().mapToInt(Integer::intValue).sum();

	}

    public void nothing() {
    }

}
