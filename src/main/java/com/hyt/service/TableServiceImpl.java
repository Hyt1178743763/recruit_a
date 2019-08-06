package com.hyt.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyt.mapper.TablesMapper;
import com.hyt.model.Tables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TableServiceImpl{

    @Autowired
    private TablesMapper tablesMapper;

    public void addTable(Tables tables) {
        if (tables.getName()!=null&&tables.getName()!=""){
            tablesMapper.insert(tables);
        }

    }

    public List<Tables> findTables() {
        return tablesMapper.findAllTables();
    }

    public Tables findById(Integer id){
        Tables tables = tablesMapper.selectByPrimaryKey(id);
        return tables;
    }

}
