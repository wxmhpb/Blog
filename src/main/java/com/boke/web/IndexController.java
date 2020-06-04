package com.boke.web;

import com.boke.service.BlogsService;
import com.boke.service.TagService;
import com.boke.service.TypeService;
import com.boke.service.TypeServiceImpl;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Action;

@Controller
public class IndexController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogsService blogsService;

    @GetMapping("/")
    public String index(@PageableDefault(size=8,sort={"updateTime"},direction = Sort.Direction.DESC)Pageable pageable,
                       Model model ){
        model.addAttribute("page",blogsService.listBlog(pageable));
        model.addAttribute("types",typeService.findTop(6));
        model.addAttribute("tags",tagService.listTagTop(10));
        model.addAttribute("recommendBlog",blogsService.listRecommendBlogTop(8));

        return "index";
    }

    @PostMapping("/search")
    public String search(@PageableDefault(size=8,sort={"updateTime"},direction = Sort.Direction.DESC)Pageable pageable,
                         Model model, @RequestParam String query){
        model.addAttribute("page",blogsService.listBlog("%"+query+"%",pageable));
        model.addAttribute("query",query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model){
        model.addAttribute("blog", blogsService.getAndConvert(id));
        return "blog";
    }
}
