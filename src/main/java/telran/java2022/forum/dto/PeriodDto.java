package telran.java2022.forum.dto;

import java.time.LocalDate;

//import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PeriodDto {

//	@JsonFormat(pattern = "yyyy-MM-dd")
	LocalDate dateFrom;
	LocalDate dateTo;
}
