package com.alfauz.orderme.entity;

import com.alfauz.orderme.enumeration.RoleName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles", schema = "om", uniqueConstraints = {
        @UniqueConstraint(name = "role_name_uk", columnNames = {"name"})
})
public class RoleEntity extends Auditable<Long> {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Enumerated(EnumType.STRING)
  @NaturalId
  @Column(length = 60)
  private RoleName name;
}