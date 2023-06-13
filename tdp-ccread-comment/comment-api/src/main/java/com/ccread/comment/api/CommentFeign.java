package com.ccread.comment.api;


import com.ccread.common.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value="ccread-comment", fallback = CommentFeignFallback.class)
public interface CommentFeign {


    @GetMapping("/comment/book/getBookScoreByNovelId/{novelId}")
    R<Integer> getBookScoreByNovelId(@PathVariable("novelId") Long novelId);


    @GetMapping("/comment/book/getCommentCount/{novelId}")
    R<Integer> getCommentCount(@PathVariable("novelId") Long novelId);


}
