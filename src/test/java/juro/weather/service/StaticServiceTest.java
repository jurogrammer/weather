package juro.weather.service;

import java.time.LocalDate;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StaticServiceTest {

	StaticService staticService;

	@BeforeEach
	void beforeEach() {
		KWeatherClient kWeatherClient = new KWeatherClient();
		kWeatherClient.setup();
		staticService = new StaticService(kWeatherClient);
	}

	@Test
	void test() {
		long l = staticService.countGreaterThanTemperature(20, LocalDate.of(2023, 5, 1), LocalDate.of(2023, 5, 16));
		System.out.println("l = " + l);
	}

	@Test
	void 지난12년간_5월1일에서_부터_현재일자까지_몇번을_특정_온도를_넘었는지_확인() {
		Map<String, Long> stringLongMap = staticService.exceedTemperatureFrequencyFrom2012To2023InMay(27);
		System.out.println("stringLongMap = " + stringLongMap);
	}

}
