package co.kr.datapia.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "pictures")
@Table(name="tb_board")
public class Board {
    @Id
    @GeneratedValue
    private Integer idx;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="user_idx")
    private User user;

    @Setter
    private String reportedDate;

    @Setter
    @NotEmpty
    private String content;

    @Setter
    @OneToMany(mappedBy="board")
    @JsonManagedReference
    private List<BoardPicture> pictures;
}
