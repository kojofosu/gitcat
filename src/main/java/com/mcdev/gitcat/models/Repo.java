package com.mcdev.gitcat.models;

import java.util.List;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Repo {
  @Id
  @Column(unique = true, updatable = false)
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
  private String id;

  @Column private String author, url, description;

  //    @Column
  //    @ElementCollection(targetClass = String.class)
  //    private List<String> tags;
}
