package com.rest;

import com.dao.PagesDao;
import com.model.FaceBookPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */

@RestController
public class PageController {

    @Autowired
    private PagesDao pagesDao;

    long started = System.currentTimeMillis();

    @RequestMapping(value="/pages/fb", method= RequestMethod.GET)
    public  Map<String, Object> registerDevice() {
        List<FaceBookPage> pageList =  pagesDao.list();

        Map<String, Object> map = new HashMap<>();
        map.put("status", HttpStatus.OK);
        map.put("result", pageList);
        return map;
    }

    @RequestMapping(value="/status", method= RequestMethod.GET)
    public  Map<String, Object> status() {

        Map<String, Object> map = new HashMap<>();
        map.put("status", HttpStatus.OK);
        map.put("started", started);
        map.put("uptime", System.currentTimeMillis() - started);
        map.put("version", "0.1" );

        return map;
    }

    public void setPagesDao(PagesDao pagesDao) {
        this.pagesDao = pagesDao;
    }
}
