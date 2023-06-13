package com.ccread.comment.controller;


import com.ccread.comment.service.CommentService;
import com.ccread.common.dto.CommentAddDto;
import com.ccread.common.dto.NovelScoreDto;
import com.ccread.common.result.R;
import com.ccread.common.vo.UserCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 评论列表
     */
    @GetMapping("/book/getCommentList/{novelId}")
    public R<List> getCommentList(@PathVariable("novelId") Long novelId){
        return commentService.getCommentList(novelId);
    }

    /**
     * 评分
     */
    @GetMapping("/book/getBookScoreByNovelId/{novelId}")
    public R<Integer> getBookScoreByNovelId(@PathVariable("novelId") Long novelId){
        return commentService.getBookScoreByNovelId(novelId);
    }

    /**
     * 评论条数
     */
    @GetMapping("/book/getCommentCount/{novelId}")
    public R<Integer> getCommentCount(@PathVariable("novelId") Long novelId){
        return commentService.getCommentCount(novelId);
    }

    /**
     * 添加评论
     */
    @PostMapping("/userAddComment")
    public R userAddComment(@RequestBody CommentAddDto commentAddDto){
        return commentService.userAddComment(commentAddDto);
    }

    /**
     * 个人评论
     */
    @GetMapping("/getUserComment")
    public R<List<UserCommentVO>> getUserComment(){
        return commentService.getUserComment();
    }

    /**
     * 批量查询评分
     */


}
