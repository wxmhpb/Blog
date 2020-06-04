package com.boke.service;

import com.boke.NotFoundException;
import com.boke.mapper.TypeRespority;
import com.boke.pojo.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRespority typeRespority;

    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRespority.save(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeRespority.findOne(id);
    }

    @Transactional
    @Override
    public Page<Type> ListType(Pageable pageable) {
        return typeRespority.findAll(pageable);
    }

    @Override
    public Type findByName(String name) {
        return typeRespority.findByName(name);
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type t = typeRespority.findOne(id);
        if (t == null) {
            throw new NotFoundException("该分类不存在");
        }
        BeanUtils.copyProperties(type, t);
        return typeRespority.save(t);
    }

    @Override
    public void deleteType(Long id) {
        typeRespority.delete(id);
    }

    @Override
    public List<Type> listType() {
        return typeRespority.findAll();
    }

    @Override
    public List<Type> findTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = new PageRequest(0,size,sort);
        return typeRespority.findTop(pageable);
    }
}
