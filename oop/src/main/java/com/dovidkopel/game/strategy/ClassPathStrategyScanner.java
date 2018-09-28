package com.dovidkopel.game.strategy;

import io.github.classgraph.ClassGraph;
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
	private ClassGraph classGraph = new ClassGraph();

	@PostConstruct
	protected void init() {
		classGraph.scan()
			.getClassesImplementing(WinningStrategy.class.getName())
			.getStandardClasses()
			.forEach(c -> {
				try {
					Class<WinningStrategy> cs = (Class<WinningStrategy>) Class.forName(c.getName()).newInstance();
					strategies.add(cs.newInstance());
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			});
		logger.info("There are {} winning strategies", strategies.size());
	}

	@Override
	public List<WinningStrategy> getWinningStrategies() {
		return strategies;
	}
}
