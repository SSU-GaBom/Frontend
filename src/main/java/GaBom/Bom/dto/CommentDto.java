package GaBom.Bom.dto;

import GaBom.Bom.entity.Comment;
import GaBom.Bom.entity.Travel;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
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

    public CommentDto(Long commentId, String content, String nickname, String updateDate, byte[] profileImage, boolean isMyComment) {
        this.commentId = commentId;
        this.content = content;
        Nickname = nickname;
        this.updateDate = updateDate;
        this.profileImage = profileImage;
        IsMyComment = isMyComment;
    }


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
