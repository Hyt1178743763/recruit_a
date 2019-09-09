package com.hyt;

import static org.junit.Assert.assertTrue;

import com.hyt.model.Tables;
import com.hyt.service.TableServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Autowired
    TableServiceImpl tableService;

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {

        //查出所有表，看看有没有注册过
        List<Tables> tables1 = tableService.findTables();
        System.out.println(tables1);
    }

}
