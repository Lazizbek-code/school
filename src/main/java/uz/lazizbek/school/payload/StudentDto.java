package uz.lazizbek.school.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StudentDto {
    @NotNull(message = "FirstName not sent")
    private String firstName;

    @NotNull(message = "LastName not sent")
    private String lastName;

    @NotNull(message = "Patronymic not sent")
    private String patronymic;

    @NotNull(message = "City not sent")
    private String city;

    @NotNull(message = "Street not sent")
    private String street;

    @NotNull(message = "Home number not sent")
    private String homeNumber;

    @NotNull(message = "Group id not sent")
    private Long groupId;
}
