package GaBom.Bom.controller;

import GaBom.Bom.dto.CommentDto;
import GaBom.Bom.entity.Comment;
import GaBom.Bom.repository.CommentRepository;
import GaBom.Bom.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comment")
@RequiredArgsConstructor
@RestControllerAdvice
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{travelId}")
    public String writeComment(@RequestBody CommentDto commentDto, @PathVariable Long travelId) { //로그인세션에 맞춰서 Travel 쓰도록 하기
        if(commentService.CreateComment(commentDto,travelId)) return "Comment writed";
        return "Comment write fail";
    }

//    @GetMapping("/{travelId}")
//    public List<CommentDto>(@PathVariable Long travelId){
//
//    }

    @DeleteMapping("/{commentId}")
    String deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return "deleted";
    }

}
