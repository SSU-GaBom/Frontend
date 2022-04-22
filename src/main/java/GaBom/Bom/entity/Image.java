package GaBom.Bom.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imagId;
    @NotNull
    private Integer boardIdx;
    @NotEmpty
    private String original_file_name;
    @NotEmpty
    private String stored_file_path;

    private long file_size;

    //프로필 이미지로 사용할 때 사용하는 속성
    @OneToOne
    @JoinColumn(name = "user_no")
    private User user;

    //게시글 올릴 때 사용하는 속성
    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;
}