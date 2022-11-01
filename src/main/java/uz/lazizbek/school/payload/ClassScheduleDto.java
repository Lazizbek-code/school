package uz.lazizbek.school.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ClassScheduleDto {

    @NotNull(message = "GroupId not sent")
    private Long groupId;

    @NotNull(message = "SubjectId not sent")
    private Long subjectId;

    @NotNull(message = "startTime not sent")
    private String startTime;

    @NotNull(message = "endTime not sent")
    private String endTime;

    @NotNull(message = "roomId not sent")
    private Long roomId;

    @NotNull(message = "weekDay not sent")
    private String weekDay;
}
