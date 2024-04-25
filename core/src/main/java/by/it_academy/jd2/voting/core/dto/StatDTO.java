package by.it_academy.jd2.voting.core.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatDTO {
    private long id;

    private String stat;

    private long cnt;
}
