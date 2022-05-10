package org.expasy.glyconnect.doppelganger.doppelganger.GETObject;

import com.google.gson.JsonObject;

public class _cellLine {
    private JsonObject cellLineJson;
    private String id;
    private String name;
    private String cellosaurusId;
    private boolean isProblematic;

    /**
     * Main constructor
     *
     * @param cellLineJson Cell line information in json format.
     */
    public _cellLine(JsonObject cellLineJson) {
        this.cellLineJson = cellLineJson;
        if ( this.cellLineJson.get("id") != null )
            this.setId(cellLineJson.get("id").getAsString());

        if ( this.cellLineJson.get("name") != null )
            this.setName(cellLineJson.get("name").getAsString());

        if ( this.cellLineJson.get("cellosaurus_id") != null )
            this.setCellosaurusId(cellLineJson.get("cellosaurus_id").getAsString());

        if ( this.cellLineJson.get("is_problematic") != null )
            this.setProblematic(cellLineJson.get("is_problematic").getAsBoolean());
    }

    public JsonObject getCellLineJson() {
        return cellLineJson;
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getCellosaurusId() {
        return cellosaurusId;
    }

    private void setCellosaurusId(String cellosaurusId) {
        this.cellosaurusId = cellosaurusId;
    }

    public boolean isProblematic() {
        return isProblematic;
    }

    private void setProblematic(boolean problematic) {
        isProblematic = problematic;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
