package com.cevex.easyevent.springmvc.share.framework;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic Mapper holding some classic mapping function.
 *
 * @param <MODEL>  POJO object representing Model Layer
 * @param <ENTITY> POJO object representing Enity Layer
 */
public abstract class AbstractMapper<MODEL, ENTITY> {

    private Class<ENTITY> entityClass;

    //=========================================================================
    //          DAO -> Model
    //=========================================================================

    /**
     * Map a list from Model to Entity
     *
     * @param entityList - List of Entity to map
     * @return List of model
     */
    protected List<MODEL> mapToModelList(List<ENTITY> entityList) {
        List<MODEL> modelList = new ArrayList<>();
        for (ENTITY entity : entityList) {
            modelList.add(this.mapToModel(entity));
        }
        return modelList;
    }

    /**
     * Map object from Model to Entity
     *
     * @param entity - Entity object to map
     * @return A new instance of model object
     */
    public abstract MODEL mapToModel(ENTITY entity);

    //=========================================================================
    //         Model -> Entity
    //=========================================================================

    /**
     * Map object from Entity to Model
     *
     * @param model - Model object to map
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