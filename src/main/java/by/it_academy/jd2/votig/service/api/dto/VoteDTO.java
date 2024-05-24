package by.it_academy.jd2.votig.service.api.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VoteDTO {
    private Long artist;
    private Long[] genres;
    private String about;
}
