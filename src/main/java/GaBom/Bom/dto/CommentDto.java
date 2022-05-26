package GaBom.Bom.dto;

import GaBom.Bom.entity.Comment;
import GaBom.Bom.entity.Travel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {

    private Long commentId;
    private String content;

//    private String userId;
    private String Nickname;
    private String updateDate;

//    private byte[] profileImage;
    private byte[] profileImage;

    private boolean IsMyComment;
    public CommentDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.content = comment.getContent();
        this.updateDate = comment.getUpdateDate();
        this.Nickname= comment.getNickname();
        this.updateDate=comment.getUpdateDate();
//        this.profileImage= comment.getProfileImage();
    }

    public Comment toEntity(){

        return Comment.builder()
                .commentId(commentId)
                .content(content)
                .updateDate(updateDate)
                .Nickname(Nickname)
//                .profileImage(profileImage)
                .build();
    }


}
