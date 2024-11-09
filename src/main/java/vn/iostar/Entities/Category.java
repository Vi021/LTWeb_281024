package vn.iostar.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="CATEGORIES")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="categoryName", columnDefinition="NVARCHAR(255)")
    private String categoryName;

    @Column(name="categoryImage", length=500, columnDefinition="NVARCHAR(500)")
    private String categoryImage;
}
