package uz.lazizbek.school.payload;

import lombok.Data;
import java.util.Set;

@Data
public class TeacherSubjectDto {
    private Long teacherId;
    private Set<Long> subjectId;
}
