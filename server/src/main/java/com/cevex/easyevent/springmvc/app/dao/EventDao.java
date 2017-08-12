package com.cevex.easyevent.springmvc.app.dao;

import com.cevex.easyevent.springmvc.app.dao.entity.EventEntity;
import com.cevex.easyevent.springmvc.share.framework.AbstractDao;
import org.springframework.stereotype.Repository;

@Repository
public class EventDao extends AbstractDao<Long, EventEntity> {

}
