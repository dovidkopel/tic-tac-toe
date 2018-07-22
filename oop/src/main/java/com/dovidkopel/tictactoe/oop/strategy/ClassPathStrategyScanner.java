package com.dovidkopel.tictactoe.oop.strategy;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class ClassPathStrategyScanner implements StrategyScanner {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private List<WinningStrategy> strategies = new ArrayList();
	private FastClasspathScanner scanner = new FastClasspathScanner();

	@PostConstruct
	protected void init() {
		scanner.matchClassesImplementing(WinningStrategy.class,
			i -> {
				try {
					strategies.add(i.newInstance());
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				}
			}
		).scan();
		logger.info("There are {} winning strategies", strategies.size());
	}

	@Override
	public List<WinningStrategy> getWinningStrategies() {
		return strategies;
	}
}
