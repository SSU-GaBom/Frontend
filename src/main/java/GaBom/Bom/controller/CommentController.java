package GaBom.Bom.controller;

import GaBom.Bom.dto.CommentDto;
import GaBom.Bom.dto.GetTravelDto;
import GaBom.Bom.entity.Comment;
import GaBom.Bom.repository.CommentRepository;
import GaBom.Bom.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/travel/comment")
@RequiredArgsConstructor
@RestControllerAdvice
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{travelId}")
    public String writeComment(@RequestBody CommentDto commentDto, @PathVariable Long travelId) throws IOException { //로그인세션에 맞춰서 Travel 쓰도록 하기
        if(commentService.CreateComment(commentDto,travelId)) return "Comment writed";
        return "Comment write fail";
    }


    @GetMapping("/{travelId}")
    public Page<CommentDto> CommentsList(
            @PageableDefault(size = 7, sort = "updateDate", direction = Sort.Direction.DESC) Pageable pageable, @PathVariable Long travelId) throws IOException {
        Page<CommentDto> travels = commentService.GetComments(travelId,pageable);
        return travels;
    }

    @PostMapping("/delete/{commentId}")
    String deleteComment(@PathVariable Long commentId) {
        if(commentService.deleteComment(commentId))
        return "comment delete success";
        else{
            return "comment delete fail";
        }
    }

}
