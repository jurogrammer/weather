package juro.weather.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import jakarta.annotation.PostConstruct;

@Component
public class KWeatherClient {
	private WebClient webClient;
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

	@PostConstruct
	public void setup() {
		webClient = WebClient.builder()
			.baseUrl("http://data.kma.go.kr/stcs/grnd/grndTaAjaxList.do")
			.build();
	}

	public List<TemperatureResponse> getDailyTemperatures(LocalDate from, LocalDate to) {
		ParameterizedTypeReference<List<TemperatureResponse>> typeRef = new ParameterizedTypeReference<>() {
		};

		return webClient.post()
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.bodyValue(
				"fileType=&pgmNo=70&menuNo=432&serviceSe=F00101&stdrMg=99999"
					+ "&startDt=" + from.format(FORMATTER)
					+ "&endDt=" + to.format(FORMATTER)
					+ "&taElement=MIN&taElement=AVG&taElement=MAX&stnGroupSns=&selectType=1&mddlClssCd=SFC01&dataFormCd=F00501&dataTypeCd=standard&startDay=20230501&startYear=2023&endDay=20230516&endYear=2023&startMonth=01&endMonth=12&sesnCd=0&txtStnNm=%EC%84%9C%EC%9A%B8&stnId=108&areaId=")
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(typeRef)
			.block();
	}

}
