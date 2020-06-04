package com.boke.service;

import com.boke.NotFoundException;
import com.boke.mapper.TagRespority;
import com.boke.pojo.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl  implements  TagService{
    @Autowired
    private TagRespority tagRespority;

    @Override
    public Tag saveTag(Tag tag) {
        return tagRespority.save(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return tagRespority.getOne(id);
    }

    @Override
    public Page<Tag> ListTag(Pageable pageable) {
        return tagRespority.findAll(pageable);
    }

    @Override
    public List<Tag> listTag() {
        return tagRespority.findAll();
    }

    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t=tagRespority.findOne(id);
        if(t==null){
            throw new NotFoundException("该标签不存在");
        }
        BeanUtils.copyProperties(tag,t);
        return tagRespority.save(t);
    }

    @Override
    public void deleteTag(Long id) {
         tagRespority.delete(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return  tagRespority.findByName(name);
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort=new Sort(Sort.Direction.DESC,"blogs.size");
        Pageable pageable=new PageRequest(0,size,sort);
        return  tagRespority.findTop(pageable);
    }

    @Override
    public List<Tag> listTag(String ids) {

        return tagRespority.findAll(convertToList(ids));
    }

    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }



}
