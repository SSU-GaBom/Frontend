package GaBom.Bom.dto;

import GaBom.Bom.entity.Comment;
import GaBom.Bom.entity.Travel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
public class CommentDto {

    private Long commentId;
    private String content;

//    private String userId;

    private String userNickname;

    public Comment toEntity(String Nickname){

        return Comment.builder()
                .commentId(commentId)
                .content(content)
                .userNickname(Nickname)
                .build();
    }
}
