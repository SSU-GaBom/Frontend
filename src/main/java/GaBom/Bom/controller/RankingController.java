package GaBom.Bom.controller;

import GaBom.Bom.dto.RankingDto;
import GaBom.Bom.service.RankingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RequiredArgsConstructor
@RestControllerAdvice
@Transactional
@Slf4j
@RequestMapping("/api/rank")
public class RankingController {
    private final RankingService rankingService;

    @GetMapping("")
    public RankingDto showRank(){
        RankingDto rankingDto = rankingService.findRank();
        return rankingDto;
    }
}
