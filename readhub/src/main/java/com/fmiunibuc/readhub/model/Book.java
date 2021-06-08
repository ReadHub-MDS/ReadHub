package com.fmiunibuc.readhub.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="Books")
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String author;
    @NonNull
    private int pages;
    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JsonIgnoreProperties("bookType")
    private List<BookCopy> copyList;
}
