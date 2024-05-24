package by.it_academy.jd2.votig.service.api.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HibStatDTO {
    private long id;

    private String stat;

    private long cnt;
}