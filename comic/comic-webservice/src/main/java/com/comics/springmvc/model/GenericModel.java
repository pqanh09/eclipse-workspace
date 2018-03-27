package com.comics.springmvc.model;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonAnySetter;

/**
 * The Class GenericModel.
 */
public class GenericModel<T> implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -2113871216746652571L;

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericModel.class);
    
    /**
     * Constant for attribute 'instanceid', used for custom mongo operation
     */
    public static final String ATTR_INSTANCE_ID = "_id";
    
    public static final String ATTR_INSTANCE_ID_NAME = "instanceid";
    
    /**
     * Constant for attribute 'createDate', used for custom mongo operation
     */
    public static final String ATTR_CREATE_DATE = "createDate";

    /**
     * Constant for attribute 'modifiedDate', used for custom mongo operation
     */
    public static final String ATTR_MODIFIED_DATE = "modifiedDate";
    
    public static final String NATURAL_SORTABLE_MAP = "naturalSortableMap";
    
//    @JsonIgnore
//    protected String strInstanceid;

    /**
     * The m_instanceid.
     */
    @Id
    protected T instanceid;
    
    /**
     * The m_create date.
     */
    protected Date createDate;
    
    /**
     * The m_modified date.
     */
    protected Date modifiedDate;
    
    /**
     * The m_last updated by.
     */
    @Field(value="lastUpdatedBy")
    protected String lastUpdatedBy;
    
    /**
     * Instantiates a new generic model.
     *
     * @param instanceid the instanceid
     * @param createDate the create date
     * @param modifiedDate the modified date
     * @param lastUpdatedBy the last updated by
     */
    public GenericModel(T instanceid, Date createDate, Date modifiedDate, String lastUpdatedBy) {
        this.instanceid = instanceid;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Instantiates a new generic model.
     */
    public GenericModel() {

    }

    /**
     * Gets the instanceid.
     *
     * @return the instanceid
     */
    public T getInstanceid() {
        return instanceid;
    }

    /**
     * Sets the instanceid.
     *
     * @param instanceid the new instanceid
     */
    public void setInstanceid(T instanceid) {
        this.instanceid = instanceid;
    }

    /**
     * Gets the creates the date.
     *
     * @return the creates the date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Sets the creates the date.
     *
     * @param createDate the new creates the date
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets the modified date.
     *
     * @return the modified date
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Sets the modified date.
     *
     * @param modifiedDate the new modified date
     */
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * Gets the last updated by.
     *
     * @return the last updated by
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Sets the last updated by.
     *
     * @param lastUpdatedBy the new last updated by
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
    
    @JsonAnySetter
    public void handleUnknown(String key, Object value) { 
        String warning = MessageFormat.format("Field {0} with value {1} from class {2} is missing a setter.",key, value, getClass());
        LOGGER.trace(warning);
    }

//    public String getStrInstanceid() {
//        return strInstanceid;
//    }
//
//    public void setStrInstanceid(String strInstanceid) {
//        this.strInstanceid = strInstanceid;
//    }

}
