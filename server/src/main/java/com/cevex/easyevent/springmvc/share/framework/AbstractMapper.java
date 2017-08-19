package com.cevex.easyevent.springmvc.share.framework;

import com.cevex.easyevent.springmvc.share.framework.model.ModelElement;
import com.cevex.easyevent.springmvc.share.framework.utils.GenericClassUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic Mapper holding some classic mapping function.
 *
 * @param <MODEL>  POJO object representing ModelElement Layer
 * @param <ENTITY> POJO object representing Enity Layer
 */
public abstract class AbstractMapper<MODEL extends ModelElement, ENTITY> {

    private Class<ENTITY> entityClass;

    @Autowired
    @SuppressWarnings("unchecked")
    public AbstractMapper() {
        this.entityClass = (Class<ENTITY>) GenericClassUtils.getParameterClass(this.getClass(), 1);
    }

    //=========================================================================
    //          DAO -> ModelElement
    //=========================================================================

    /**
     * Map a list from ModelElement to Entity
     *
     * @param entityList - List of Entity to map
     * @return List of model
     */
    public List<MODEL> mapToModelList(List<ENTITY> entityList) {
        List<MODEL> modelList = new ArrayList<>();
        for (ENTITY entity : entityList) {
            modelList.add(this.mapToModel(entity));
        }
        return modelList;
    }

    /**
     * Map object from ModelElement to Entity
     *
     * @param entity - Entity object to map
     * @return A new instance of model object
     */
    public abstract MODEL mapToModel(ENTITY entity);

    //=========================================================================
    //         ModelElement -> Entity
    //=========================================================================

    /**
     * Map object from Entity to ModelElement
     *
     * @param model - ModelElement object to map
     * @return A new instance of entity object
     */
    public ENTITY mapToEntity(MODEL model) {
        //Get entity instance
        ENTITY entity = null;
        try {
            entity = this.entityClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        //Update data
        updateEntity(entity, model);
        return entity;
    }

    /**
     * Update the given entity with model values.
     * <p>Usefull with hibernate (same reference)</p>
     *
     * @param entity - The entity to update
     * @param model  - The model to copy
     */
    public abstract void updateEntity(ENTITY entity, MODEL model);
}