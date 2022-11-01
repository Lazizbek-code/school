package uz.lazizbek.school.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {

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

    @NotNull(message = "Phone number not sent")
    private String phoneNumber;
}
