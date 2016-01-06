package com.dao;

import com.model.FaceBookPage;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
public class PagesDao {

    private SessionFactory sessionFactory;



    @Transactional
    public List<FaceBookPage> list() {
        List<FaceBookPage> pageList = (List<FaceBookPage>) sessionFactory.getCurrentSession()
                .createCriteria(FaceBookPage.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return pageList;
    }

    @Transactional
    public int save(FaceBookPage faceBookModel){
        Integer id = (Integer)sessionFactory.getCurrentSession().save(faceBookModel);
        return id;
    }


    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
