package com.ccread.bookeack.controller;

import com.ccread.bookeack.service.CollectionService;
import com.ccread.bookeack.service.PurchaseService;
import com.ccread.common.result.R;
import com.ccread.common.vo.CollectionListVo;
import com.ccread.common.vo.PurchaseListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookeack")
@RequiredArgsConstructor
public class BookeackController {
    private final CollectionService collectionService;

    private final PurchaseService purchaseService;



    /**
     * 查看收藏图书
     */
    @GetMapping("/getCollectionList")
    public R<List<CollectionListVo>> getCollectionList(){
        return collectionService.getCollectionList();
    }


    /**
     * 查看购买图书
     */
    @GetMapping("/getPurchaseList")
    public R<List<PurchaseListVo>> getPurchaseList(){
        return purchaseService.getPurchaseList();
    }

    /**
     * 判断购买
     */
    @GetMapping("/getPirchaseFalg/{novelId}")
    public R<Boolean> getPirchaseFalg(@PathVariable("novelId") Long novelId){
        return purchaseService.getPirchaseFalg(novelId);
    }



    /**
     * 添加购买记录
     */
    @PostMapping("createPurchase")
    public R createPurchase(@RequestParam Long novelId){
        return purchaseService.createPurchase(novelId);
    }

    /**
     * 判断收藏
     */
    @GetMapping("/getCollectionFalg/{novelId}")
    public R<Boolean> getCollectionFalg(@PathVariable("novelId") Long novelId){
        return collectionService.getCollectionFalg(novelId);
    }

    /**
     * 添加收藏
     */
    @PostMapping("/addCollection/{novelId}")
    public R addCollection(@PathVariable("novelId") Long novelId){
        return collectionService.addCollection(novelId);
    }

    /**
     * 取消收藏
     */
    @PutMapping("/upCollection/{novelId}")
    public R upCollection(@PathVariable("novelId") Long novelId){
        return collectionService.upCollection(novelId);
    }
}
