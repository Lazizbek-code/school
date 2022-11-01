package uz.lazizbek.school.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.lazizbek.school.template.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Time;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ClassSchedule extends AbstractEntity {

    @ManyToOne
    private Groups group;

    @ManyToOne
    private Subject subject;

    @JsonFormat(pattern = "HH:mm")
    @Column(nullable = false)
    private Time startTime;

    @JsonFormat(pattern = "HH:mm")
    @Column(nullable = false)
    private Time endTime;

    @ManyToOne
    private Room room;

    @Column(nullable = false)
    private String weekDay;

}
