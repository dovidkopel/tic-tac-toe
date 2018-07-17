package com.dovidkopel.tictactoe.oop;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.matchprocessor.ImplementingClassMatchProcessor;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicTacToe {
	List<WinningStrategy> winningStrategies = new ArrayList();

	FastClasspathScanner scanner = new FastClasspathScanner();

	Logger logger = LoggerFactory.getLogger(getClass());

	@PostConstruct
	public void init() {
		logger.info("Started..");
		scanner.matchClassesImplementing(WinningStrategy.class,
			i -> {
				try {
					winningStrategies.add(i.newInstance());
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				}
			}
		).scan();
		logger.info("There are {} winning strategies", winningStrategies.size());
	}
}
