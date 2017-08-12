package com.cevex.easyevent.springmvc.share.framework;

import com.cevex.easyevent.springmvc.share.framework.error.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractService<MODEL, ENTITY> {

    //=========================================================================
    //          Attributes
    //=========================================================================

    /**
     * Allowed to map data from database to model object
     */
    protected AbstractMapper<MODEL, ENTITY> mapper;

    /**
     * Allowed to access database entries
     */
    protected AbstractDao<Long, ENTITY> dao;

    //=========================================================================
    //          Constructor
    //=========================================================================

    /**
     * Default constructor
     */
    @Autowired
    public AbstractService(AbstractDao<Long, ENTITY> dao, AbstractMapper<MODEL, ENTITY> mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    //=========================================================================
    //          Control
    //=========================================================================

    /**
     * Define if an element exist
     *
     * @param id - Identifier of the element to check
     * @return true if exists, false otherwise
     */
    protected boolean isExist(Long id) {
        return id != null && this.findEntity(id) != null;
    }

    //=========================================================================
    //          Retrieve
    //=========================================================================

    /**
     * Retrieve an element by identifier
     *
     * @return All the Element
     */
    public MODEL get(long id) {
        ENTITY entity = findEntity(id);
        return mapper.mapToModel(entity);
    }

    /**
     * Retrieve all elements.
     *
     * @return All the Element
     */
    public List<MODEL> getAll() {
        List<ENTITY> entityList = dao.findAll();
        return mapper.mapToModelList(entityList);
    }

    /**
     * Retrieve an element by identifier
     *
     * @return The entity if found
     * @throws NotFoundException - If not found
     */
    private ENTITY findEntity(long id) {
        ENTITY entity = dao.find(id);
        if (entity == null) {
            throw new NotFoundException("Expense: id=" + id + " doesn't exist");
        }
        return entity;
    }

    //=========================================================================
    //          Create
    //=========================================================================

    /**
     * Create a new element
     *
     * @param model - Element to create
     */
    public void create(MODEL model) {
        dao.save(mapper.mapToEntity(model));
    }

    //=========================================================================
    //          Update
    //=========================================================================


    /**
     * Update an existing element
     *
     * @param model - Element to create
     */
    public MODEL update(long id, MODEL model) {
        ENTITY entity = this.findEntity(id);
        mapper.updateEntity(entity, model);
        return model;
    }

    //=========================================================================
    //          Delete
    //=========================================================================

    /**
     * Delete an element
     */
    public void delete(long id) {
        ENTITY entity = this.findEntity(id);
        dao.delete(entity);
    }
}
