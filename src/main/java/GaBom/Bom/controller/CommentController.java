package GaBom.Bom.controller;

import GaBom.Bom.entity.Comment;
import GaBom.Bom.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
@RestControllerAdvice
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{travelId}")
    public String writeTravel(@RequestBody Comment comment, @PathVariable Long travelId) { //로그인세션에 맞춰서 Travel 쓰도록 하기
        if(commentService.CreateComment(comment,travelId)){
            return "write";
        }
        return "false";
    }
}
