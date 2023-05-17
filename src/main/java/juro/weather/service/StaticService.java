package juro.weather.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StaticService {
	private final KWeatherClient kWeatherClient;

	public long countGreaterThanTemperature(
		double baseTemperature,
		LocalDate from,
		LocalDate to
	) {
		return kWeatherClient.getDailyTemperatures(from, to).stream()
			.map(TemperatureResponse::getMaximumTemperature)
			.filter(maximumTemperature -> baseTemperature <= maximumTemperature)
			.count();
	}

	public Map<String, Long> exceedTemperatureFrequencyFrom2012To2023InMay(double temperature) {
		DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy");
		LocalDate from = LocalDate.of(2012, 5, 1);
		LocalDate to = LocalDate.of(2012, 5, LocalDate.now().getDayOfMonth());

		Map<String, Long> countMap = new TreeMap<>();
		for (int i = 0; i < 12; i++) {
			long exceedCount = countGreaterThanTemperature(temperature, from, to);
			String year = from.format(FORMATTER);
			countMap.put(year, exceedCount);

			from = from.plusYears(1);
			to = to.plusYears(1);
		}

		return countMap;
	}
}
