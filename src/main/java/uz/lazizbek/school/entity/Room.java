package uz.lazizbek.school.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.lazizbek.school.template.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Room extends AbstractEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Boolean active = true;
}
