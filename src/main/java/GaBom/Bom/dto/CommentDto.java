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

    private String userId;

    public Comment toEntity(String userId){
        return Comment.builder()
                .commentId(commentId)
                .content(content)
                .userId(userId)
                .build();
    }
}
